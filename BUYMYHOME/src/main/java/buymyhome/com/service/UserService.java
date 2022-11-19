package buymyhome.com.service;

import buymyhome.com.vojo.LoginFormDTO;
import buymyhome.com.vojo.Result;
import buymyhome.com.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨世博
 *
 * 登录
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param request
     * @param loginFormDTO
     * @return
     */
    Result login(HttpServletRequest request, LoginFormDTO loginFormDTO);

    /**
     * 发送验证码
     * @param request
     * @param phone
     * @return
     */
    Result sendCode(HttpServletRequest request, String phone);

    /**
     * 退出登录
     * @param request
     * @return
     */
    Result logout(HttpServletRequest request);
}
