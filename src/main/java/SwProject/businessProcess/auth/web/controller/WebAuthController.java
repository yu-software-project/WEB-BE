package SwProject.businessProcess.auth.web.controller;

import SwProject.Exception.collections.InputValid.BindingErrors;
import SwProject.SpringSecurity.jwt.dto.AllJwtTokenDto;
import SwProject.businessProcess.auth.web.dto.WebSignInDto;
import SwProject.businessProcess.auth.web.dto.WebSignUpDto;
import SwProject.businessProcess.auth.service.Facade.web.AuthWebFacadeService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Auth", description = "auth Api")
@RestController
@RequestMapping("api/auth/web")
@RequiredArgsConstructor
@Slf4j
public class WebAuthController {
    private final AuthWebFacadeService authWebFacadeService;

    @Transactional //매니저와 보육원의 동시저장을 보장해줄 애노테이션
    @PostMapping("/signUp")
    @Operation(summary = "Web signUp summary", description = "웹 회원가입 시 사용할 api 명세서")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "회원가입이 완료되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    public ResponseEntity<?> authSignup(@RequestPart("signUpDto") @Valid WebSignUpDto webSignUpDto,
                                        @RequestPart("certificateFile") MultipartFile multipartFile,
                                        BindingResult bindingResult ) throws IOException {
        //@Valid 체크
        handleBindingErrors(bindingResult);
        authWebFacadeService.authSignup(webSignUpDto, multipartFile);
        return new ResponseEntity<>(ControllerConstants.completeSignUp, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "web SignIn api 명세서 ", description = "웹 로그인 시 사용할 api 명세서")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllJwtTokenDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/signIn")
    public ResponseEntity<?> authSignIn(@RequestBody @Valid WebSignInDto signDto, BindingResult bindingResult) {
        // BindingResult 에러 처리
        handleBindingErrors(bindingResult);

        AllJwtTokenDto allJwtTokenDto = authWebFacadeService.authSignIn(signDto);
        return ResponseEntity.ok(allJwtTokenDto);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
