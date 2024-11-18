package SwProject.domain.center.childCenter.repository;

import SwProject.Exception.collections.business.DatabaseNotFoundException;
import SwProject.Exception.message.DbExceptionMessage;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.center.childCenter.model.QChildCenter;
import SwProject.domain.manager.model.QManager;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
public class ChildCenterRepositoryImpl implements ChildCenterRepositoryCustom {
    //QueryDsl의 핵심 컴포넌트 -> 쿼리를 생성하고 실행할 수 있게 함.
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ChildCenter getChildCenterPk(Authentication authentication) {
        //Q클래스 : QueryDsl이 자동생성한 Q타입 클래스
        QManager qManager = QManager.manager;
        QChildCenter qChildCenter = QChildCenter.childCenter;

        ChildCenter fetchedChildCenter = jpaQueryFactory
                .select(qChildCenter) //조회할 대상 엔티티
                .from(qManager) //조회를 시작할 엔티티
                .join(qManager.childCenter, qChildCenter) //Manger 엔티티와 연관된 QchildCenter 엔티티 조인
                .where(qManager.emailId.eq(authentication.getName())) //조건 지정
                .fetchOne();

        //이 코드는 현재 인증된 사용자의 이메일(authentication.getName())을 사용하여 Manager 엔티티를 조회하고,
        // 해당 매니저와 연관된 ChildCenter 엔티티를 조인하여 반환한다.

        if (fetchedChildCenter == null) {
            throw new DatabaseNotFoundException(DbExceptionMessage.ChildCenterDatabaseNotFoundException);
        }


        return fetchedChildCenter;

    }
}
