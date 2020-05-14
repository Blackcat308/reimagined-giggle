package com.laocat.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lao Cat
 * @Date: 2020/5/13 16:42
 */
@RestController
public class PermissionController {

    @RequestMapping("/docker")
    @PreAuthorize("hasRole('ROLE_DOCKER')")
    public String test1() {
        return "你有docker权限";
    }

    @PreAuthorize("hasRole('ROLE_JAVA')")
    @RequestMapping("/java")
    public String test2() {
        return "你有java权限";
    }

    @PreAuthorize("hasRole('ROLE_PHP')")
    @RequestMapping("/php")
    public String test3() {
        return "你有最好语言的权限";
    }

    @RequestMapping("/docker/{code}")
    @PreAuthorize("hasPermission('ROLE_DOCKER',#code)")
    public String test4(@PathVariable String code) {
        return "你有docker角色的" + code + "权限";
    }

    @RequestMapping("/java/{code}")
    @PreAuthorize("hasPermission('ROLE_JAVA',#code)")
    public String test5(@PathVariable String code) {
        return "你有java角色的" + code + "权限";
    }

    @RequestMapping("/php/{code}")
    @PreAuthorize("hasPermission('ROLE_PHP',#code)")
    public String test6(@PathVariable String code) {
        return "你有php角色的" + code + "权限";
    }

    @RequestMapping("/custom")
    @PreAuthorize("@bcCustomPermissionEvaluatorI.check('user')")
    public String test7() {
        return "你有自定义权限";
    }
}
