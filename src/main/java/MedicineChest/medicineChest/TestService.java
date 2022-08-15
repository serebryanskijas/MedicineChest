package MedicineChest.medicineChest;

import MedicineChest.medicineChestMedicine.MedicineChestMedicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;


    public MedicineChestMedicine getMedicineChestMedicineById(Long id) {
        return testRepository.getMedicineChestMedicineById(id);
    }

}
