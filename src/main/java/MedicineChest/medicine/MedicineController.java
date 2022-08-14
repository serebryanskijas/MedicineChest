package MedicineChest.medicine;


import MedicineChest.category.CategoryService;
import MedicineChest.dosageForm.DosageFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
    public class MedicineController {

        @Autowired
        private MedicineService medicineService;

    @Autowired
    private CategoryService categoryService;
    private DosageFormService dosageFormService;

    @GetMapping(value ={"/index","/"})
    public String index() {
        return "index";
    }

    @GetMapping(value ="/medicines" )
    public String listMedicines(Model model) {
        model.addAttribute("medicines", medicineService.findAll());
        return "medicines";
    }

    @GetMapping(value ="/edit_medicine")
    public String editMedicine(Model model, @RequestParam(name="id") Long id) {
        Medicine medicine = medicineService.findById(id);
        model.addAttribute("medicine",medicine);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("dosageForm", dosageFormService.findAll());
        return "edit_medicine";
    }

    @PutMapping(value="/update_medicine")
    public String updateMedicine(Medicine medicine, Model model) {
        Medicine medicineDb = medicineService.findById(medicine.getId());
        medicineDb.setName(medicine.getName());
        medicineService.save(medicineDb);
        model.addAttribute("medicine", medicineService.findAll());
        return "redirect:/medicines";
    }

    @GetMapping(value = "/delete_medicine")
    public String deleteUser(@RequestParam(name="id")Long id) { medicineService.deleteById(id);
        return "redirect:/medicines";
    }

    //Регистрация
 /*       @GetMapping(value ="/signup")
        public String signup(Model model) {
            model.addAttribute("user",new User()); //Если не добавить, то не будет выполняться парсинг шаблона исходной страницы
            return "signup";
        }

        @PostMapping(value="/signup")
        public String saveUser(User user, Model model, HttpServletResponse response,
                               @RequestParam(value="password2", required = true) String password2
                               ){
        if (user==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fill all fields");
            if (medicineService.getUserByUsername(user.getUsername())!=null || medicineService.getUserByEmail(user.getEmail())!=null){
                throw new ResponseStatusException(HttpStatus.FOUND, "User with this username or email already exists");
            }
            if(!user.getPassword().equals(password2)){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Passwords do not match");
            }
            Set<Role> rolesNew = new HashSet<>();
            rolesNew.add(categoryService.findById(3L));
            user.setRoles(rolesNew);
            user.setEnabled(true);
            User userNew = userService.save(user);

            long id = userNew.getId();
            response.addHeader("id", String.valueOf(id));
            return "index";
        }*/



    //Вход
  /*  @GetMapping(value ="/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping(value ="/signin-error")
    public String signinError(Model model) {
        model.addAttribute("loginError",true);
        return "signin";
    }

    @PostMapping(value="/signin")
    public String signinUser(HttpServletResponse response,
                             @RequestParam(name="username")String username,
                             @RequestParam(name="password")String password){
        User user = userService.getUserByUsername(username);
        System.out.println(username);
        System.out.println(password);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        if (user==null || !user.getPassword().equals(password)) return "redirect:/signin-error";
        else return "index";
    }*/

    }

