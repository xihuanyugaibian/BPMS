package com.bpms.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.bpms.shiro.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/*shiro的注册，springboot没有配置文件，只有通过java类的形式进行注册，
关于shiro一共用 普通java类，SSM框架，springboot分别写了三遍，原理大致是一样的。区别在于怎么配置
1.shiro过滤器，  对登录的用户进行 认证，授权。对url路径进行的拦截，需要shiro过滤器
2.shiro管理器，管理认证 授权 缓存 等等。但是都需要创建配置。首先创建安全管理器，去管理各种组件。
3.shiro自定义域。shiro安全管理器中认证需要的组件
4.密码匹配器。
5.缓存
6.使shiro权限注解生效
7.对类进行aop代理
8.对thymeleaf进行支持
*/
@Configuration//说明这个被为配置信息类，spring 把这个类作为配置文件处理，把里面被@Bean修饰的方法返回的对象交给IOC容器管理。注入需要的地方
                //这个注入，好像时方法中的参数。会被自动调用
public class ShiroConfig {

    //Shiro过滤器配置，并注入安全管理器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("sm") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setLoginUrl("/login.html");
        bean.setUnauthorizedUrl("/403.html");
        Map map = new LinkedHashMap();
        map.put("/login.html", "anon");
        map.put("/doLogin", "anon");
        map.put("/css/**", "anon");
        map.put("/static/**", "anon");
        map.put("/js/**", "anon");
        map.put("/images/**", "anon");
        map.put("/*", "authc");
        bean.setFilterChainDefinitionMap(map);
        bean.setSecurityManager(securityManager);
        return bean;
    }

    //Shiro安全管理器，把域放入安全管理器
    @Bean("sm")
    public DefaultWebSecurityManager securityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    //配置自定义域，可以注入密码匹配器
    @Bean
    public CustomRealm customRealm(HashedCredentialsMatcher matcher, MemoryConstrainedCacheManager manager) {
        CustomRealm realm = new CustomRealm();
        realm.setCredentialsMatcher(matcher);//注入
        realm.setCacheManager(manager);
        return realm;
    }

    //密码匹配器
    @Bean
    public HashedCredentialsMatcher matcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        //matcher.setHashIterations(2);
        return matcher;
    }

    //缓存
    @Bean
    public MemoryConstrainedCacheManager cacheManager() {
        MemoryConstrainedCacheManager manager = new MemoryConstrainedCacheManager();
        return manager;
    }

    //使Shiro权限注解生效。让controller中添加的 对权限，或者角色 后台拦截的标签生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    //对类进行aop代理。shiro的认证功能 属于在请求到达方法前进行的，切面编程。执行aop代理
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    //支持thyemleaf
    @Bean
    public ShiroDialect shiroDialect() {
        ShiroDialect dialect = new ShiroDialect();
        return dialect;
    }
}
