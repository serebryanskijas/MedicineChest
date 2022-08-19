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

        public MedicineChestMedicine save(MedicineChestMedicine medicineChestMedicine) {
            return medicineChestMedicineRepository.save(medicineChestMedicine);
        }

        public MedicineChestMedicine findById(Long id) {
            return medicineChestMedicineRepository.findById(id).get();
        }

        public List<MedicineChestMedicine> findByMedicineChestId(Long id) {
            return medicineChestMedicineRepository.getByMedicineChestId(id);
        }

        public void deleteById(Long id) {
            medicineChestMedicineRepository.deleteById(id);
        }
    }
