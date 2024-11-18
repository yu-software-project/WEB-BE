package SwProject.businessProcess.facade.dto.response;

import SwProject.domain.routeInfo.domain.RouteInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAddressAndRouteInfoDto {
    String Address;
    RouteInfo routeInfo;
}
