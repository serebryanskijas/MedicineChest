package MedicineChest.medicineChest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicineChestRepository extends JpaRepository<MedicineChest, Long> {

    public MedicineChest getMedicineChestById(Long pid);
}


