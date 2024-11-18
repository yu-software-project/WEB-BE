package SwProject.domain.scrapRecruitment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestScrapRecruitmentDto {

    @NotNull(message = "해당 공고글의 id를 입력하세요.")
    private Long recruitmentId;

}
