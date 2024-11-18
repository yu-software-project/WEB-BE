package SwProject.SpringSecurity.jwt.redis.model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value = "refreshToken", timeToLive = 3) //3일 뒤 자동삭제 :  60 * 60 * 24 * 3
public class RedisRefreshTokenDto {

    @Id
    private String id;
    private String refrehToken;

}

//web의 경우 id에 emailId 들어감
//app의 경우 id에 phoneNumber 들어감.