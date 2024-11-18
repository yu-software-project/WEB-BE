package SwProject.domain.facility.floorSize.repository;

import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.facility.floorSize.model.FloorSize;


public interface FloorSizeRepositoryCustom {
    FloorSize isExitsFloorSize (int displayIndex, ChildCenter childCenter);
}
