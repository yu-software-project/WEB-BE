package SwProject.domain.facility.floorSize.repository;

import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.floorSize.model.FloorSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FloorSizeRepository extends JpaRepository<FloorSize, Long>, FloorSizeRepositoryCustom {
    List<FloorSize> findByFacilityIntroductionOrderByDisplayIndexAsc(FacilityIntroduction facilityIntroduction);
}
