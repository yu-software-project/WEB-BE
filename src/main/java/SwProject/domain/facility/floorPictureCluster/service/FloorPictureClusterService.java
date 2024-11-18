package SwProject.domain.facility.floorPictureCluster.service;

import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;

import java.util.List;

public interface FloorPictureClusterService {
//    void createFloorPictureList(FloorPictureListUpdateRequest floorPictureListUpdateRequest);
    FloorPictureCluster createFloorPictureCluster(FacilityIntroduction facilityIntroduction, int floor);
    FloorPictureCluster findByFloor(int floor, ChildCenter childCenter);
    List<FloorPictureCluster> getAllFloorPictureCluster(FacilityIntroduction facilityIntroduction);


}
