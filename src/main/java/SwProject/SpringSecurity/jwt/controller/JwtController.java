package SwProject.SpringSecurity.jwt.controller;

import SwProject.SpringSecurity.jwt.dto.AccessTokenDto;
import SwProject.SpringSecurity.jwt.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class JwtController {
    private final JwtService jwtService;

    @Transactional
    @Operation(summary = "request new access Token", description = "어세스 토큰 재발급")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccessTokenDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/auth/update/access/token")
    public AccessTokenDto updateAccessToken(HttpServletRequest request){
        return jwtService.updateAccessToken(request);
    }


}