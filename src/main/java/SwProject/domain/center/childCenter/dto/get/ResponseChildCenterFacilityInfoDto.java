package SwProject.domain.center.childCenter.dto.get;

import SwProject.domain.greetings.domain.Greetings;
import SwProject.domain.routeInfo.domain.RouteInfo;
import SwProject.domain.yearHistory.model.DecadeYear;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChildCenterFacilityInfoDto {
    private Greetings greeting;
    private List<DecadeYear> decadeYearList;
    private RouteInfo routeInfo;
}
