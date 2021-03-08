package com.yxd.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Description
 * @Author yangxiaodong
 * @Date 2021-03-08
 **/
public class ServerStartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 将 web 应用名称（路径）保存到 application 范围中
        ServletContext servletContext = sce.getServletContext();
        String contextPath = servletContext.getContextPath();
        servletContext.setAttribute("APP_PATH", contextPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
