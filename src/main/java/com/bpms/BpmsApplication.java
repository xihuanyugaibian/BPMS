package com.bpms;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan(basePackages = "com.bpms.dao")
@EnableTransactionManagement//开启注解事务的支持
public class BpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BpmsApplication.class, args);
    }

    //用来展示视图
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        bean.setName("druid");
        Map map = new HashMap();
        //map.put("loginUserName","admin");
        //map.put("loginPassword","123456");
        map.put("allow", "127.0.0.1");
        bean.setInitParameters(map);
        bean.setOrder(1);
        return bean;
    }

    //用于收集数据
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        bean.setOrder(1);
        Map map = new HashMap();
        map.put("exclusions", "*.js,*.css,/druid/*");//排除静态资源
        bean.addUrlPatterns("/*");
        bean.setInitParameters(map);
        return bean;
    }
}
