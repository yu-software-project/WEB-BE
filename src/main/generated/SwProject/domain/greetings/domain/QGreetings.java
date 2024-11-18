package SwProject.domain.greetings.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGreetings is a Querydsl query type for Greetings
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGreetings extends EntityPathBase<Greetings> {

    private static final long serialVersionUID = 1877177941L;

    public static final QGreetings greetings = new QGreetings("greetings");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final StringPath pictureUrl = createString("pictureUrl");

    public QGreetings(String variable) {
        super(Greetings.class, forVariable(variable));
    }

    public QGreetings(Path<? extends Greetings> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGreetings(PathMetadata metadata) {
        super(Greetings.class, metadata);
    }

}

