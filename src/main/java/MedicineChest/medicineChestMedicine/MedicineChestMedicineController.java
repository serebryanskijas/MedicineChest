package MedicineChest.medicineChestMedicine;

import MedicineChest.category.CategoryService;
import MedicineChest.dosageForm.DosageFormService;
import MedicineChest.medicine.Medicine;
import MedicineChest.medicine.MedicineService;
import MedicineChest.medicineChest.MedicineChest;
import MedicineChest.medicineChest.MedicineChestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
    public String listMedicineChestMedicine(Model model, @RequestParam Long id,
            @RequestParam(name = "category", required = false) Long category) {
        if (category == null || category == -1L) {
            model.addAttribute("medicineChestMedicines", medicineChestMedicineService.findByMedicineChestId(id));
        } else {
            model.addAttribute("medicineChestMedicines", medicineChestMedicineService.findByCategory(id, category));
            model.addAttribute("saveSelectState", category);
        }
        model.addAttribute("medicineChests", medicineChestService.findById(id));
        model.addAttribute("medicineList", medicineService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        return "medicineChestMedicines";
    }

    @GetMapping(value = "/add_medicineChestMedicine")
    public String addMedicineChestMedicine(Model model, @RequestParam Long id) {
        MedicineChestMedicine medicineChestMedicine = new MedicineChestMedicine();
        medicineChestMedicine.setCount(1);
        MedicineChest medicineChest = medicineChestService.findById(id);
        medicineChestMedicine.setMedicineChest(medicineChest);
        model.addAttribute("medicineChestMedicine", medicineChestMedicine);
        model.addAttribute("medicineChest", medicineChest);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("dosageForms", dosageFormService.findAll());
        model.addAttribute("medicines", medicineService.findAll());
        model.addAttribute("currentId", id);

        return "add_medicineChestMedicine";
    }

    @GetMapping(value = "/getMedicineByCategory")
    public @ResponseBody String getMedicineByCategory(@RequestParam String categoryId) throws JsonProcessingException {
        //        return medicineService.findByCategoryId(Long.valueOf(categoryId));
        Map<String, Object> map = new HashMap<>();
        List<Medicine> listMedicine = medicineService.findByCategoryId(Long.valueOf(categoryId));
        Set<Long> idSet = new HashSet<>();
        for (Medicine el : listMedicine) {
            idSet.add(el.getId());
        }
        map.put("result", idSet);
        return new ObjectMapper().writeValueAsString(map);
    }

    @PostMapping(value = "/add_medicineChestMedicine")
    public String saveMedicineChestMedicine(MedicineChestMedicine medicineChestMedicine) {
        medicineChestMedicineService.save(medicineChestMedicine);
        return "redirect:/medicineChestMedicines?id=" + medicineChestMedicine.getMedicineChest().getId();
    }

    @GetMapping(value = "/update_medicineChestMedicine")
    public String updateMedicineChestMedicine(Model model, @RequestParam(name = "id") Long id) {
        MedicineChestMedicine medicineChestMedicine = medicineChestMedicineService.findById(id);
        model.addAttribute("medicineChestMedicine", medicineChestMedicine);
        model.addAttribute("medicineChest", medicineChestService.getMedicineChest(id));
        model.addAttribute("medicines",
                medicineService.findByCategoryId(medicineChestMedicine.getMedicine().getCategory().getId()));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("dosageForms", dosageFormService.findAll());
        return "update_medicineChestMedicine";
    }

    @PostMapping(value = "/update_medicineChestMedicine")
    public String updateMedicineChestMedicine(MedicineChestMedicine medicineChestMedicine) {

        medicineChestMedicineService.save(medicineChestMedicine);

        return "redirect:/medicineChestMedicines?id=" + medicineChestMedicine.getMedicineChest().getId();
    }

    @GetMapping(value = "/delete_medicineChestMedicine")
    public String deleteMedicineChestMedicine(@RequestParam(name = "id") Long id, @RequestParam Long chestId) {
        medicineChestMedicineService.deleteById(id);
        return "redirect:/medicineChestMedicines?id=" + chestId;
    }

}
