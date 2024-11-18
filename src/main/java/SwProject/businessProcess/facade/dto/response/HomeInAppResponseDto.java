package SwProject.businessProcess.facade.dto.response;

import SwProject.domain.scrapRecruitment.dto.ResponseRecruitmentWithScrapToAppDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomeInAppResponseDto {
    private String volunteerName;
    private int recruitmentCountByVolunteer;
    private List<ResponseRecruitmentWithScrapToAppDto> scrapRecruitments;
}
