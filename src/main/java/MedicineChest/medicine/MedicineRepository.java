package MedicineChest.medicine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    //SQL, HQL
    @Query("select m from Medicine m where m.name=:name")
    public Medicine getMedicineByName(@Param("name") String name);

    }


