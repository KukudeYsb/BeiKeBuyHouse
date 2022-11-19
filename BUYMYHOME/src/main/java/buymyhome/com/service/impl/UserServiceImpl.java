package buymyhome.com.service.impl;

import buymyhome.com.common.Code;
import buymyhome.com.util.RegexUtils;
import buymyhome.com.vojo.LoginFormDTO;
import buymyhome.com.vojo.Result;
import buymyhome.com.dao.UserDao;
import buymyhome.com.pojo.User;
import buymyhome.com.service.UserService;
import buymyhome.com.vojo.UserDTO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static buymyhome.com.common.SystemConstants.*;

/**
 * @author 杨世博
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserDao userDao;

    @Override
    public Result login(HttpServletRequest request, LoginFormDTO loginFormDTO) {
        //判断手机号是否输入正确
        if (RegexUtils.isPhoneInvalid(loginFormDTO.getPhone())) {
            return new Result(null,Code.OPERATE_ERR,"手机号输入错误");
        }

        //从redis获取验证码
        String code = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + loginFormDTO.getPhone());
//        Object code = request.getSession().getAttribute("code");
        //判断验证码时候输入正确
        if(code==null || !code.equals(loginFormDTO.getCode())){
            return new Result(null,Code.LOGIN_ERR1,"验证码输入错误");
        }
        //查找手机号是否存在
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("phone",loginFormDTO.getPhone());
        User user = userDao.selectOne(qw);
        //不存在，自动注册
        if (user == null){
            user = createUserWithPhone(loginFormDTO.getPhone());
        }

        //使用UUID生成token           并将对象存入redis中
        String token = LOGIN_TOKEN_KEY + UUID.randomUUID(true).toString();
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        stringRedisTemplate.opsForHash().putAll(token, userMap);

        //设置token的有效期
        stringRedisTemplate.expire(token, LOGIN_USER_TOKEN_TIME, TimeUnit.MINUTES);

//        request.getSession().setAttribute("user",BeanUtil.copyProperties(user, UserDTO.class));
        return new Result(token,Code.LOGIN_ERR1);
    }

    /**
     * 创建一个新的账号
     * @param phone
     * @return
     */
    public User createUserWithPhone(String phone){
        java.util.Date utilDate=new Date();
        java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());


        User user = new User();
        user.setId(null);
        user.setPhone(phone);
        user.setUserName(CREATE_USER_NAME + RandomUtil.randomString(10));
        user.setCreateTime(sqlDate);
        user.setUpdateTime(sqlDate);
        user.setIsDelete(0);
        //保存用户
        save(user);
        return user;
    }

    @Override
    public Result sendCode(HttpServletRequest request, String phone) {
        if (RegexUtils.isPhoneInvalid(phone)) {
            return new Result(null,Code.OPERATE_ERR,"手机号输入错误");
        }

        //随机生成验证码
        String code = RandomUtil.randomNumbers(6);

        //将生成的验证码保存到 redis 中
        stringRedisTemplate.opsForValue().set( LOGIN_CODE_KEY + phone, code, LOGIN_CODE_VALID_TIME, TimeUnit.MINUTES);
//        request.getSession().setAttribute("code",code);

        //发送验证码（暂未实现，模拟）
        log.debug("成功发送短信验证码：{" + code + "}");

        return new Result(null,Code.OPERATE_OK,"验证码发送成功");
    }

    @Override
    public Result logout(HttpServletRequest request) {
        //清除Session
        request.getSession().removeAttribute("user");

        return new Result();
    }
}
