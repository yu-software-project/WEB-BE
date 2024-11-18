package SwProject.domain.RecruitmentManagement.domain.recruitment.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDaysOfWeek is a Querydsl query type for DaysOfWeek
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDaysOfWeek extends BeanPath<DaysOfWeek> {

    private static final long serialVersionUID = 91575107L;

    public static final QDaysOfWeek daysOfWeek = new QDaysOfWeek("daysOfWeek");

    public final BooleanPath friday = createBoolean("friday");

    public final BooleanPath monday = createBoolean("monday");

    public final BooleanPath saturday = createBoolean("saturday");

    public final BooleanPath sunday = createBoolean("sunday");

    public final BooleanPath thursday = createBoolean("thursday");

    public final BooleanPath tuesday = createBoolean("tuesday");

    public final BooleanPath wednesday = createBoolean("wednesday");

    public QDaysOfWeek(String variable) {
        super(DaysOfWeek.class, forVariable(variable));
    }

    public QDaysOfWeek(Path<? extends DaysOfWeek> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDaysOfWeek(PathMetadata metadata) {
        super(DaysOfWeek.class, metadata);
    }

}

