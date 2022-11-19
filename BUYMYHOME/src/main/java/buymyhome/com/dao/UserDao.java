package buymyhome.com.dao;

import buymyhome.com.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 杨世博
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
