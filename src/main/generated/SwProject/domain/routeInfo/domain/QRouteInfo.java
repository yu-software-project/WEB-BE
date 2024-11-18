package SwProject.domain.routeInfo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRouteInfo is a Querydsl query type for RouteInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRouteInfo extends EntityPathBase<RouteInfo> {

    private static final long serialVersionUID = 1244346357L;

    public static final QRouteInfo routeInfo = new QRouteInfo("routeInfo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public QRouteInfo(String variable) {
        super(RouteInfo.class, forVariable(variable));
    }

    public QRouteInfo(Path<? extends RouteInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRouteInfo(PathMetadata metadata) {
        super(RouteInfo.class, metadata);
    }

}

