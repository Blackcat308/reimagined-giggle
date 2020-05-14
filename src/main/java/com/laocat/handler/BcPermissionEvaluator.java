package com.laocat.handler;

import cn.hutool.core.collection.CollUtil;
import com.laocat.entity.SysRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: Lao Cat
 * @Date: 2020/5/13 14:57
 */
@Component
public class BcPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean hasPermission(Authentication authentication, Object customizeRole, Object customizePermission) {
        User user = (User) authentication.getPrincipal();

        Collection<GrantedAuthority> authorities = user.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = authorities
                .stream()
                .filter(a -> StringUtils.equals(a.getAuthority(), customizeRole.toString())).collect(Collectors.toList());

        for (GrantedAuthority authority : grantedAuthorities) {
            String roleName = authority.getAuthority();

            SysRole sysRole = mongoTemplate.findOne(Query.query(Criteria.where("roleName").is(roleName)), SysRole.class);
            //获取角色的所有权限
            List<String> permissions = CollUtil.newArrayList();
            if (Objects.nonNull(sysRole)) {
                permissions = Arrays.asList(sysRole.getPermissions());
            }

            // 遍历是否有存在的权限
            for (String permission : permissions) {

                if (permissions.contains(customizePermission.toString())) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
