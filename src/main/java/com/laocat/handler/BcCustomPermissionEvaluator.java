package com.laocat.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

interface BcCustomPermissionEvaluatorI {
    boolean check(String authentication);
}

/**
 * @Author: Lao Cat
 * @Date: 2020/5/14 10:01
 */
@Slf4j
@Service("bcCustomPermissionEvaluatorI")
public class BcCustomPermissionEvaluator implements BcCustomPermissionEvaluatorI {

    @Override
    public boolean check(String authentication) {
        // 自定义的匹配规则
        log.info("进入自定义匹配器...");
        return true;
    }
}