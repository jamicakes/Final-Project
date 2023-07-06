package daycare.controller.model;

import daycare.entity.Daycare;
import daycare.entity.Student;
import daycare.entity.Teacher;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentData {

  private Long studentId;
  private String studentName;
  private String studentEmail;
  private Set<TeacherResponse> teachers = new HashSet<>();
  private StudentDaycare daycare;

  public StudentData(Student student) {
    studentId = student.getStudentId();
    studentName = student.getStudentName();
    studentEmail = student.getStudentEmail();

    for (Teacher teacher : student.getTeachers()) {
      teachers.add(new TeacherResponse(teacher));
    }
  }

  @Data
  @NoArgsConstructor
  public static class TeacherResponse {
    private Long teacherId;
    private String teacherFirstName;
    private String teacherLastName;
    private String teacherEmail;
    private Set<Daycare> daycares = new HashSet<>(); 

     TeacherResponse(Teacher teacher) {
      teacherId = teacher.getTeacherId();
      teacherFirstName = teacher.getTeacherFirstName();
      teacherLastName = teacher.getTeacherLastName();
      teacherEmail = teacher.getTeacherEmail();
      
   
    }
  }

  @Data
  @NoArgsConstructor
  public static class StudentDaycare {
    private Long daycareId;
    private String daycareName;
    private String daycareAddress;
    private String daycareCity;
    private String daycareState;
    private String daycareZip;
    private String daycarePhone;
    
    public StudentDaycare(Daycare daycare) {
     daycareId = daycare.getDaycareId();
     daycareName = daycare.getDaycareName();
     daycareAddress = daycare.getDaycareAddress();
     daycareCity = daycare.getDaycareCity();
     daycareState = daycare.getDaycareState();
     daycareZip = daycare.getDaycareZip();
     daycarePhone = daycare.getDaycarePhone();
     
     
    }
  }
}
