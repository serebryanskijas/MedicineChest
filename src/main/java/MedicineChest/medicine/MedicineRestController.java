package MedicineChest.medicine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
public class MedicineRestController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicines")
    List<Medicine> findAll(){
        return medicineService.findAll();
    }

}
