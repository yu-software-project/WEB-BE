package SwProject.businessProcess.redis.service;

import SwProject.SpringSecurity.jwt.redis.model.RedisRefreshTokenDto;
import SwProject.SpringSecurity.jwt.redis.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtRedisServiceImpl implements JwtRedisService {
    private final RefreshTokenRepository refreshTokenRepository;
    @Override
    public RedisRefreshTokenDto save(RedisRefreshTokenDto redisRefreshTokenDto) {
       return refreshTokenRepository.save(redisRefreshTokenDto);
    }

    @Override
    public Optional<RedisRefreshTokenDto> find(String id) {
        return refreshTokenRepository.findById(id);
    }

}
