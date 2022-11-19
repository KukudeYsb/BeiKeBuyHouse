package buymyhome.com.util;

import buymyhome.com.common.SystemConstants;
import buymyhome.com.vojo.UserDTO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static buymyhome.com.common.SystemConstants.LOGIN_TOKEN_KEY;
import static buymyhome.com.common.SystemConstants.LOGIN_USER_TOKEN_TIME;

/**
 * @author 杨世博
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是否需要拦截（ThreadLocal中是否有用户）
        if (UserHolder.getUser() == null){
            response.setStatus(401);
            return false;
        }
        return true;
    }
}
