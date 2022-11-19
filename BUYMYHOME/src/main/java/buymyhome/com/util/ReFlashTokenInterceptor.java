package buymyhome.com.util;

import buymyhome.com.vojo.UserDTO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static buymyhome.com.common.SystemConstants.LOGIN_TOKEN_KEY;
import static buymyhome.com.common.SystemConstants.LOGIN_USER_TOKEN_TIME;

/**
 * @author 杨世博
 */
public class ReFlashTokenInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public ReFlashTokenInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头里面的token
        String token = LOGIN_TOKEN_KEY + request.getHeader("authorization");
        if (StrUtil.isBlank(token)) {
            return true;
        }

        // 基于token获取redis中的用户
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(token);

        if(userMap.isEmpty()){
            return true;
        }
        // 将查询到的Hash数据装换为UserDTO对象
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
        // 保存用户信息到ThreadLocal
        UserHolder.saveUser(userDTO);

        // 刷新token的有效期
        stringRedisTemplate.expire(token, LOGIN_USER_TOKEN_TIME, TimeUnit.MINUTES);
        // 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除用户
        UserHolder.removeUser();
    }
}
