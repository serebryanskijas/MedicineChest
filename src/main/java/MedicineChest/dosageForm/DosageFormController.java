package MedicineChest.dosageForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class DosageFormController {

    @Autowired
    private DosageFormService dosageFormService;

    @GetMapping(value ="/add_dosageForm")
    public String index(Model model) {
        model.addAttribute("dosageForm",new DosageForm()); //Если не добавить, то не будет выполняться парсинг шаблона исходной страницы
        return "add_dosageForm";
    }

    @GetMapping(value ="/list_dosageForms" )
    public String listDosageForms(Model model) {
        model.addAttribute("dosageForms", dosageFormService.findAll());
        return "list_dosageForms";
    }

    @PostMapping(value="/add_dosageForm")
    public String saveDosageForm(DosageForm dosageForm, Model model, HttpServletResponse response) {
        System.out.println(dosageForm);
        //Передать id в заголовке ответа
        DosageForm newDosageForm = dosageFormService.save(dosageForm);
        long id = newDosageForm.getId();
        response.addHeader("id", String.valueOf(id));
        model.addAttribute("dosageForms", dosageFormService.findAll());
        return "redirect:/list_dosageForms";
    }

    @DeleteMapping(value = "/delete_dosageForm")
    public String deleteDosageForm(@RequestParam(name="id")Long id) {
        dosageFormService.deleteById(id);
        return "redirect:/list_dosageForms";
    }

    @GetMapping(value ="/edit_dosageForm")
    public String editDosageForm(Model model, @RequestParam(name="id")Long id) {
        DosageForm dosageForm = dosageFormService.findById(id);
        model.addAttribute("dosageForm",dosageForm);
        return "edit_dosageForm";
    }

    @PutMapping(value="/update_form")
    public String updateDosageForm(DosageForm dosageForm, Model model) {
        DosageForm dosageFormDb = dosageFormService.findById(dosageForm.getId());
        dosageFormDb.setForm(dosageForm.getForm());
        dosageFormService.save(dosageFormDb);
        model.addAttribute("formdosageForms", dosageFormService.findAll());
        return "redirect:/list_dosageForms";
    }
}
