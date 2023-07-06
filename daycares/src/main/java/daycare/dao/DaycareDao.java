package daycare.dao;

import daycare.entity.Daycare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaycareDao extends JpaRepository<Daycare, Long> {

}
