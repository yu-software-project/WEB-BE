package SwProject.businessProcess.facade.controller;

import SwProject.businessProcess.facade.dto.response.ResponseAddressAndRouteInfoDto;
import SwProject.businessProcess.facade.dto.response.ResponseGetFloorSizeAndPictureCluster;
import SwProject.businessProcess.facade.dto.response.ResponseGetGreetingsAndYearHistoryDto;
import SwProject.businessProcess.facade.service.DatabaseFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/get")
public class WebGetController {
    private final DatabaseFacadeService databaseFacadeService;

    @Transactional
    @Operation(summary = "get Greetings and YearHistory", description = "인사말과 연혁 가져올 get Api 명세서")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseGetGreetingsAndYearHistoryDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/greeting/and/year")
    public ResponseEntity<?> getAllGreetingsAndYearHistory() {
        ResponseGetGreetingsAndYearHistoryDto responseGetGreetingsAndYearHistoryDto = databaseFacadeService.getGreetingsandYearHistory();
        return ResponseEntity.ok(responseGetGreetingsAndYearHistoryDto);
    }

    @Transactional
    @Operation(summary = "get All Facility Ficture", description = "시설 사진 모음집에 대한 get 요청 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseGetFloorSizeAndPictureCluster.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/facility/size/and/ficture")
    public ResponseEntity<?> getAllFacilityFicture() {
        ResponseGetFloorSizeAndPictureCluster response = databaseFacadeService.getFailciltySizeAndFicture();
        return ResponseEntity.ok(response);
    }

    @Transactional
    @Operation(summary = "get Address And RouteInfo", description = "해당 기관의 주소와 찾아오는 길 정보 반환")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseAddressAndRouteInfoDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @GetMapping("/address/and/routeinfo")
    public ResponseEntity<?> getAddressAndRouteInfo(){
        ResponseAddressAndRouteInfoDto responseAddressAndRouteInfoDto = databaseFacadeService.getAddressAndRouteInfo();
        return ResponseEntity.ok(responseAddressAndRouteInfoDto);
    }

}
