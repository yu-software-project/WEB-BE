package SwProject.domain.volunteer.repository;

import SwProject.domain.volunteer.model.Volunteer;
import org.springframework.security.core.Authentication;

public interface VolunteerRepositoryCustom {
    Volunteer getVolunteerPk(Authentication authentication);
    Volunteer findVolunteerById(Long id);
}
