package SwProject.businessProcess.auth.app.controller;

import SwProject.Exception.collections.InputValid.BindingErrors;
import SwProject.SpringSecurity.jwt.dto.AllJwtTokenDto;
import SwProject.businessProcess.auth.app.dto.AppSignInDto;
import SwProject.businessProcess.auth.app.dto.AppSignUpDto;
import SwProject.businessProcess.auth.service.Facade.app.AuthAppFacadeService;
import SwProject.config.constant.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Auth", description = "auth Api")
@RestController
@RequestMapping("api/auth/app")
@RequiredArgsConstructor
@Slf4j
public class AppAuthController {
    private final AuthAppFacadeService authAppFacadeService;

    @Transactional //매니저와 보육원의 동시저장을 보장해줄 애노테이션
    @PostMapping("/signUp")
    @Operation(summary = "App signUp Api summary", description = "회원가입 시 사용할 api 명세서")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "회원가입이 완료되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not founc"),
    })
    public ResponseEntity<?> authSignup(@RequestBody @Valid AppSignUpDto appSignUpDto, BindingResult bindingResult ) throws IOException {
        //@Valid 체크
        handleBindingErrors(bindingResult);
        authAppFacadeService.authSignUp(appSignUpDto);
        return new ResponseEntity<>(ControllerConstants.completeSignUp, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "auth SignIn", description = "앱 내에서의 로그인 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllJwtTokenDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/signIn")
    public ResponseEntity<?> authSignIn(@RequestBody @Valid AppSignInDto signDto, BindingResult bindingResult) {
        handleBindingErrors(bindingResult);
        AllJwtTokenDto allJwtTokenDto = authAppFacadeService.authSignIn(signDto);
        return ResponseEntity.ok(allJwtTokenDto);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
