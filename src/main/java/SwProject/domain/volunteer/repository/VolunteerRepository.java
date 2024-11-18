package SwProject.domain.volunteer.repository;

import SwProject.domain.volunteer.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long>, VolunteerRepositoryCustom {
    Optional<Volunteer> findByEmailId(String emailId);
    Optional<Volunteer> findByPhoneNum(String phoneNum);
}

