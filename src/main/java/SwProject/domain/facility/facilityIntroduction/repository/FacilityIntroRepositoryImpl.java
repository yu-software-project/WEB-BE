package SwProject.domain.facility.facilityIntroduction.repository;

import SwProject.Exception.collections.business.DatabaseNotFoundException;
import SwProject.Exception.message.DbExceptionMessage;
import SwProject.domain.center.childCenter.model.QChildCenter;
import SwProject.domain.facility.facilityIntroduction.model.FacilityIntroduction;
import SwProject.domain.facility.facilityIntroduction.model.QFacilityIntroduction;
import SwProject.domain.facility.floorSize.model.FloorSize;
import SwProject.domain.manager.model.QManager;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
public class FacilityIntroRepositoryImpl implements FacilityIntroRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public FacilityIntroduction getFacilityPk(Authentication authentication) {

        QManager qManager = QManager.manager;
        QChildCenter childCenter = QChildCenter.childCenter;
        QFacilityIntroduction facilityIntroduction = QFacilityIntroduction.facilityIntroduction; // 가정한 시설소개 엔티티의 Q 타입

        FacilityIntroduction fetchedFacilityIntroduction = jpaQueryFactory
                .select(facilityIntroduction)
                //jpaQueryFactory.select(facilityIntroduction):
                //facilityIntroduction 엔티티를 선택하여 조회합니다. 즉, 결과로 얻고 싶은 데이터의 타입을 지정합니다.
                .from(qManager)
                //조회를 시작할 기본 엔티티를 manager로 지정합니다. 이는 쿼리의 'FROM' 절에 해당하며,
                // 여기서는 manager 테이블(엔티티)에서 시작해 다른 테이블로 조인하며 정보를 찾아갈 것임을 의미합니다.
                .join(qManager.childCenter, childCenter)
                //manager 엔티티와 연관된 childCenter 엔티티를 조인합니다.
                // 여기서는 manager 엔티티 내에 정의된 childCenter 필드(또는 관계)를 통해 childCenter 엔티티와 조인합니다.
                .join(childCenter.facilityIntroduction, facilityIntroduction)
                .where(qManager.emailId.eq(authentication.getName()))
                //조건을 지정합니다. 여기서는 manager 엔티티의 emailId 필드가 인자로 받은 email 값과 동일한 경우만을 필터링합니다.
                // 즉, 특정 이메일 주소를 가진 관리자에 대한 정보만 조회하겠다는 의미입니다.
                .fetchOne();
                //쿼리의 결과를 가져옵니다. 여기서는 fetchOne() 메소드를 사용하여 단일 결과를 가져오도록 요청합니다.
                // 즉, 조건에 맞는 FacilityIntroduction 엔티티가 하나만 있을 것으로 예상하며, 해당 엔티티를 조회하여 반환합니다.

        if (fetchedFacilityIntroduction == null) {
            throw new DatabaseNotFoundException(DbExceptionMessage.FacilityDatabaseNotFoundException);
        }

        return fetchedFacilityIntroduction;
    }


    @Override
    public void updateFacilityFloorSizeList(FacilityIntroduction facilityIntroduction, FloorSize floorSize) {
        //양방향 매핑을 위해 시설소개 DB에도 변경사항 업데이트

        //해당 DB는 영속상태 이므로 JPA에서 알아서 업데이트 해줌
        facilityIntroduction.getFloorSizes().add(floorSize);
    }
}


/*
* QClass와 엔티티 클래스는 많은 장점을 공유하고 있지만 그럼에 QClass를 사용하는 이유는 다음과 같다.

QClass는 엔티티 속성을 정적인 방식으로 표현하므로 IDE의 자동 완성 기능을 활용할 수 있고, 속성 이름을 직접 기억하거나 확인하지 않아도 된다는 장점을 가지고 있다. 
QClass는 엔티티 속성의 타입을 정확하게 표현하므로, 타입에 맞지 않는 연산이나 비교를 시도하면 컴파일러가 오류를 감지할 수 있다.
*
* QClass는 엔티티 클래스의 확장으로 생각할 수 있다. 엔티티 클래스는 데이터베이스 테이블의 매핑을 담당하고, QClass는 쿼리 작성을 위한 편의성과 안전성을 제공을 해주면서 유지보수의 편의성 및 실수 방지를 하지 않도록 해준다고 생각한다.
*
* */