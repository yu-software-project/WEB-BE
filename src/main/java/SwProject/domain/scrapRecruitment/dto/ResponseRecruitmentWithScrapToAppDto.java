package SwProject.domain.scrapRecruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRecruitmentWithScrapToAppDto {
    private Long id;
    private String recruitmentName;
    private String childCenterName;
    private LocalDate recruitmentStartDate;
    boolean isScrap;
}
