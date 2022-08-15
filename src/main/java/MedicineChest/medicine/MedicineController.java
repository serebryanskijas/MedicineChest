package MedicineChest.medicine;

import MedicineChest.category.CategoryService;
import MedicineChest.dosageForm.DosageFormService;
import MedicineChest.medicineChest.MedicineChest;
import MedicineChest.medicineChest.MedicineChestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineChestService medicineChestService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DosageFormService dosageFormService;

    @GetMapping(value = { "/index", "/" })
    public String index() {
        return "index";
    }

    @GetMapping(value = "/medicines")
    public String listMedicines(Model model) {
        model.addAttribute("medicines", medicineService.findAll());
        //TODO
        return "medicines";
    }

    @GetMapping(value = "/edit_medicine")
    public String editMedicine(Model model, @RequestParam(name = "id") Long id) {
        Medicine medicine = medicineService.findById(id);
        model.addAttribute("medicine", medicine);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("dosageForm", dosageFormService.findAll());
        return "edit_medicine";
    }

    @PutMapping(value = "/update_medicine")
    public String updateMedicine(Medicine medicine, Model model) {
        Medicine medicineDb = medicineService.findById(medicine.getId());
        medicineDb.setName(medicine.getName());
        medicineService.save(medicineDb);
        model.addAttribute("medicine", medicineService.findAll());
        return "redirect:/medicines";
    }

    @GetMapping(value = "/delete_medicine")
    public String deleteMedicine(@RequestParam(name = "id") Long id) {
        medicineService.deleteById(id);
        return "redirect:/medicines";
    }



}

