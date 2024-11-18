package SwProject.SpringSecurity.jwt.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/api/auth/set-redis-key")
    public String setRedisKey() {
        redisTemplate.opsForValue().set("test-key", "test-value");
        return "Key set";
    }
}