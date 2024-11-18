package SwProject.domain.routeInfo.repository;

import SwProject.domain.routeInfo.domain.RouteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RouteInfoRepository extends JpaRepository<RouteInfo, CrudRepository> {
}
