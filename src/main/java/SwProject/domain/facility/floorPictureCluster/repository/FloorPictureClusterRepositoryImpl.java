package SwProject.domain.facility.floorPictureCluster.repository;

import SwProject.Exception.collections.business.DatabaseNotFoundException;
import SwProject.Exception.message.DbExceptionMessage;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.facilityIntroduction.model.QFacilityIntroduction;
import SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster;
import SwProject.domain.facility.floorPictureCluster.model.QFloorPictureCluster;
import SwProject.domain.facility.floorPicutre.model.FloorPicture;
import SwProject.domain.facility.floorPicutre.model.QFloorPicture;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FloorPictureClusterRepositoryImpl implements FloorPictureClusterRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public FloorPictureCluster getFloorPicutreClusterPk (FacilityIntroduction facilityIntro) {

        QFacilityIntroduction facilityIntroduction = QFacilityIntroduction.facilityIntroduction;
        QFloorPictureCluster floorPictureCluster = QFloorPictureCluster.floorPictureCluster;

        //todo : 한 방 쿼리로 해결하는 방법도 있음.
        FloorPictureCluster fetchedFloorPictureCluster = jpaQueryFactory
                .select(floorPictureCluster)
                //jpaQueryFactory.select(facilityIntroduction):
                //floorPictureCluster 엔티티를 선택하여 조회합니다. 즉, 결과로 얻고 싶은 데이터의 타입을 지정합니다.
                .from(floorPictureCluster)
                //조회를 시작할 기본 엔티티를 manager로 지정합니다. 이는 쿼리의 'FROM' 절에 해당하며,
                // 여기서는 floorPictureCluster 테이블(엔티티)에서 시작해 다른 테이블로 조인하며 정보를 찾아갈 것임을 의미합니다.
                .join(floorPictureCluster.facilityIntroduction, facilityIntroduction) // 수정된 부분
                //floorPictureCluster 엔티티와 연관된 facilityIntroduction 엔티티를 조인합니다.
                // 여기서는 floorPictureCluster 엔티티 내에 정의된 facilityIntroduction 필드(또는 관계)를 통해 facilityIntroduction 엔티티와 조인합니다.
                .where(facilityIntroduction.eq(facilityIntro)) // 예시 조건
                // manager.emailId.eq(authenticatedEmail)) -> 해당 내용 제거 -> 이전에 시설소개 PK 가져오는 부분에서 이미 검증된 내용임
                //조건을 지정합니다. 여기서는 .join 함수에서 조회된 facilityIntroduction이 인자로 받은 facilityIntro와 동일한지 필터링합니다.
                .fetchOne();
                 //쿼리의 결과를 가져옵니다. 여기서는 fetchOne() 메소드를 사용하여 단일 결과를 가져오도록 요청합니다.
                 // 즉, 조건에 맞는 FacilityIntroduction 엔티티가 하나만 있을 것으로 예상하며, 해당 엔티티를 조회하여 반환합니다.

        if (fetchedFloorPictureCluster== null) {
            throw new DatabaseNotFoundException(DbExceptionMessage.FloorPictureDatabaseNotFoundException);
        }

        return fetchedFloorPictureCluster;
    }

    @Override
    public void createFloorPictureList(FloorPictureCluster floorPictureCluster, FloorPicture floorPicture) {
        List<FloorPicture> floorPictureList = floorPictureCluster.getFloorPictureList();
        floorPictureList.add(floorPicture);
    }

    @Override
    public List<FloorPictureCluster> getAllFloorPicutre(FacilityIntroduction facilityIntroduction) {
        QFloorPictureCluster qFloorPictureCluster = QFloorPictureCluster.floorPictureCluster;
        QFloorPicture qFloorPicture = QFloorPicture.floorPicture;

        List<FloorPictureCluster> result = jpaQueryFactory
                .selectFrom(qFloorPictureCluster)
                .leftJoin(qFloorPictureCluster.floorPictureList, qFloorPicture).fetchJoin()
                .where(qFloorPictureCluster.facilityIntroduction.eq(facilityIntroduction))
                .orderBy(qFloorPictureCluster.floor.asc(), qFloorPicture.imageIndex.asc())
                .fetch();

        return result;
    }

    @Override
    public FloorPictureCluster findByFloor(int floor, ChildCenter childCenter) {
        QFloorPictureCluster floorPictureCluster = QFloorPictureCluster.floorPictureCluster;

        return jpaQueryFactory.selectFrom(floorPictureCluster)
                .where(floorPictureCluster.floor.eq(floor)
                        .and(floorPictureCluster.facilityIntroduction.eq(childCenter.getFacilityIntroduction())))
                .fetchOne();
    }


}
    //뭐가 더 최선인지는 아직 모르겠음 나중에 프로파일링 등을 통해 어느것이 더 성능이 좋은지 확인해보기