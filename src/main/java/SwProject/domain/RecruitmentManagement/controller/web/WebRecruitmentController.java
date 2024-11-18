package SwProject.domain.RecruitmentManagement.controller.web;

import SwProject.Exception.collections.InputValid.BindingErrors;
import SwProject.config.constant.ControllerConstants;
import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.*;
import SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.dto.PerVolunteerByDateDto;
import SwProject.domain.RecruitmentManagement.facade.RecruitmentFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static SwProject.config.constant.ControllerConstants.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/web/recruitment")
public class WebRecruitmentController {
    private final RecruitmentFacadeService recruitmentFacadeService;

    @Transactional
    @Operation(summary = "createRecruitment", description = "봉사공고 등록 Api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "봉사공고 업로드가 완료되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @PostMapping("/create")
    public ResponseEntity<?> createRecruitment(@RequestBody @Valid RequestRecruitmentDto requestRecruitmentDto, BindingResult bindingResult) {
        handleBindingErrors(bindingResult);
        recruitmentFacadeService.createRecruitment(requestRecruitmentDto);
        return new ResponseEntity<>(ControllerConstants.completecreateRecruitment, HttpStatus.OK);
    }


    @Transactional
    @Operation(summary = "봉사공고 업데이트 api", description = "봉사공고 업데이트 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved recruitments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PutMapping("/update")
    public ResponseEntity<?> updateRecruitment(@RequestBody @Valid RequestRecruitmentUpdateDto requestRecruitmentUpdateDto, BindingResult bindingResult) {
        handleBindingErrors(bindingResult);
        recruitmentFacadeService.updateRecruitment(requestRecruitmentUpdateDto);
        return new ResponseEntity<>(ControllerConstants.completeupdateRecruitment, HttpStatus.OK);
    }

    @PostMapping("/findByName")
    @Transactional(readOnly = true)
    @Operation(summary = "제목으로 봉사공고글 검색 api", description = "제목으로 봉사공고글 검색 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved recruitments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecruitmentPageDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<RecruitmentPageDto> findByName(@RequestBody @Valid RequestFindByName requestFindByName,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         BindingResult bindingResult) {
        handleBindingErrors(bindingResult);
        Pageable pageable = PageRequest.of(page, size);
        RecruitmentPageDto recruitments = recruitmentFacadeService.findByNameWithPagination(requestFindByName, pageable);
        return ResponseEntity.ok(recruitments);
    }


    @Transactional
    @Operation(summary = "Get Recruitments Pagination", description = "봉사공고 목록 페이지네이션")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved recruitments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecruitmentPageDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/get/pagination")
    public RecruitmentPageDto getRecruitments(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return recruitmentFacadeService.getRecruitments(pageable);
    }

    @PostMapping("/get/volunteers/by/date")
    @Transactional(readOnly = true)
    @Operation(summary = "Get Volunteers by Date", description = "해당 날짜에 봉사자를 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved volunteers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VolunteerByDateResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<VolunteerByDateResponseDto> getVolunteersByDate(@RequestBody @Valid RequestVolunteersByDate requestVolunteersByDate,
                                                                          BindingResult bindingResult) {
        handleBindingErrors(bindingResult);
        VolunteerByDateResponseDto volunteers = recruitmentFacadeService.getVolunteersByDate(requestVolunteersByDate);
        return ResponseEntity.ok(volunteers);
    }


    @PostMapping("/accept")
    @Transactional
    @Operation(summary = "accept Volunteers by Date", description = "특정 날짜의 봉사자 승인신청 완료 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved volunteers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<String> acceptVolunteer(@RequestBody PerVolunteerByDateDto perVolunteerByDateDto) {
        recruitmentFacadeService.acceptVolunteer(perVolunteerByDateDto.getRecruitmentId(), perVolunteerByDateDto.getVolunteerId(), perVolunteerByDateDto.getRecruitmentDate());
        return new ResponseEntity<>(completeAcceptVolunteer, HttpStatus.OK);
    }

    @PostMapping("/delete/accept")
    @Transactional
    @Operation(summary = "Delete Volunteers by Date from accept", description = "승인 완료 명단 특정 날짜의 봉사자 삭제 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted volunteer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<String> deleteAcceptanceVolunteer(@RequestBody PerVolunteerByDateDto perVolunteerByDateDto) {
        recruitmentFacadeService.deleteVolunteerFromRecruitmentAccept(perVolunteerByDateDto.getRecruitmentId(), perVolunteerByDateDto.getVolunteerId(), perVolunteerByDateDto.getRecruitmentDate());
        return new ResponseEntity<>(completeDeleteWaitingVolunteer, HttpStatus.OK);
    }

    @PostMapping("/delete/waiting")
    @Transactional
    @Operation( summary = "Delete Volunteers by Date from waiting", description = "대기자 명단 특정 날짜의 봉사자 삭제 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted volunteer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<String> deleteWaitingVolunteer(@RequestBody PerVolunteerByDateDto perVolunteerByDateDto) {
        recruitmentFacadeService.deleteVolunteerFromRecruitmentWaiting(perVolunteerByDateDto.getRecruitmentId(), perVolunteerByDateDto.getVolunteerId(), perVolunteerByDateDto.getRecruitmentDate());
        return new ResponseEntity<>(completeDeleteAcceptanceVolunteer, HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
