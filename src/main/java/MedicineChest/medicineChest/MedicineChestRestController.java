package MedicineChest.medicineChest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicineChestRestController {
    @Autowired
    private MedicineChestService medicineChestService;

    @GetMapping(value="/medicineChest", params={})
    public List<MedicineChest> getMedicineChest(){
        var medicineChests = (List<MedicineChest>) medicineChestService.findAll();
        return medicineChests;
    }


}
