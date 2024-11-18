package SwProject.domain.scrapRecruitment.controller;

import SwProject.Exception.collections.InputValid.BindingErrors;
import SwProject.domain.center.childCenter.dto.put.RequestFindWordDto;
import SwProject.domain.scrapRecruitment.dto.RequestScrapRecruitmentDto;
import SwProject.domain.scrapRecruitment.dto.ResponseRecruitmentWithScrapToAppDto;
import SwProject.domain.scrapRecruitment.facade.ScrapRecruitmentFacadeService;
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

import static SwProject.config.constant.ControllerConstants.completeScrapRecruitment;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/app/scrap/recruitment")
public class ScrapRecruitmentController {
    private final ScrapRecruitmentFacadeService scrapRecruitmentFacadeService;

    @Transactional
    @Operation(summary = "put Scrap Recruitment button", description = "봉사공고글 스크랩 기능 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "해당 공고글 스크랩 완료 되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @PostMapping("/create")
    public ResponseEntity<?> scrapRecruitmentButton(@RequestBody @Valid RequestScrapRecruitmentDto requestScrapRecruitmentDto, BindingResult bindingResult) {
        handleBindingErrors(bindingResult);
        scrapRecruitmentFacadeService.scrapRecruitment(requestScrapRecruitmentDto);
        return new ResponseEntity<>(completeScrapRecruitment, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "search Recruitment With scrap", description = "봉사공고 검색 api (스크랩 여부 포함)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType =  "application/json",
                            array =  @ArraySchema(schema = @Schema(implementation = ResponseRecruitmentWithScrapToAppDto.class, example = "봉사공고 신청이 완료되었습니다.")))),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @PostMapping("/search")
    public ResponseEntity<?> searchRecruitmentWithLike(@RequestBody @Valid RequestFindWordDto requestFindWordDto, BindingResult bindingResult) {
        handleBindingErrors(bindingResult);
        List<ResponseRecruitmentWithScrapToAppDto> response = scrapRecruitmentFacadeService.searchRecruitmentWithScrap(requestFindWordDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "get Recruitment With scrap", description = "봉사공고 반환 api (스크랩 여부 포함)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(mediaType =  "application/json",
                            array =  @ArraySchema(schema = @Schema(implementation = ResponseRecruitmentWithScrapToAppDto.class, example = "봉사공고 신청이 완료되었습니다.")))),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @GetMapping("/get")
    public ResponseEntity<?> getRecruitmentWithLike() {
        List<ResponseRecruitmentWithScrapToAppDto> response = scrapRecruitmentFacadeService.getScrappedRecruitments();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }
}
