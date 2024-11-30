package SwProject.businessProcess.auth.web.controller;

import SwProject.businessProcess.auth.smtp.EmailDto;
import SwProject.businessProcess.auth.web.dto.CenterDetailInfoReq;
import SwProject.businessProcess.auth.web.dto.CenterNameListReq;
import SwProject.businessProcess.auth.web.dto.CenterRegisterRefusalReason;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

public interface AdminControllerApi {
    //승인 대기 기관 목록 반환
    @Transactional
    @Operation(summary = "승인 대기 기관 목록 반환 ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CenterNameListReq.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/waiting/center/summary")
    ResponseEntity<?> showWaitingCenterSummary();

    //승인 완료 기관 목록 반환
    @Transactional
    @Operation(summary = "승인 완료 기관 목록 반환 ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CenterNameListReq.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/center/summary")
    ResponseEntity<?> showCenterSummary();

    //승인 대기 특정 기관 상세 정보 반환
    @Transactional
    @Operation(summary = "특정 승인 대기 기관 상세 정보 반환 ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CenterDetailInfoReq.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("waiting/center/detail/{center_id}")
    ResponseEntity<?> showWaitingCenterDetail(@PathVariable Long center_id);

    //승인 완료 특정 기관 상세 정보 반환
    @Transactional
    @Operation(summary = "특정 승인 완료 기관 상세 정보 반환 ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CenterDetailInfoReq.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/center/detail/{center_id}")
    ResponseEntity<?> showCenterDetail(@PathVariable Long center_id);

    @Transactional
    @Operation(summary = "특정 기관 삭제 요청")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "해당 기관을 삭제하였습니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @DeleteMapping("/center/delete/{center_id}")
    ResponseEntity<?> deleteCenter(@PathVariable Long center_id);

    @Transactional
    @Operation(summary = "특정 기관 회원가입 요청 거부")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CenterDetailInfoReq.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/center/register/refusal")
    ResponseEntity<?> refuseCenterRegister(@Valid @RequestBody CenterRegisterRefusalReason refusalReason, BindingResult bindingResult) throws MessagingException;

    @Transactional
    @Operation(summary = "특정 기관 회원가입 요청 승인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CenterDetailInfoReq.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/center/register/permit/{center_id}")
    ResponseEntity<?> permitCenterRegister(@PathVariable Long center_id);
}

