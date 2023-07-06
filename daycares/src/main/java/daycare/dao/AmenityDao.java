package daycare.dao;

import daycare.entity.Amenity;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityDao extends JpaRepository<Amenity, Long> {

  Set<Amenity> findAllByAmenityIn(Set<String> amenities);

}
