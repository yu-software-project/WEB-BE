package SwProject.domain.yearHistory.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDecadeYear is a Querydsl query type for DecadeYear
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDecadeYear extends EntityPathBase<DecadeYear> {

    private static final long serialVersionUID = 450325674L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDecadeYear decadeYear = new QDecadeYear("decadeYear");

    public final SwProject.domain.center.childCenter.model.QChildCenter childCenter;

    public final NumberPath<Integer> decadeStartYear = createNumber("decadeStartYear", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<YearHistory, QYearHistory> yearList = this.<YearHistory, QYearHistory>createList("yearList", YearHistory.class, QYearHistory.class, PathInits.DIRECT2);

    public QDecadeYear(String variable) {
        this(DecadeYear.class, forVariable(variable), INITS);
    }

    public QDecadeYear(Path<? extends DecadeYear> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDecadeYear(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDecadeYear(PathMetadata metadata, PathInits inits) {
        this(DecadeYear.class, metadata, inits);
    }

    public QDecadeYear(Class<? extends DecadeYear> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.childCenter = inits.isInitialized("childCenter") ? new SwProject.domain.center.childCenter.model.QChildCenter(forProperty("childCenter"), inits.get("childCenter")) : null;
    }

}

