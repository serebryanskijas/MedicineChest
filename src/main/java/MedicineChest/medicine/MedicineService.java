package MedicineChest.medicine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
    public class MedicineService {
        @Autowired
        private MedicineRepository medicineRepository;

        public List<Medicine> findAll() {
            return medicineRepository.findAll();
        }

        public Medicine save(Medicine medicine) {
           return medicineRepository.save(medicine);
        }

        public Medicine findById(Long id) {
            return medicineRepository.findById(id).get();
        }

        public void deleteById(Long id) {
            medicineRepository.deleteById(id);
        }

        public Medicine getMedicineByName(String name){
            return medicineRepository.getMedicineByName(name);
        }

    }
