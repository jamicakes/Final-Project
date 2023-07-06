package daycare.controller.model;

import daycare.entity.Daycare;
import daycare.entity.Student;
import daycare.entity.Teacher;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
public class DaycareData {

  private Long daycareId;
  private String daycareName;
  private String daycareAddress;
  private String daycareCity;
  private String daycareState;
  private String daycareZip;
  private String daycarePhone;

  public DaycareData(Daycare daycare) {
    daycareId = daycare.getDaycareId();
    daycareName = daycare.getDaycareName();
    daycareAddress = daycare.getDaycareAddress();
    daycareCity = daycare.getDaycareCity();
    daycareState = daycare.getDaycareState();
    daycareZip = daycare.getDaycareZip();
    daycarePhone = daycare.getDaycarePhone();

    for (Teacher teacher : daycare.getTeachers()) {
      teachers.add(new TeacherResponse(teacher));
    }
  }

  @Value(value = "student")
  private Set<StudentResponse> student = new HashSet<>();
  private Set<String> amenities = new HashSet<>();
  private Set<TeacherResponse> teachers = new HashSet<>();

  @Data
  static class StudentResponse {
    private Long studentId;
    private String studentName;
    private String studentEmail;
   
    private Set<String> teacher = new HashSet<>(); // SET STRING might be better as SET<TEACHER> if
                                                   // it's not working try that.

    StudentResponse(Student student) {
      studentId = student.getStudentId();
      studentName = student.getStudentName();
      studentEmail = student.getStudentEmail();

    }

  }

  @Data
  @NoArgsConstructor
  static class TeacherResponse {
    private Long teacherId;
    private String teacherFirstName;
    private String teacherLastName;
    private String teacherEmail;
    private Set<String> students = new HashSet<>(); // again SET STRING may be better as
                                                    // SET<STUDENT>
    private Set<Daycare> daycares = new HashSet<>();

    TeacherResponse(Teacher teacher) {
      teacherId = teacher.getTeacherId();
      teacherFirstName = teacher.getTeacherFirstName();
      teacherLastName = teacher.getTeacherFirstName();
      teacherEmail = teacher.getTeacherEmail();

      for (Student student : teacher.getStudents()) {
        students.add(student.getStudentName());
      }
    }
  }
  
 // private Set<Daycare> daycares = new HashSet<>(); this seems self referential but I don't remember if I need it or not. 
  
  
  
}
