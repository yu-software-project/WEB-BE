package SwProject.businessProcess.auth.redis.service;

import SwProject.businessProcess.auth.redis.model.RedisAuthCodeDto;

import java.util.Optional;

public interface AuthRedisService {
    RedisAuthCodeDto save(RedisAuthCodeDto authCodeDto);
    //리프레시 토큰을 redis 에 저장

    Optional<RedisAuthCodeDto> find(String id);

}
