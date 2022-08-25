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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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


    @GetMapping(value = "/medicineChestMedicines")
    public String listMedicineChestMedicine(Model model, @RequestParam Long id, @RequestParam(name = "category", required = false) Long category) {
        if (category == null || category==-1L) {
            model.addAttribute("medicineChestMedicines", medicineChestMedicineService.findByMedicineChestId(id));
        } else {
            model.addAttribute("medicineChestMedicines", medicineChestMedicineService.findByCategory(id, category));
            model.addAttribute("saveSelectState", category);
        }
        model.addAttribute("medicineChests", medicineChestService.findById(id));
        model.addAttribute("categoryList", categoryService.findAll());
        return "medicineChestMedicines";
    }
    /*@GetMapping(value ="/medicineChestMedicines" )
    public String listMedicineChestMedicine(Model model, @RequestParam Long id) {
        model.addAttribute("medicineChestMedicines" ,medicineChestMedicineService.findByMedicineChestId(id));
        model.addAttribute("medicineChests", medicineChestService.findById(id));
        return "medicineChestMedicines";
    }*/
    @GetMapping(value ="/add_medicineChestMedicine")
    public String addMedicineChestMedicine(Model model, @RequestParam Long id) {
        model.addAttribute("medicineChestMedicine",new MedicineChestMedicine());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("dosageForms", dosageFormService.findAll());
        model.addAttribute("medicines", medicineService.findAll());
        model.addAttribute("currentId", id);

        return "add_medicineChestMedicine";
    }

    @PostMapping(value="/add_medicineChestMedicine")
    public String saveMedicineChestMedicine(MedicineChestMedicine medicineChestMedicine,
            @RequestParam(value = "expirationDate", defaultValue = "") String expirationDateString, Model model, HttpServletResponse response) {
        LocalDate expirationDate = LocalDate.parse(expirationDateString, DateTimeFormatter.ofPattern("dd.MM.yyyy",
                Locale.US));
        medicineChestMedicine.setExpirationDate(expirationDate);
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
