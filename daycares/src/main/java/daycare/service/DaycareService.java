package daycare.service;

import daycare.controller.model.DaycareData;
import daycare.controller.model.StudentData;
import daycare.controller.model.TeacherData;
import daycare.dao.AmenityDao;
import daycare.dao.DaycareDao;
import daycare.dao.StudentDao;
import daycare.dao.TeacherDao;
import daycare.entity.Amenity;
import daycare.entity.Daycare;
import daycare.entity.Student;
import daycare.entity.Teacher;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DaycareService {
  @Autowired
  private DaycareDao daycareDao;

  @Autowired
  private AmenityDao amenityDao;

  @Autowired
  private TeacherDao teacherDao;
  
  @Autowired
  private StudentDao studentDao;
  
  
  // ************ Start DayCare ********** ---------------------

  @Transactional(readOnly = false)
  public DaycareData saveDaycare(DaycareData daycareData) {
    Long daycareId = daycareData.getDaycareId();
    Daycare daycare = findOrCreateDaycare(daycareId);

    Set<Amenity> amenities = amenityDao.findAllByAmenityIn(daycareData.getAmenities());

    for (Amenity amenity : amenities) {
      amenity.getDaycares().add(daycare);
      daycare.getAmenities().add(amenity);
    }
    
    setFieldsInDaycare(daycare, daycareData);
    return new DaycareData(daycareDao.save(daycare));
  }

  private void setFieldsInDaycare(Daycare daycare, DaycareData daycareData) {
    daycare.setDaycareAddress(daycareData.getDaycareAddress());
    daycare.setDaycareCity(daycareData.getDaycareCity());
    daycare.setDaycareState(daycareData.getDaycareState());
    daycare.setDaycareZip(daycareData.getDaycareZip());
    daycare.setDaycareId(daycareData.getDaycareId());
    daycare.setDaycareName(daycareData.getDaycareName());
    daycare.setDaycarePhone(daycareData.getDaycarePhone());

  }

  private Daycare findOrCreateDaycare(Long daycareId) {
    Daycare daycare;

    if (Objects.isNull(daycareId)) {
      daycare = new Daycare();
    } else {
      daycare = findDaycareById(daycareId);
    }
    return daycare;
  }

  private Daycare findDaycareById(Long daycareId) {
    return daycareDao.findById(daycareId).orElseThrow(
        () -> new NoSuchElementException("Daycare with ID=" + daycareId + " was not found."));
  }

  @Transactional(readOnly = true)
  public List<DaycareData> retrieveAllDaycares() {
    List<Daycare> daycares = daycareDao.findAll();
    List<DaycareData> response = new LinkedList<>();

    for (Daycare daycare : daycares) {
      response.add(new DaycareData(daycare));
    }

    return response;
  }

  @Transactional(readOnly = true)
  public DaycareData retrieveDaycareById(Long daycareId) {
    Daycare daycare = findDaycareById(daycareId);
    return new DaycareData(daycare);
  }

  @Transactional(readOnly = false)
  public void deleteDaycareById(Long daycareId) {
    Daycare daycare = findDaycareById(daycareId);
    daycareDao.delete(daycare);
  

  

  } // end daycare save info / delete info / find by info 
  
  //-----------------------------------------------------------------------------------------------

  // ******TEACHER START ********* -----------------------------------
  
  public TeacherData saveTeacher(Long daycareId, TeacherData teacherData) {
    Daycare daycare = findDaycareById(daycareId);

    Teacher teacher = findOrCreateTeacher(teacherData.getTeacherId());
    setTeacherFields(teacher, teacherData);

    teacher.setDaycare(daycare);

    Teacher dbTeacher = teacherDao.save(teacher);
    return new TeacherData(dbTeacher);
  }
  
  private void setStudentFields(Student student, StudentData studentData) {
   student.setStudentId(studentData.getStudentId()); 
   student.setStudentName(studentData.getStudentName());
   student.setStudentEmail(studentData.getStudentEmail());
   

   
  }

  private void setTeacherFields(Teacher teacher, TeacherData teacherData) {
   teacher.setTeacherFirstName(teacherData.getTeacherFirstName());
    teacher.setTeacherLastName(teacherData.getTeacherLastName());
    teacher.setTeacherEmail(teacherData.getTeacherEmail());
    teacher.setTeacherId(teacherData.getTeacherId());
    
  }

  private Teacher findOrCreateTeacher(Long teacherId) {
    Teacher teacher;

    if (Objects.isNull(teacherId)) {
      teacher = new Teacher();
    } else {
      teacher = findTeacherById(teacherId);
    }
    return teacher;
  }


  private Teacher findTeacherById(Long teacherId) {
    return teacherDao.findById(teacherId).orElseThrow(
        () -> new NoSuchElementException("Teacher with ID=" + teacherId + " does not exist."));
  }
  
  @Transactional(readOnly = true)
  public List<TeacherData> retrieveAllTeachers() {
    List<Teacher> teachers = teacherDao.findAll();
    List<TeacherData> response = new LinkedList<>();

    for (Teacher teacher : teachers) {
      response.add(new TeacherData(teacher));
    }

    return response;
  }
  
  @Transactional(readOnly = true)
  public TeacherData retrieveTeacherById(Long teacherId) {
 Teacher teacher = findTeacherById(teacherId); 
 return new TeacherData(teacher); 
  }
  

  
  // ************ START STUDENT ****************************

  @Transactional(readOnly = false)
  public StudentData saveStudent(Long daycareId, StudentData studentData, Long teacherId) {
    Daycare daycare = findDaycareById(daycareId); 
    Teacher teacher = findTeacherById(teacherId);
    

        
    Student student = findOrCreateStudent(studentData.getStudentId());
    setStudentFields(student, studentData); 
    
    student.setDaycare(daycare);
    daycare.getStudents().add(student); 
    Set<Teacher> teachers = new HashSet<>();
    teachers.add(teacher); 
    student.setTeachers(teachers);
    teacher.getStudents().add(student);


    
    Student dbStudent = studentDao.save(student); 
    return new StudentData(dbStudent); 
  }

  private Student findOrCreateStudent(Long studentId) {
    Student student;
    if (Objects.isNull(studentId)) {
      student = new Student();
    } else {
      student = findStudentById(studentId);
    }
    return student;
  }

  private Student findStudentById(Long studentId) {
    return studentDao.findById(studentId)
        .orElseThrow(() -> new NoSuchElementException("Student with ID=" + studentId + " does not exist."));
  }

  @Transactional(readOnly = true)
  public List<StudentData> retrieveAllStudents() {
   List<Student> students = studentDao.findAll();
   List<StudentData> response = new LinkedList<>();
   
   for (Student student : students) {
     response.add(new StudentData(student));
   }
   return response;
  }

  @Transactional(readOnly = true)
  public StudentData retrieveStudentById(Long studentId) {
   Student student = findStudentById(studentId); 
   return new StudentData(student); 
  }

  public StudentData joinStudentAndTeacher(Long teacherId, Long studentId) {
    // TODO Auto-generated method stub
    return null;
  }

  public StudentData addTeacherToStudentId(Long studentId, Long teacherId) {

    Teacher teacher = findTeacherById(teacherId);
    Student student = findOrCreateStudent(studentId);
    // setStudentFields(student, studentData);
    Set<Teacher> teachers = new HashSet<>();
    teachers.add(teacher); 
    student.setTeachers(teachers);
    teacher.getStudents().add(student);
    Student dbStudent = studentDao.save(student);
    return new StudentData(dbStudent);

  }



}
