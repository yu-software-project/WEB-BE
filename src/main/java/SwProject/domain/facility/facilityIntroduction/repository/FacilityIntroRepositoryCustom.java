package SwProject.domain.facility.facilityIntroduction.repository;

import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.floorSize.model.FloorSize;
import org.springframework.security.core.Authentication;

public interface FacilityIntroRepositoryCustom {
    // 이메일로 관리자db->보육원db로 외래키를 타고 들어가 보육원 필드에 저장된 시설소개의 기본키 가져오기
    public FacilityIntroduction getFacilityPk(Authentication authentication);
    public void updateFacilityFloorSizeList(FacilityIntroduction facilityIntroduction, FloorSize floorSize);
}
