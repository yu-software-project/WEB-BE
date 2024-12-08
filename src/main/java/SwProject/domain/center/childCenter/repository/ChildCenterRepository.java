package SwProject.domain.center.childCenter.repository;

import SwProject.businessProcess.auth.web.dto.CenterDetailInfoReq;
import SwProject.businessProcess.auth.web.dto.CenterNameListReq;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.manager.model.Manager;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChildCenterRepository extends JpaRepository<ChildCenter, Long>, ChildCenterRepositoryCustom{
    Optional<ChildCenter> findByPhoneNumId(String phoneNumId);

    @Query("SELECT c FROM ChildCenter c WHERE c.roadAddress LIKE %:keyword%")
    List<ChildCenter> findByRoadAddressContaining(@Param("keyword") String keyword);

    @Query("SELECT c FROM ChildCenter c WHERE c.centerName LIKE %:keyword%")
    List<ChildCenter> findByCenterNameContaining(@Param("keyword") String keyword);

    @Query("SELECT new SwProject.businessProcess.auth.web.dto.CenterNameListReq(c.id, c.centerName) " +
            "FROM Manager m JOIN m.childCenter c WHERE m.approvalStatus = :approvalStatus")
    List<CenterNameListReq> findCenterNameListByApprovalStatus(@Param("approvalStatus") Manager.ApprovalStatus approvalStatus);

}
