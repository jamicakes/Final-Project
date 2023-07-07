package daycare.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Teacher {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long teacherId; 
  private String teacherFirstName; 
  private String teacherLastName;
  @Column(unique = true)
  private String teacherEmail;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "teacher_student",  
  joinColumns = @JoinColumn(name = "teacher_id"), 
  inverseJoinColumns = @JoinColumn(name = "student_id"))
  private Set<Student> students = new HashSet<>();
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "daycare_id")
  private Daycare daycare;
  
 
  
  
}
