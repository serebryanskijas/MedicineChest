package MedicineChest.medicineChestMedicine;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineChestMedicineRepository extends JpaRepository<MedicineChestMedicine, Long> {

    List<MedicineChestMedicine> getByMedicineChestId(Long id);
}


