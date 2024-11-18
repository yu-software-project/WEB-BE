package SwProject.domain.center.likeChildCenter.repository;

import SwProject.domain.center.likeChildCenter.model.LikeCenter;
import SwProject.domain.volunteer.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeCenterRepository extends JpaRepository<LikeCenter, CrudRepository>, LikeCenterRepositoryCustom {
    List<LikeCenter> findByVolunteer(Volunteer volunteer);
}
