package buymyhome.com;

import buymyhome.com.dao.UsedHouseDao;
import buymyhome.com.dao.HousingEstateDao;
import buymyhome.com.dao.UserDao;
import buymyhome.com.pojo.UsedHouse;
import buymyhome.com.pojo.User;
import buymyhome.com.service.UsedHouseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@SpringBootTest
class ComApplicationTests {
    @Autowired
    private static final ObjectMapper mapper = new ObjectMapper();

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void Test1() throws JsonProcessingException {
        UsedHouse usedHouse = new UsedHouse();

        String json = mapper.writeValueAsString(usedHouse);

        stringRedisTemplate.opsForValue().set("name",json);
        String jsonUser = stringRedisTemplate.opsForValue().get("name");

        UsedHouse house = mapper.readValue(jsonUser, UsedHouse.class);

        System.out.println(house);

    }
}
