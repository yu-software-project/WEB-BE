package SwProject.SpringSecurity.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllJwtTokenDto {
    private AccessTokenDto accessTokenDto;
    private RefreshTokenDto refreshTokenDto;
}
