package SwProject.businessProcess.facade.controller;

import SwProject.businessProcess.facade.dto.response.HomeInAppResponseDto;
import SwProject.businessProcess.facade.service.DatabaseFacadeService;
import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentReservationDto;
import SwProject.domain.center.childCenter.dto.get.ResponseChildCenterFacilityInfoDto;
import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RecruitmentSummaryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/get")
public class AppGetController {
    private final DatabaseFacadeService databaseFacadeService;

    @Transactional
    @Operation(summary = "Find Child Center", description = "Find child centers based on search criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseChildCenterFacilityInfoDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/center/{id}/facility/info")
    public ResponseEntity<?> getChildCenterFacilityIntroById(@PathVariable Long id) {
        ResponseChildCenterFacilityInfoDto responseDto = databaseFacadeService.getChildCenterFailcilityInfo(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "Find Child Center", description = "Find child centers based on search criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RecruitmentSummaryDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/center/{id}/recruitment/preview")
    public ResponseEntity<?> getChildCenterRecruitmentPreviewById(@PathVariable Long id) {
        List<RecruitmentSummaryDto> responseDto = databaseFacadeService.getChildCenterRecruitmentPreveiw(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/center/{id}/recruitment/reservation")
    @Operation(summary = "Recruitment Reservation", description = "해당 봉사공고 신청")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecruitmentReservationDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<?> getChildCenterRecruitmentReservationById(@PathVariable Long id) {
        RecruitmentReservationDto responseDto = databaseFacadeService.getRecruitmentReservation(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/app/home")
    @Operation(summary = "get Home In App", description = "앱에서 홈 화면 get 요청 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 app 화면을 불러왔습니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation =  HomeInAppResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<?> getHomeInApp() {
        HomeInAppResponseDto responseDto = databaseFacadeService.getHomeInApp();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
