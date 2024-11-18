package SwProject.domain.center.childCenter.repository;

import SwProject.domain.center.childCenter.model.ChildCenter;
import org.springframework.security.core.Authentication;

public interface ChildCenterRepositoryCustom { //더 복잡한 쿼리사용을 위해 커스텀 레포 만듦
    ChildCenter getChildCenterPk(Authentication authentication);
}

