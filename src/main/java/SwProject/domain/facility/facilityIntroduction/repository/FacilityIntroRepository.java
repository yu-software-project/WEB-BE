package SwProject.domain.facility.facilityIntroduction.repository;


import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FacilityIntroRepository extends JpaRepository<FacilityIntroduction, CrudRepository>, FacilityIntroRepositoryCustom {

}

