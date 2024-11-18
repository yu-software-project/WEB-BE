package SwProject.domain.facility.floorPicutre.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFloorPicture is a Querydsl query type for FloorPicture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFloorPicture extends EntityPathBase<FloorPicture> {

    private static final long serialVersionUID = 1137429371L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFloorPicture floorPicture = new QFloorPicture("floorPicture");

    public final NumberPath<Integer> floor = createNumber("floor", Integer.class);

    public final SwProject.domain.facility.floorPictureCluster.model.QFloorPictureCluster floorPictureCluster;

    public final NumberPath<Long> floorPictureId = createNumber("floorPictureId", Long.class);

    public final NumberPath<Integer> imageIndex = createNumber("imageIndex", Integer.class);

    public final StringPath pictureUrl = createString("pictureUrl");

    public final StringPath purpose = createString("purpose");

    public QFloorPicture(String variable) {
        this(FloorPicture.class, forVariable(variable), INITS);
    }

    public QFloorPicture(Path<? extends FloorPicture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFloorPicture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFloorPicture(PathMetadata metadata, PathInits inits) {
        this(FloorPicture.class, metadata, inits);
    }

    public QFloorPicture(Class<? extends FloorPicture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.floorPictureCluster = inits.isInitialized("floorPictureCluster") ? new SwProject.domain.facility.floorPictureCluster.model.QFloorPictureCluster(forProperty("floorPictureCluster"), inits.get("floorPictureCluster")) : null;
    }

}

