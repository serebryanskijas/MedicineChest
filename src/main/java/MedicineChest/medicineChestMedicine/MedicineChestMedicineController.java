package MedicineChest.medicineChestMedicine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MedicineChestMedicineController {

    @Autowired
    private MedicineChestMedicineService medicineChestMedicineService;

    @GetMapping(value ="/add_medicineChestMedicine")
    public String index(Model model) {
        model.addAttribute("medicineChestMedicines",new MedicineChestMedicine()); //Если не добавить, то не будет выполняться парсинг шаблона исходной страницы
        return "add_medicineChestMedicine";
    }

    @GetMapping(value ="/list_medicineChestMedicines" )
    public String listMedicineChestMedicine(Model model) {
        model.addAttribute("medicineChestMedicines", medicineChestMedicineService.findAll());
        return "list_medicineChestMedicine";
    }

    @PostMapping(value="/add_medicineChestMedicines")
    public String saveMedicineChestMedicine(MedicineChestMedicine medicineChestMedicine, Model model, HttpServletResponse response) {
        System.out.println(medicineChestMedicine);
        //Передать id в заголовке ответа
        MedicineChestMedicine newMedicineChestMedicine = medicineChestMedicineService.save(medicineChestMedicine);
        long id = newMedicineChestMedicine.getId();
        response.addHeader("id", String.valueOf(id));
        model.addAttribute("medicineChestMedicines", medicineChestMedicineService.findAll());
        return "redirect:/list_medicineChestMedicines";
    }

    @DeleteMapping(value = "/delete_medicineChestMedicine")
    public String deleteMedicineChestMedicine(@RequestParam(name="id")Long id) {
        medicineChestMedicineService.deleteById(id);
        return "redirect:/list_medicineChestMedicines";
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
