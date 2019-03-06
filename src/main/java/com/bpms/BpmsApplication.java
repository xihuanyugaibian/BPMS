package com.bpms;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan(basePackages = "com.bpms.dao")
@EnableTransactionManagement//开启注解事务的支持
public class BpmsApplication extends SpringBootServletInitializer {
    //把springboot项目打成war包的配置，还有pom.xml。打成的war包放在 Tomcat启动
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(BpmsApplication.class);
        return super.configure(builder);

    }

    public static void main(String[] args) {
        SpringApplication.run(BpmsApplication.class, args);
    }

    /*注册servlet及过滤器*/

    //用来展示视图
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        //创建一个servlet 指定一个路径  添加到servlet配置对象中
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //bean.setServlet(new StatViewServlet());//这三句是上面一句
        //ArrayList<String> list = new ArrayList<>();
        //list.add("/druid/*");
        //bean.setUrlMappings(list);

        bean.setName("druid");
        Map map = new HashMap();
        //map.put("loginUserName","admin");
        //map.put("loginPassword","123456");
        map.put("allow", "127.0.0.1");
        bean.setInitParameters(map);
        bean.setOrder(1);
        return bean;
    }

    //过滤器 用于收集数据
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());//用什么过滤器进行拦截（收集）数据，添加到过滤器配置对象中
        bean.setOrder(1);
        Map map = new HashMap();
        map.put("exclusions", "*.js,*.css,/druid/*");//排除静态资源
        bean.addUrlPatterns("/*");
        bean.setInitParameters(map);
        return bean;
    }
}
