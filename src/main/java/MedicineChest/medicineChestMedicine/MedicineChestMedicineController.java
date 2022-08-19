package MedicineChest.medicineChestMedicine;

import MedicineChest.category.CategoryService;
import MedicineChest.dosageForm.DosageFormService;
import MedicineChest.medicine.Medicine;
import MedicineChest.medicine.MedicineService;
import MedicineChest.medicineChest.MedicineChestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MedicineChestMedicineController {

    @Autowired
    private MedicineChestMedicineService medicineChestMedicineService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineChestService medicineChestService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DosageFormService dosageFormService;


    @GetMapping(value ="/medicineChestMedicines" )
    public String listMedicineChestMedicine(Model model, @RequestParam Long id) {
        model.addAttribute("medicineChestMedicines" ,medicineChestMedicineService.findByMedicineChestId(id));
        model.addAttribute("medicineChests", medicineChestService.findById(id));
        return "medicineChestMedicines";
    }
    @GetMapping(value ="/add_medicineChestMedicine")
    public String addMedicineChestMedicine(Model model) {
        model.addAttribute("medicineChestMedicine",new MedicineChestMedicine());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("dosageForms", dosageFormService.findAll());
        model.addAttribute("medicines", medicineService.findAll());
        return "add_medicineChestMedicine";
    }

    @PostMapping(value="/add_medicineChestMedicine")
    public String saveMedicineChestMedicine(MedicineChestMedicine medicineChestMedicine, Model model, HttpServletResponse response) {
        System.out.println(medicineChestMedicine);
        //Передать id в заголовке ответа
        MedicineChestMedicine newMedicineChestMedicine = medicineChestMedicineService.save(medicineChestMedicine);
        long id = newMedicineChestMedicine.getId();
        response.addHeader("id", String.valueOf(id));
        model.addAttribute("medicineChestMedicine", medicineChestMedicineService.findAll());
        return "redirect:/medicineChestMedicines";
    }

    @GetMapping(value = "/delete_medicineChestMedicine")
    public String deleteMedicineChestMedicine(@RequestParam(name="id")Long id) {
        medicineChestMedicineService.deleteById(id);
        return "redirect:/medicineChestMedicines";
    }

    @GetMapping(value ="/edit_medicineChestMedicine")
    public String editMedicineChestMedicine(Model model, @RequestParam(name="id")Long id) {
        MedicineChestMedicine medicineChestMedicine = medicineChestMedicineService.findById(id);
        model.addAttribute("medicineChestMedicines",medicineChestMedicine);
        return "edit_medicineChestMedicine";
    }

    @PutMapping(value="/update_medicineChestMedicine")
    public String updateMedicineChestMedicine(MedicineChestMedicine medicineChestMedicine, Model model) {
        MedicineChestMedicine medicineChestMedicineDb = medicineChestMedicineService.findById(medicineChestMedicine.getId());
        medicineChestMedicineDb.setExpirationDate(medicineChestMedicine.getExpirationDate());
        medicineChestMedicineService.save(medicineChestMedicineDb);
        model.addAttribute("medicineChestMedicines", medicineChestMedicineService.findAll());
        return "redirect:/list_medicineChestMedicines";
    }
}
