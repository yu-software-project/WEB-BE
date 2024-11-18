package SwProject.domain.center.likeChildCenter.controller;

import SwProject.Exception.collections.InputValid.BindingErrors;
import SwProject.config.constant.ControllerConstants;
import SwProject.domain.center.childCenter.dto.put.ResponseChildCenterToAppDto;
import SwProject.domain.center.likeChildCenter.dto.RequestLikeCenterDto;
import SwProject.domain.center.facade.CenterFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/app/like/center")
public class LikeCenterController {
    private final CenterFacadeService centerFacadeService;

    @Transactional
    @Operation(summary = "put Like Center button", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "관심 등록 보육원으로 등록 완료 되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @PostMapping("/create")
    public ResponseEntity<?> putLikeCenterButton(@RequestBody @Valid RequestLikeCenterDto requestLikeCenterDto, BindingResult bindingResult) {
        handleBindingErrors(bindingResult);
        centerFacadeService.addLikeCenter(requestLikeCenterDto);
        return new ResponseEntity<>(ControllerConstants.completeAddLikeCenter, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "search ChildCenter With Like In App", description = "보육원 찾기 api (관심글 여부 포함)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType =  "application/json",
                            array =  @ArraySchema(schema = @Schema(implementation = ResponseChildCenterToAppDto.class, example = "봉사공고 신청이 완료되었습니다.")))),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @GetMapping("/search")
    public ResponseEntity<?> searchRecruitmentWithLike() {
        List<ResponseChildCenterToAppDto> response = centerFacadeService.searchCenterWithLike();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
