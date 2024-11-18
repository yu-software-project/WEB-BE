package SwProject.domain.center.childCenter.controller;

import SwProject.Exception.collections.InputValid.BindingErrors;
import SwProject.domain.center.childCenter.dto.put.ResponseChildCenterDetailDto;
import SwProject.domain.center.childCenter.dto.put.RequestFindWordDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/center")
public class ChildCenterController {
    private final CenterFacadeService centerFacadeService;

    @Transactional
    @Operation(summary = "Find Child Center", description = "Find child centers based on search criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved child centers",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseChildCenterDetailDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/search/app")
    public ResponseEntity<?> findChildCenter(@RequestBody @Valid RequestFindWordDto requestFindWordDto, BindingResult bindingResult) throws IOException {
        handleBindingErrors(bindingResult);

        List<ChildCenter> centers = centerFacadeService.findChildCenter(requestFindWordDto);
        List<ResponseChildCenterDetailDto> response = centerFacadeService.convertResponseToWeb(centers);

        return ResponseEntity.ok(response);
    }


    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindingErrors();
        }
    }


}
