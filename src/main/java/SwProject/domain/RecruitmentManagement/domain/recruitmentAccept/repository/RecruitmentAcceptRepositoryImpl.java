package SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.repository;

import SwProject.domain.RecruitmentManagement.domain.recruitment.dto.RequestVolunteersByDate;
import SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.model.QRecruitmentAccept;
import SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.model.RecruitmentAccept;
import SwProject.domain.RecruitmentManagement.domain.recruitmentWaiting.dto.RequestAssignmentDto;
import SwProject.domain.center.childCenter.model.ChildCenter;
import SwProject.domain.volunteer.model.Volunteer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static SwProject.domain.RecruitmentManagement.domain.recruitment.model.QRecruitment.recruitment;
import static SwProject.domain.RecruitmentManagement.domain.recruitmentAccept.model.QRecruitmentAccept.recruitmentAccept;

@RequiredArgsConstructor
public class RecruitmentAcceptRepositoryImpl implements RecruitmentAcceptRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Volunteer> findVolunteersByDate(RequestVolunteersByDate request, ChildCenter fetchedChildCenter) {
        return queryFactory
                .select(recruitmentAccept.volunteer)
                .from(recruitmentAccept)
                .join(recruitmentAccept.recruitment, recruitment)
                .where(recruitment.id.eq(request.getRecruitmentId())
                        .and(recruitment.childCenter.eq(fetchedChildCenter)))
                .fetch();
    }

    @Override
    public Optional<RecruitmentAccept> findByRecruitmentIdAndVolunteerId(Long recruitmentId, Long volunteerId) {
        QRecruitmentAccept recruitmentAccept = QRecruitmentAccept.recruitmentAccept;

        RecruitmentAccept result = queryFactory
                .selectFrom(recruitmentAccept)
                .where(recruitmentAccept.recruitment.id.eq(recruitmentId)
                        .and(recruitmentAccept.volunteer.id.eq(volunteerId)))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public boolean isDuplicateRecruitment(Volunteer volunteer, RequestAssignmentDto requestAssignmentDto) {
        return queryFactory
                .selectFrom(recruitmentAccept)
                .join(recruitmentAccept.recruitment, recruitment)
                .where(recruitment.id.eq(requestAssignmentDto.getRecruitmentId())
                        .and(recruitmentAccept.volunteer.eq(volunteer)))
                .fetch().size() > 0;
    }

    @Override
    public int countCompletedRecruitmentsByVolunteer(Volunteer volunteer) {
        int count = queryFactory
                .selectFrom(recruitmentAccept)
                .where(recruitmentAccept.volunteer.eq(volunteer)
                        .and(recruitmentAccept.recruitment.recruitmentEndDate.before(LocalDate.now())))
                .fetch().size();

        return count;
    }


}
