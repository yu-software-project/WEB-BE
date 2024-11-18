package SwProject.domain.routeInfo.service;

import SwProject.config.constant.DbInitConstants;
import SwProject.domain.routeInfo.domain.RouteInfo;
import SwProject.domain.routeInfo.dto.UpdateRouteInfoDto;
import SwProject.domain.routeInfo.repository.RouteInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteInfoServiceImpl implements RouteInfoService {
    private final RouteInfoRepository routeInfoRepository;
    @Override
    public RouteInfo createRouteInfo() {
        RouteInfo routeInfo = RouteInfo.builder()
                .memo(DbInitConstants.routeInfoInitMessage)
                .build();

        routeInfoRepository.save(routeInfo);
        return routeInfo;
    }

    @Override
    public void updateRouteInfo(UpdateRouteInfoDto updateRouteInfoDto) {
        routeInfoRepository.delete(updateRouteInfoDto.getOldRouteInfo());
        routeInfoRepository.save(updateRouteInfoDto.getNewRouteInf0());
    }
}
