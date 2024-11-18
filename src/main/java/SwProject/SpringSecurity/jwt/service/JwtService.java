package SwProject.SpringSecurity.jwt.service;

import SwProject.SpringSecurity.jwt.dto.AccessTokenDto;
import jakarta.servlet.http.HttpServletRequest;

public interface JwtService {
    AccessTokenDto updateAccessToken(HttpServletRequest request);
}
