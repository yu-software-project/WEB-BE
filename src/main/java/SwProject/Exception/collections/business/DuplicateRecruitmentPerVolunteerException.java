package SwProject.Exception.collections.business;

import static SwProject.Exception.message.CommonExceptionMessage.DuplicateRecruitmentPerVolunteer;

public class DuplicateRecruitmentPerVolunteerException extends BusinessException {

    public DuplicateRecruitmentPerVolunteerException() {
        super(DuplicateRecruitmentPerVolunteer);
    }
}
