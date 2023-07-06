package daycare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long studentId;
  private String studentName;
  private String studentEmail;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(mappedBy = "students")
  private Set<Teacher> teachers = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "daycare_id")
  private Daycare daycare;

  


}
//     Set<Amenity> amenities = amenityDao.findAllByAmenityIn(daycareData.getAmenities());

//for (Amenity amenity : amenities) {
//  amenity.getDaycares().add(daycare);
//  daycare.getAmenities().add(amenity);
//}