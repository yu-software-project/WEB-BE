package SwProject.domain.facility.facilityIntroduction.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacilityIntroduction is a Querydsl query type for FacilityIntroduction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacilityIntroduction extends EntityPathBase<FacilityIntroduction> {

    private static final long serialVersionUID = -1197940487L;

    public static final QFacilityIntroduction facilityIntroduction = new QFacilityIntroduction("facilityIntroduction");

    public final ListPath<SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster, SwProject.domain.facility.floorPictureCluster.model.QFloorPictureCluster> floorPictureClusters = this.<SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster, SwProject.domain.facility.floorPictureCluster.model.QFloorPictureCluster>createList("floorPictureClusters", SwProject.domain.facility.floorPictureCluster.model.FloorPictureCluster.class, SwProject.domain.facility.floorPictureCluster.model.QFloorPictureCluster.class, PathInits.DIRECT2);

    public final ListPath<SwProject.domain.facility.floorSize.model.FloorSize, SwProject.domain.facility.floorSize.model.QFloorSize> floorSizes = this.<SwProject.domain.facility.floorSize.model.FloorSize, SwProject.domain.facility.floorSize.model.QFloorSize>createList("floorSizes", SwProject.domain.facility.floorSize.model.FloorSize.class, SwProject.domain.facility.floorSize.model.QFloorSize.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> totalArea = createNumber("totalArea", Integer.class);

    public QFacilityIntroduction(String variable) {
        super(FacilityIntroduction.class, forVariable(variable));
    }

    public QFacilityIntroduction(Path<? extends FacilityIntroduction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFacilityIntroduction(PathMetadata metadata) {
        super(FacilityIntroduction.class, metadata);
    }

}

