package MedicineChest.medicineChest;

import MedicineChest.medicineChestMedicine.MedicineChestMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<MedicineChestMedicine, Long> {

    MedicineChestMedicine getMedicineChestMedicineById(Long id);

}
