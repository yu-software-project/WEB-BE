package SwProject.businessProcess.facade.dto;

import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.greetings.domain.Greetings;
import SwProject.domain.routeInfo.domain.RouteInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CenterForeignKeyDto {
    FacilityIntroduction facility;
    Greetings greetings;
    RouteInfo routeInfo;
}
