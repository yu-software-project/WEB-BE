package SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecruitmentWaiting is a Querydsl query type for RecruitmentWaiting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitmentWaiting extends EntityPathBase<RecruitmentWaiting> {

    private static final long serialVersionUID = 2019108233L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecruitmentWaiting recruitmentWaiting = new QRecruitmentWaiting("recruitmentWaiting");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SwProject.domain.RecruitmentManagement.domain.recruitment.model.QRecruitment recruitment;

    public final StringPath selfIntroduction = createString("selfIntroduction");

    public final SwProject.domain.volunteer.model.QVolunteer volunteer;

    public QRecruitmentWaiting(String variable) {
        this(RecruitmentWaiting.class, forVariable(variable), INITS);
    }

    public QRecruitmentWaiting(Path<? extends RecruitmentWaiting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecruitmentWaiting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecruitmentWaiting(PathMetadata metadata, PathInits inits) {
        this(RecruitmentWaiting.class, metadata, inits);
    }

    public QRecruitmentWaiting(Class<? extends RecruitmentWaiting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recruitment = inits.isInitialized("recruitment") ? new SwProject.domain.RecruitmentManagement.domain.recruitment.model.QRecruitment(forProperty("recruitment"), inits.get("recruitment")) : null;
        this.volunteer = inits.isInitialized("volunteer") ? new SwProject.domain.volunteer.model.QVolunteer(forProperty("volunteer")) : null;
    }

}

