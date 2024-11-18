package SwProject.businessProcess.auth.redis.service;

import SwProject.businessProcess.auth.redis.repository.AuthenticationCode;
import SwProject.businessProcess.auth.redis.model.RedisAuthCodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthRedisServiceImpl implements AuthRedisService {
    private final AuthenticationCode authenticationCode;
    @Override
    public RedisAuthCodeDto save(RedisAuthCodeDto authCodeDto) {
       return authenticationCode.save(authCodeDto);
    }

    @Override
    public Optional<RedisAuthCodeDto> find(String id) {
        return authenticationCode.findById(id);
    }

}

