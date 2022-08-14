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

    @GetMapping(value ="/list_medicineChests" )
    public String listFirms(Model model) {
        model.addAttribute("medicineChests", medicineChestService.findAll());
        return "list_medicineChests";
    }

    @PostMapping(value="/add_medicineChest")
    public String saveFirm(MedicineChest medicineChest, Model model, HttpServletResponse response) {
        System.out.println(medicineChest);
        //Передать id в заголовке ответа
        MedicineChest newMedicineChest = medicineChestService.save(medicineChest);
        long id = newMedicineChest.getId();
        response.addHeader("id", String.valueOf(id));
        model.addAttribute("medicineChests", medicineChestService.findAll());
        return "redirect:/list_medicineChests";
    }

    @DeleteMapping(value = "/delete_medicineChest")
    public String deleteFirm(@RequestParam(name="id")Long id) {
        medicineChestService.deleteById(id);
        return "redirect:/list_medicineChests";
    }

    @GetMapping(value ="/edit_medicineChest")
    public String editFirm(Model model, @RequestParam(name="id")Long id) {
        MedicineChest medicineChest = medicineChestService.findById(id);
        model.addAttribute("medicineChest",medicineChest);
        return "edit_medicineChest";
    }

    @PutMapping(value="/update_firm")
    public String updateFirm(MedicineChest medicineChest, Model model) {
        MedicineChest medicineChestDb = medicineChestService.findById(medicineChest.getId());
        medicineChestDb.setName(medicineChest.getName());
        medicineChestService.save(medicineChestDb);
        model.addAttribute("medicineChests", medicineChestService.findAll());
        return "redirect:/list_medicineChests";
    }
}
