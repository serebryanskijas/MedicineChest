package MedicineChest.medicineChestMedicine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class MedicineChestMedicineService {
        @Autowired
        private MedicineChestMedicineRepository medicineChestMedicineRepository;

        public List<MedicineChestMedicine> findAll() {
            return medicineChestMedicineRepository.findAll();
        }

        public MedicineChestMedicine save(MedicineChestMedicine dosageForm) {
            return medicineChestMedicineRepository.save(dosageForm);
        }

        public MedicineChestMedicine findById(Long id) {
            return medicineChestMedicineRepository.findById(id).get();
        }

        public void deleteById(Long id) {
            medicineChestMedicineRepository.deleteById(id);
        }
    }
