package buymyhome.com.util;

import buymyhome.com.dao.UserDao;
import buymyhome.com.pojo.User;
import buymyhome.com.vojo.UserDTO;

/**
 * @author 杨世博
 */
public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();

    public static void saveUser(UserDTO user){
        tl.set(user);
    }

    public static UserDTO getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
