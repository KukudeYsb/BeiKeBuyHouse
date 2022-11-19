package buymyhome.com.controller;

import buymyhome.com.common.Code;
import buymyhome.com.vojo.LoginFormDTO;
import buymyhome.com.vojo.Result;
import buymyhome.com.pojo.User;
import buymyhome.com.service.UserService;
import buymyhome.com.util.UserHolder;
import buymyhome.com.vojo.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨世博
 *
 * 用户
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param request
     * @param loginFormDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody LoginFormDTO loginFormDTO){
        return userService.login(request,loginFormDTO);
    }

    /**
     * 发送验证码
     * @param request
     * @param phone
     * @return
     */
    @PostMapping("/code/{phone}")
    public Result sendCode(HttpServletRequest request, @PathVariable String phone) {
        return userService.sendCode(request, phone);
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        return userService.logout(request);
    }

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    @PostMapping("/me")
    public Result me(HttpServletRequest request){
        UserDTO user = UserHolder.getUser();
        return new Result(user,Code.INQUIRE_OK);
    }
}
