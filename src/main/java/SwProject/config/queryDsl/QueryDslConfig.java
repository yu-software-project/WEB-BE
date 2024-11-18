package SwProject.config.queryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {

    @PersistenceContext // Spring Boot에 경우 Bean에 EntityManager가 자동으로 등록되어있어서 @persistenceContext 어노테이션으로 사용할 수 있다.
    EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }

}

// JPAQueryFactory가 JPA 쿼리를 실행하기 위해 EntityManager를 내부에서 사용해야 하기에, JPAQueryFactory 생성자 값에 entityManager 객체를 넣어 반환한다.

// EntityManager는 JPA의 핵심 인터페이스로, 엔티티를 데이터베이스에 생성, 수정, 삭제, 조회 등의 작업을 수행하는 역할을 합니다. 이는 데이터베이스와의 상호작용을 관리하며, 트랜잭션 관리와 쿼리 실행 등의 역할을 담당합니다.
// JPAQueryFactory는 QueryDSL 라이브러리의 핵심 클래스로, 타입-세이프한 쿼리를 생성하는 역할을 합니다. 이는 JPA 쿼리를 더욱 간결하고 가독성 좋게 작성할 수 있도록 도와줍니다.

// 즉, JPAQueryFactory를 통해 생성한 쿼리는 EntityManager를 통해 데이터베이스와 상호 작용하게 됩니다. 이렇게 분리된 역할을 통해 개발자는 쿼리의 생성과 실행을 더욱 효과적으로 관리할 수 있습니다.
