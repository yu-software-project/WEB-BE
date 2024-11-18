package SwProject.domain.RecruitmentManagement.domain.recruitment.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecruitment is a Querydsl query type for Recruitment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitment extends EntityPathBase<Recruitment> {

    private static final long serialVersionUID = 1510050843L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecruitment recruitment = new QRecruitment("recruitment");

    public final SwProject.domain.center.childCenter.model.QChildCenter childCenter;

    public final NumberPath<Integer> currentApplicants = createNumber("currentApplicants", Integer.class);

    public final StringPath detailInfo = createString("detailInfo");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> endTime = createTime("endTime", java.time.LocalTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isRepeatedDate = createBoolean("isRepeatedDate");

    public final BooleanPath isTimeExits = createBoolean("isTimeExits");

    public final StringPath name = createString("name");

    public final DatePath<java.time.LocalDate> recruitmentEndDate = createDate("recruitmentEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> recruitmentStartDate = createDate("recruitmentStartDate", java.time.LocalDate.class);

    public final ListPath<SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model.RecruitmentWaiting, SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model.QRecruitmentWaiting> recruitmentWaitings = this.<SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model.RecruitmentWaiting, SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model.QRecruitmentWaiting>createList("recruitmentWaitings", SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model.RecruitmentWaiting.class, SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model.QRecruitmentWaiting.class, PathInits.DIRECT2);

    public final QDaysOfWeek repeatedDays;

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    public final NumberPath<Integer> totalApplicants = createNumber("totalApplicants", Integer.class);

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public QRecruitment(String variable) {
        this(Recruitment.class, forVariable(variable), INITS);
    }

    public QRecruitment(Path<? extends Recruitment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecruitment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecruitment(PathMetadata metadata, PathInits inits) {
        this(Recruitment.class, metadata, inits);
    }

    public QRecruitment(Class<? extends Recruitment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.childCenter = inits.isInitialized("childCenter") ? new SwProject.domain.center.childCenter.model.QChildCenter(forProperty("childCenter"), inits.get("childCenter")) : null;
        this.repeatedDays = inits.isInitialized("repeatedDays") ? new QDaysOfWeek(forProperty("repeatedDays")) : null;
    }

}

