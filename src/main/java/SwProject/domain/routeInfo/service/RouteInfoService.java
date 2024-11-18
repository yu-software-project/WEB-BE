package SwProject.domain.routeInfo.service;

import SwProject.domain.routeInfo.domain.RouteInfo;
import SwProject.domain.routeInfo.dto.UpdateRouteInfoDto;

public interface RouteInfoService {
    RouteInfo createRouteInfo();
    void updateRouteInfo(UpdateRouteInfoDto updateRouteInfoDto);
}
