package SwProject.domain.volunteer.repository;

import SwProject.Exception.collections.business.DatabaseNotFoundException;
import SwProject.domain.volunteer.model.QVolunteer;
import SwProject.domain.volunteer.model.Volunteer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import static SwProject.Exception.message.DbExceptionMessage.VolunteerDatabaseNotFoundException;

@RequiredArgsConstructor
public class VolunteerRepositoryImpl implements VolunteerRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Volunteer getVolunteerPk(Authentication authentication) {

        QVolunteer qVolunteer = QVolunteer.volunteer;

        Volunteer fetchedVoluteer = jpaQueryFactory
                .select(qVolunteer)
                .from(qVolunteer)
                .where(qVolunteer.phoneNum.eq(authentication.getName()))
                .fetchOne();

        if(fetchedVoluteer == null) {
            throw new DatabaseNotFoundException(VolunteerDatabaseNotFoundException);
        }

        return fetchedVoluteer;
    }


    @Override
    public Volunteer findVolunteerById(Long id) {
        QVolunteer qVolunteer = QVolunteer.volunteer;

        Volunteer fetchedVolunteer = jpaQueryFactory
                .selectFrom(qVolunteer)
                .where(qVolunteer.id.eq(id))
                .fetchOne();

        if (fetchedVolunteer == null) {
            throw new DatabaseNotFoundException(VolunteerDatabaseNotFoundException);
        }

        return fetchedVolunteer;
    }
}
