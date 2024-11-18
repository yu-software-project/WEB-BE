package SwProject.domain.RecruitmentManagement.domain.recruitment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRecruitmentUpdateDto {
    @Schema(description = "The ID of the recruitment", example = "1")
    private Long id;

    @Schema(description = "The new recruitment data")
    private RequestRecruitmentDto newRecruitmentDto;
}