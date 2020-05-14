//package com.laocat.init;
//
//import com.laocat.entity.SysRole;
//import com.laocat.entity.SysUser;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * @Author: Lao Cat
// * @Date: 2020/5/13 16:55
// */
//@Slf4j
//@Component
//@AllArgsConstructor
//public class DataInit implements CommandLineRunner {
//
//    private final MongoTemplate mongoTemplate;
//
//    public static final Set<SysRole> SYS_ROLES = new HashSet<>();
//
//    @Override
//    public void run(String... args) throws Exception {
//        log.info("正在初始化数据中....");
//        SYS_ROLES.add(new SysRole(1L, "ROLE_JAVA", new String[]{"c", "r", "u", "d"}));
//        SYS_ROLES.add(new SysRole(2L, "ROLE_DOCKER", new String[]{"c", "r", "d"}));
//        SYS_ROLES.add(new SysRole(3L, "ROLE_PHP", new String[]{"c",  "u"}));
//        SYS_ROLES.add(new SysRole(4L, "ROLE_PYTHON", new String[]{"c", "d"}));
//        SYS_ROLES.add(new SysRole(5L, "ROLE_CENTOS", new String[]{"c", "r", "d"}));
//
//        mongoTemplate.save(new SysRole(1L, "ROLE_JAVA", new String[]{"c", "r", "u", "d"}));
//        mongoTemplate.save(new SysRole(2L, "ROLE_DOCKER", new String[]{"c", "r", "d"}));
//        mongoTemplate.save(new SysRole(3L, "ROLE_PHP", new String[]{"c",  "u"}));
//        mongoTemplate.save(new SysRole(4L, "ROLE_PYTHON", new String[]{"c", "d"}));
//        mongoTemplate.save(new SysRole(5L, "ROLE_CENTOS", new String[]{"c", "r", "d"}));
//
//        mongoTemplate.save(new SysUser(1L, "laocat", "123456", SYS_ROLES
//                .stream()
//                .filter(r -> StringUtils.equalsAny(r.getRoleName(), "ROLE_JAVA", "ROLE_DOCKER")).collect(Collectors.toList())
//        ));
//        mongoTemplate.save(new SysUser(2L, "wangwu", "123456", SYS_ROLES
//                .stream()
//                .filter(r -> StringUtils.equalsAny(r.getRoleName(), "ROLE_PHP", "ROLE_DOCKER")).collect(Collectors.toList())
//        ));
//        mongoTemplate.save(new SysUser(3L, "lisi", "123456", SYS_ROLES
//                .stream()
//                .filter(r -> StringUtils.equalsAny(r.getRoleName(), "ROLE_PYTHON", "ROLE_CENTOS")).collect(Collectors.toList())
//        ));
//        log.info("初始化数据完毕....");
//    }
//}


/*
 * 如若第一次启动项目，请解除该类的注释，执行成功后 记得再将该类注释（其实无所谓）。
 *
 * */