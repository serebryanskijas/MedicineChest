package MedicineChest.medicineChest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineChestRepository extends JpaRepository<MedicineChest, Long> {

    MedicineChest getMedicineChestById(Long pid);
}


