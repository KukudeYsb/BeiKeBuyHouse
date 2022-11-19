package buymyhome.com.config;

import buymyhome.com.util.LoginInterceptor;
import buymyhome.com.util.ReFlashTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 杨世博
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/user/login/**" ,
                        "/user/code/**"
                );
        // token刷新拦截器
        registry.addInterceptor(new ReFlashTokenInterceptor(stringRedisTemplate));
    }
}
