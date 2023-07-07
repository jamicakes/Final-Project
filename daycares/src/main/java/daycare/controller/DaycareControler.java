package daycare.controller;

import daycare.controller.model.DaycareData;
import daycare.controller.model.StudentData;
import daycare.controller.model.TeacherData;
import daycare.service.DaycareService;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/daycare")
@Slf4j
public class DaycareControler {
  
  @Autowired
  private DaycareService daycareService;

  @PostMapping("/daycare")
  @ResponseStatus(code = HttpStatus.CREATED)
  public DaycareData insertDaycare(
      @RequestBody DaycareData daycareData) {
    log.info("Creating daycare {}", daycareData);
    return daycareService.saveDaycare(daycareData);
  } // daycare data /insert daycare
  
  @PutMapping("/daycare/{daycareId}")
  public DaycareData udpateDaycare(@PathVariable Long daycareId, 
      @RequestBody DaycareData daycareData) {
    daycareData.setDaycareId(daycareId);
    log.info("Updating daycare with ID {}", daycareData);
    return daycareService.saveDaycare(daycareData);
  } // udpate daycare
  
  @GetMapping("/daycare")
  public List<DaycareData> retrieveAllDaycares(){
    log.info("Retrieve all daycares called.");
    return daycareService.retrieveAllDaycares();
  } // retrieve ALL daycares
  
  
  @GetMapping("/daycare/{daycareId}")
  public DaycareData retrieveDaycareById(@PathVariable Long daycareId) {
    log.info("Retrieving contributor with ID {}", daycareId);
    return daycareService.retrieveDaycareById(daycareId);
  } // retrieveDaycareById
  
  @DeleteMapping("/daycare")
  public void deleteAllDaycares() {
    log.info("Attempting to delete all daycares."); 
    throw new UnsupportedOperationException("Deleting all daycares is not allowed."); 
    
  }
  
  @DeleteMapping("/daycare/{daycareId}")
  public Map<String, String> deleteDaycareById(@PathVariable Long daycareId) {
    log.info("Deleting daycare with ID={}", daycareId); 
    
    daycareService.deleteDaycareById(daycareId); 
    
    return Map.of("mesage", "Deletion of daycare with ID=" + daycareId + " was successful.");
    
  }
  
  
  //************* TEACHER START *****************
  
  @PostMapping("/daycare/{daycareId}/teacher")
  @ResponseStatus(code = HttpStatus.CREATED)
  public TeacherData saveTeacher(@PathVariable Long daycareId,
      @RequestBody TeacherData teacherData) {
    
    log.info("Creating teacher profile {} for Daycare with ID= {}", daycareId, teacherData);

return daycareService.saveTeacher(daycareId,teacherData);
  }
  
  @GetMapping("/daycare/teacher")
  public List<TeacherData> retrieveAllTeachers() {
    log.info("Retrieve all teachers called.");
    return daycareService.retrieveAllTeachers(); 
  }
  
  @GetMapping("/daycare/{daycareId}/teacher/{teacherId}")
  public TeacherData retrieveTeacherByID(@PathVariable Long teacherId) {
    log.info("Retrieving teacher with ID{}", teacherId); 
    return daycareService.retrieveTeacherById(teacherId); 
  }
  
  
  
  //************* STUDENT START *****************
  
  @PostMapping("/daycare/{daycareId}/teacher/{teacherId}/student")
  @ResponseStatus(code = HttpStatus.CREATED)
  public StudentData saveStudent(@PathVariable Long daycareId, @PathVariable Long teacherId,
      @RequestBody StudentData studentData) {

    log.info("Creating student profile {} for Daycare with ID= {}", daycareId, studentData);

    return daycareService.saveStudent(daycareId, studentData, teacherId);
  }

 // PUT daycare/daycareid/student/studentid/teacherId
  
  @PutMapping("/daycare/student/{studentId}/{teacherId}")
  public StudentData addTeacherToStudentId(@PathVariable Long studentId,
      @PathVariable Long teacherId) {
    
    return daycareService.addTeacherToStudentId(studentId, teacherId); 
  }
  
 
  
  @GetMapping("/daycare/{daycareId}/student")
  public List<StudentData> retrieveAllStudents() {
    log.info("Retrieve all students called");
    return daycareService.retrieveAllStudents();
  }
  
  @GetMapping("/daycare/{daycareId}/student/{studentId}")

  public StudentData retrieveStudentById(@PathVariable Long studentId) {
    log.info("Retrieving student with ID{}", studentId);
    return daycareService.retrieveStudentById(studentId);
  }
  
 //------------------ *********** TEACHER/STUDENT **********************
  
//  @PostMapping("/{teacherId}/{studentId}")
//  public StudentData joinStudentAndTeacher(@PathVariable Long teacherId, Long studentId) {
//    log.info("Displaying teacher ID= {} and student ID= {} relationships.", teacherId, studentId);
//    return daycareService.joinStudentAndTeacher(teacherId, studentId); 
//  }
  
} // main 
