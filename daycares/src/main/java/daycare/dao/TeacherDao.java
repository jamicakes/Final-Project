package daycare.dao;

import daycare.controller.model.StudentData.TeacherResponse;
import daycare.entity.Teacher;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDao extends JpaRepository<Teacher, Long> {

  
 // Set<Teacher> findAllByTeacherIn(Long teacherId);
  
}
