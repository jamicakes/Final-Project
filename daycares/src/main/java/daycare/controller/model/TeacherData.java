package daycare.controller.model;

import daycare.entity.Amenity;
import daycare.entity.Daycare;
import daycare.entity.Student;
import daycare.entity.Teacher;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherData {
  
  private Long teacherId; 
  private String teacherFirstName; 
  private String teacherLastName;
  private String teacherEmail;
  private Set<StudentResponse> students = new HashSet<>();
  private Set<DaycareResponse> daycares = new HashSet<>();
  
  public TeacherData(Teacher teacher) {
    teacherId = teacher.getTeacherId();
    teacherFirstName = teacher.getTeacherFirstName();
    teacherLastName = teacher.getTeacherLastName();
    teacherEmail = teacher.getTeacherEmail();
    
    for (Student student : teacher.getStudents()) {
      students.add(new StudentResponse(student)); 
    }
    
    for (Daycare daycare : teacher.getDaycare()) {
      daycares.add(new DaycareResponse(daycare));
    }
  }
  
  @Data
  @NoArgsConstructor
  static class StudentResponse {
    private Long studentId;
    private String studentName;
    private String studentEmail;
    
    private Set<String> daycare = new HashSet<>();

    StudentResponse(Student student) {
      studentId = student.getStudentId();
      studentName = student.getStudentName();
      studentEmail = student.getStudentEmail();
    }
  }
  
  @Data
  @NoArgsConstructor
  static class DaycareResponse {
    private Long daycareId;
    private String daycareName;
    private String daycareAddress;
    private String daycareCity;
    private String daycareState;
    private String daycareZip;
    private String daycarePhone;
    
    private Set<Amenity> amenities = new HashSet<>();
    
    DaycareResponse(Daycare daycare) {
      daycareId = daycare.getDaycareId();
      daycareName = daycare.getDaycareName();
      daycareAddress = daycare.getDaycareAddress();
      daycareCity = daycare.getDaycareCity();
      daycareState = daycare.getDaycareState();
      daycareZip = daycare.getDaycareZip();
      daycarePhone = daycare.getDaycarePhone();
      amenities = daycare.getAmenities();
    }
  }
}