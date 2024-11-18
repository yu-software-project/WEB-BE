package SwProject.domain.facility.floorPictureCluster.repository;

import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import SwProject.domain.facility.floorPicutre.model.FloorPicture;

import java.util.List;

public interface FloorPictureClusterRepositoryCustom {
    FloorPictureCluster getFloorPicutreClusterPk(FacilityIntroduction facilityIntroduction);
    void createFloorPictureList(FloorPictureCluster floorPictureCluster, FloorPicture floorPicture);
    List<FloorPictureCluster> getAllFloorPicutre (FacilityIntroduction facilityIntroduction);
    FloorPictureCluster findByFloor(int floor, ChildCenter childCenter);
}
