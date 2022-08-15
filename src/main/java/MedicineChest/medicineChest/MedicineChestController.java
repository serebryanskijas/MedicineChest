package MedicineChest.medicineChest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MedicineChestController {

    @Autowired
    private MedicineChestService medicineChestService;

    @GetMapping(value ="/add_medicineChest")
    public String index(Model model) {
        model.addAttribute("medicineChest",new MedicineChest()); //Если не добавить, то не будет выполняться парсинг шаблона исходной страницы
        return "add_medicineChest";
    }

    @GetMapping(value ="/medicineChests" )
    public String listMedicineChest(Model model) {
        model.addAttribute("medicineChests", medicineChestService.findAll());
        return "medicineChests";
    }

    @PostMapping(value="/add_medicineChest")
    public String saveMedicineChest(MedicineChest medicineChest, Model model, HttpServletResponse response) {
        System.out.println(medicineChest);
        //Передать id в заголовке ответа
        MedicineChest newMedicineChest = medicineChestService.save(medicineChest);
        long id = newMedicineChest.getId();
        response.addHeader("id", String.valueOf(id));
        model.addAttribute("medicineChests", medicineChestService.findAll());
        return "redirect:/medicineChests";
    }

    @GetMapping(value = "/delete_medicineChest")
    public String deleteMedicineChest(@RequestParam(name="id")Long id) {
        medicineChestService.deleteById(id);
        return "redirect:/medicineChests";
    }

   /* @GetMapping(value = "/delete_medicinesFromChest")
    public String deleteMedicineFromChest(@RequestParam(name = "medicineId", required = false) Long medicineId,
            @RequestParam(name = "chestId", required = false) Long chestId) {
        MedicineChest medicineChest = medicineChestService.getMedicineChest(chestId);
        medicineChest.getMedicine().removeIf(medicine -> medicine.getId().equals(medicineId));
        medicineChestService.save(medicineChest);
        return "redirect:/edit_medicineChest?id=" + chestId;
    }*/



    @GetMapping(value ="/edit_medicineChest")
    public String editMedicineChest(Model model, @RequestParam(name="id")Long id) {
        MedicineChest medicineChest = medicineChestService.findById(id);

        model.addAttribute("medicines",medicineChest);
        return "edit_medicineChest";
    }

    @PutMapping(value="/update_medicineChest")
    public String updateMedicineChest(MedicineChest medicineChest, Model model) {
        MedicineChest medicineChestDb = medicineChestService.findById(medicineChest.getId());
        medicineChestDb.setName(medicineChest.getName());
        medicineChestService.save(medicineChestDb);
        model.addAttribute("medicineChests", medicineChestService.findAll());
        return "redirect:/medicineChests";
    }
}
