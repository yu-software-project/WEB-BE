package SwProject.SpringSecurity.jwt.redis.repository;

import SwProject.SpringSecurity.jwt.redis.model.RedisRefreshTokenDto;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RedisRefreshTokenDto, String> {

}