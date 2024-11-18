package SwProject.domain.scrapRecruitment.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScrapRecruitment is a Querydsl query type for ScrapRecruitment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScrapRecruitment extends EntityPathBase<ScrapRecruitment> {

    private static final long serialVersionUID = -1089205886L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScrapRecruitment scrapRecruitment = new QScrapRecruitment("scrapRecruitment");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SwProject.domain.RecruitmentManagement.domain.recruitment.model.QRecruitment recruitment;

    public final SwProject.domain.volunteer.model.QVolunteer volunteer;

    public QScrapRecruitment(String variable) {
        this(ScrapRecruitment.class, forVariable(variable), INITS);
    }

    public QScrapRecruitment(Path<? extends ScrapRecruitment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScrapRecruitment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScrapRecruitment(PathMetadata metadata, PathInits inits) {
        this(ScrapRecruitment.class, metadata, inits);
    }

    public QScrapRecruitment(Class<? extends ScrapRecruitment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recruitment = inits.isInitialized("recruitment") ? new SwProject.domain.RecruitmentManagement.domain.recruitment.model.QRecruitment(forProperty("recruitment"), inits.get("recruitment")) : null;
        this.volunteer = inits.isInitialized("volunteer") ? new SwProject.domain.volunteer.model.QVolunteer(forProperty("volunteer")) : null;
    }

}

