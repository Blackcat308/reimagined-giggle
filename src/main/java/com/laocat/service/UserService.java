package com.laocat.service;

import com.laocat.entity.SysRole;
import com.laocat.entity.SysUser;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Lao Cat
 * @Date: 2020/5/13 15:29
 */
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final MongoTemplate mongoTemplate;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser sysUser = mongoTemplate.findOne(Query.query(Criteria.where("userName").is(userName)), SysUser.class);
        if (Objects.isNull(sysUser)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 模拟这个是数据库取出来的权限
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        List<SysRole> roles = sysUser.getRoles();
        for (SysRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new User(sysUser.getUserName(), sysUser.getPassWord(), authorities);
    }
}
