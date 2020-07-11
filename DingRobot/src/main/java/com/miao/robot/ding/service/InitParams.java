package com.miao.robot.ding.service;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Map;

@Service
public class InitParams implements InitializingBean, ServletContextAware {

    private static String baseParamsSql = "select * from miao_params";

    @Autowired
    private JdbcUtils jdbcUtils;

    public static Map<String, String> miaoParams;

    @Override
    public void afterPropertiesSet() throws Exception {
        return;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        miaoParams = jdbcUtils.queryParamsMap(baseParamsSql);
    }
}
