package MedicineChest.medicineChest;

import MedicineChest.medicineChestMedicine.MedicineChestMedicineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MedicineChestController {

    @Autowired
    private MedicineChestService medicineChestService;

    @Autowired
    private MedicineChestMedicineService medicineChestMedicineService;

    private final Logger logger = LoggerFactory.getLogger(MedicineChestController.class);

    ;

    @GetMapping(value = "/medicineChests")
    public String listMedicineChest(Model model) {
        model.addAttribute("medicineChests", medicineChestService.findAll());
        return "medicineChests";
    }

    @GetMapping(value = "/add_medicineChest")
    public String index(Model model) {
        model.addAttribute("medicineChest",
                new MedicineChest()); //Если не добавить, то не будет выполняться парсинг шаблона исходной страницы
        return "add_medicineChest";
    }

    @PostMapping(value = "/add_medicineChest")
    public String saveMedicineChest(MedicineChest medicineChest, Model model, HttpServletResponse response) {
        System.out.println(medicineChest);
        //Передать id в заголовке ответа
        if (!StringUtils.isEmpty(medicineChest.getName())) {
            MedicineChest newMedicineChest = medicineChestService.save(medicineChest);
            long id = newMedicineChest.getId();
            response.addHeader("id", String.valueOf(id));
        }
        model.addAttribute("medicineChests", medicineChestService.findAll());
        return "redirect:/medicineChests";
    }

    @GetMapping(value = "/delete_medicineChest")
    public String deleteMedicineChest(@RequestParam(name = "id") Long id) {
        medicineChestService.deleteById(id);
        return "redirect:/medicineChests";
    }

    @GetMapping(value = "/update_medicineChest")
    public String updateMedicineChest(Model model, @RequestParam(name = "id") Long id) {
        MedicineChest medicineChest = medicineChestService.findById(id);
        model.addAttribute("medicineChests", medicineChest);
        return "update_medicineChest";
    }

    @PostMapping(value = "/update_medicineChest")
    public String updateMedicineChest(MedicineChest medicineChest, Model model) {
        MedicineChest medicineChestDb = medicineChestService.findById(medicineChest.getId());
        medicineChestDb.setName(medicineChest.getName());
        medicineChestService.save(medicineChestDb);
        model.addAttribute("medicineChests", medicineChestService.findAll());
        return "redirect:/medicineChests";
    }

}
