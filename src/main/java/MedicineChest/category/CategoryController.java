package MedicineChest.category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;



    @GetMapping(value ="/list_categories" )
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "list_categories";
    }

   /* @GetMapping(value ="/add_category")
    public String index(Model model) {
        //model.addAttribute("firm",new Firm()); //Если не добавить, то не будет выполняться парсинг шаблона исходной страницы
        return "add_category";
    }

    @PostMapping(value="/add_category")
    public String saveFirm(Category category, Model model, HttpServletResponse response) {
        System.out.println(category);
        //Передать id в заголовке ответа
        //Firm newFirm = firmService.save(firm);
        //long id = newFirm.getId();
        //response.addHeader("id", String.valueOf(id));
        model.addAttribute("categories", categoryService.findAll());
        return "redirect:/list_categories";
    }

    @DeleteMapping(value = "/delete_firm")
    public String deleteFirm(@RequestParam(name="id")Long id) {
        firmService.deleteById(id);
        return "redirect:/list_firms";
    }

    @GetMapping(value ="/edit_firm")
    public String editFirm(Model model, @RequestParam(name="id")Long id) {
        //Firm firm = firmService.findById(id);
        //model.addAttribute("firm",firm);
        return "edit_firm";
    }

    @PutMapping(value="/update_firm")
    public String updateFirm(Role firm, Model model) {
        //Firm firmDb = firmService.findById(firm.getId());
        //firmDb.setName(firm.getName());
        //firmService.save(firmDb);
        model.addAttribute("firms", firmService.findAll());
        return "redirect:/list_firms";
    }*/
}
