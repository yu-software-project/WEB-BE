package SwProject.businessProcess.auth.redis.repository;

import SwProject.businessProcess.auth.redis.model.RedisAuthCodeDto;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticationCode extends CrudRepository<RedisAuthCodeDto, String>{
}
