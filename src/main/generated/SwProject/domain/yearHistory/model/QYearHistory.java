package SwProject.domain.yearHistory.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QYearHistory is a Querydsl query type for YearHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QYearHistory extends EntityPathBase<YearHistory> {

    private static final long serialVersionUID = 1935556682L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QYearHistory yearHistory = new QYearHistory("yearHistory");

    public final QDecadeYear decadeYear;

    public final NumberPath<Integer> displayIndex = createNumber("displayIndex", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QYearHistory(String variable) {
        this(YearHistory.class, forVariable(variable), INITS);
    }

    public QYearHistory(Path<? extends YearHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QYearHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QYearHistory(PathMetadata metadata, PathInits inits) {
        this(YearHistory.class, metadata, inits);
    }

    public QYearHistory(Class<? extends YearHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.decadeYear = inits.isInitialized("decadeYear") ? new QDecadeYear(forProperty("decadeYear"), inits.get("decadeYear")) : null;
    }

}

