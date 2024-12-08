package SwProject.domain.center.childCenter.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChildCenter is a Querydsl query type for ChildCenter
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChildCenter extends EntityPathBase<ChildCenter> {

    private static final long serialVersionUID = 1044167591L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChildCenter childCenter = new QChildCenter("childCenter");

    public final StringPath centerName = createString("centerName");

    public final StringPath ceoName = createString("ceoName");

    public final StringPath certificate = createString("certificate");

    public final StringPath detailAddress = createString("detailAddress");

    public final SwProject.domain.facility.facilityIntroduction.model.QFacilityIntroduction facilityIntroduction;

    public final SwProject.domain.greetings.domain.QGreetings greetings;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath phoneNumId = createString("phoneNumId");

    public final StringPath roadAddress = createString("roadAddress");

    public final SwProject.domain.routeInfo.domain.QRouteInfo routeInfo;

    public QChildCenter(String variable) {
        this(ChildCenter.class, forVariable(variable), INITS);
    }

    public QChildCenter(Path<? extends ChildCenter> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChildCenter(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChildCenter(PathMetadata metadata, PathInits inits) {
        this(ChildCenter.class, metadata, inits);
    }

    public QChildCenter(Class<? extends ChildCenter> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.facilityIntroduction = inits.isInitialized("facilityIntroduction") ? new SwProject.domain.facility.facilityIntroduction.model.QFacilityIntroduction(forProperty("facilityIntroduction")) : null;
        this.greetings = inits.isInitialized("greetings") ? new SwProject.domain.greetings.domain.QGreetings(forProperty("greetings")) : null;
        this.routeInfo = inits.isInitialized("routeInfo") ? new SwProject.domain.routeInfo.domain.QRouteInfo(forProperty("routeInfo")) : null;
    }

}

