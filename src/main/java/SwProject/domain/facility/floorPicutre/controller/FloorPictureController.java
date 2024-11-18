package SwProject.domain.facility.floorPicutre.controller;

import SwProject.Exception.collections.InputValid.BindingErrors;
import SwProject.Exception.collections.IoException.ImageInputException;
import SwProject.config.constant.ControllerConstants;
import SwProject.domain.facility.facade.FacilityFacadeService;
import SwProject.domain.facility.floorPicutre.dto.FloorPictureDto;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/facility/floorPicture")
public class FloorPictureController {
    private final FacilityFacadeService facilityFacadeService;

    @Transactional
    @Operation(summary = "floorPicture create Api", description = "층별 사진 create api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "층별 소개가 업데이트 완료되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @PostMapping("/create") //층별사진 생성
    public ResponseEntity<?> createFloorPicture(@RequestPart("CreateFloorPictureDto") @Valid List<FloorPictureDto> floorPictureDtoList,
                                                @RequestPart("FloorPictureFile") List<MultipartFile> multipartFile, BindingResult bindingResult) throws ImageInputException {
        handleBindingErrors(bindingResult);
        //컨트롤러가 소수의 서비스 레이어만을 관리하기 위해 파사드 패턴 생성
        facilityFacadeService.createFloorPicture(floorPictureDtoList, multipartFile);

        return new ResponseEntity<>(ControllerConstants.completeUpdateFloorSize, HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "floorPicture update Api", description = "층별 사진 update api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(schema = @Schema(implementation = String.class, example = "층별 소개가 업데이트 완료되었습니다."))}),
            @ApiResponse(responseCode = "400", description = "Not found"),
    })
    @PutMapping("/update") //층별사진 수정
    public ResponseEntity<?> updateFloorPicture(@RequestPart("UpdateFloorPictureDto") @Valid List<FloorPictureDto> floorPictureDtoList,
                                                @RequestPart("FloorPictureFile") List<MultipartFile> multipartFile, BindingResult bindingResult) throws ImageInputException {
        handleBindingErrors(bindingResult);
        //컨트롤러가 소수의 서비스 레이어만을 관리하기 위해 파사드 패턴 생성
        facilityFacadeService.updateFloorPicuture(floorPictureDtoList, multipartFile);
        return new ResponseEntity<>(ControllerConstants.completeUpdateFloorSize, HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }

}
