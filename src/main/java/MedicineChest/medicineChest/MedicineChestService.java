package MedicineChest.medicineChest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MedicineChestService {

    @Autowired
    private MedicineChestRepository medicineChestRepository;

    public List<MedicineChest> findAll() {
        return medicineChestRepository.findAll();
    }

    public MedicineChest save(MedicineChest medicineChest) {
        return medicineChestRepository.save(medicineChest);
    }

    public MedicineChest findById(Long id) {
        return medicineChestRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        medicineChestRepository.deleteById(id);
    }

    public MedicineChest getMedicineChest(Long pid) {
        return medicineChestRepository.getMedicineChestById(pid);
    }

}
