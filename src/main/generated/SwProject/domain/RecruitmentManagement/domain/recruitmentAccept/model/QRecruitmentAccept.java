package SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecruitmentAccept is a Querydsl query type for RecruitmentAccept
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitmentAccept extends EntityPathBase<RecruitmentAccept> {

    private static final long serialVersionUID = -894291797L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecruitmentAccept recruitmentAccept = new QRecruitmentAccept("recruitmentAccept");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SwProject.domain.RecruitmentManagement.domain.recruitment.model.QRecruitment recruitment;

    public final ListPath<java.time.LocalDate, DatePath<java.time.LocalDate>> recruitmentDates = this.<java.time.LocalDate, DatePath<java.time.LocalDate>>createList("recruitmentDates", java.time.LocalDate.class, DatePath.class, PathInits.DIRECT2);

    public final SwProject.domain.volunteer.model.QVolunteer volunteer;

    public QRecruitmentAccept(String variable) {
        this(RecruitmentAccept.class, forVariable(variable), INITS);
    }

    public QRecruitmentAccept(Path<? extends RecruitmentAccept> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecruitmentAccept(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecruitmentAccept(PathMetadata metadata, PathInits inits) {
        this(RecruitmentAccept.class, metadata, inits);
    }

    public QRecruitmentAccept(Class<? extends RecruitmentAccept> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recruitment = inits.isInitialized("recruitment") ? new SwProject.domain.RecruitmentManagement.domain.recruitment.model.QRecruitment(forProperty("recruitment"), inits.get("recruitment")) : null;
        this.volunteer = inits.isInitialized("volunteer") ? new SwProject.domain.volunteer.model.QVolunteer(forProperty("volunteer")) : null;
    }

}

