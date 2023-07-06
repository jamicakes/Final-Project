package daycare.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Daycare {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  
  private Long daycareId;
  private String daycareName;
  private String daycareAddress;
  private String daycareCity;
  private String daycareState;
  private String daycareZip;
  private String daycarePhone;
      
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @OneToMany(mappedBy = "daycare", cascade = CascadeType.ALL)
  private Set<Student> students = new HashSet<>();
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "daycare_amenity",
  joinColumns = @JoinColumn(name = "daycare_id"),
  inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities = new HashSet<>();
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(mappedBy = "daycare")
  private Set<Teacher> teachers = new HashSet<>();


}
