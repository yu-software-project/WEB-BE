package SwProject.domain.facility.floorSize.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFloorSize is a Querydsl query type for FloorSize
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFloorSize extends EntityPathBase<FloorSize> {

    private static final long serialVersionUID = -1638879507L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFloorSize floorSize = new QFloorSize("floorSize");

    public final NumberPath<Integer> area = createNumber("area", Integer.class);

    public final NumberPath<Integer> displayIndex = createNumber("displayIndex", Integer.class);

    public final SwProject.domain.facility.facilityIntroduction.model.QFacilityIntroduction facilityIntroduction;

    public final StringPath floor = createString("floor");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath purpose = createString("purpose");

    public final StringPath remark = createString("remark");

    public QFloorSize(String variable) {
        this(FloorSize.class, forVariable(variable), INITS);
    }

    public QFloorSize(Path<? extends FloorSize> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFloorSize(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFloorSize(PathMetadata metadata, PathInits inits) {
        this(FloorSize.class, metadata, inits);
    }

    public QFloorSize(Class<? extends FloorSize> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.facilityIntroduction = inits.isInitialized("facilityIntroduction") ? new SwProject.domain.facility.facilityIntroduction.model.QFacilityIntroduction(forProperty("facilityIntroduction")) : null;
    }

}

