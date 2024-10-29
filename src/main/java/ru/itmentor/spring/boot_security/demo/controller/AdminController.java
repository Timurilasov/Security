import ru.itmentor.spring.boot_security.demo.services.UserServicesImpl;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserServicesImpl userServices;
    private final RoleServiceImpl roleService;

    public AdminController(UserServicesImpl userServices, RoleServiceImpl roleService) {
        this.userServices = userServices;
        this.roleService = roleService;
    }

    private void addAllRolesToModel(Model model) {
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("allRoles", roles);
    }

    @GetMapping("")
    public String adminHome(Model model, Authentication authentication) {
        if (authentication != null) {
            String adminName = authentication.getName();
            model.addAttribute("adminName", adminName);
        }
        return "admin-home"; 
    }

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> users = userServices.getUsersAndRoles();
        model.addAttribute("users", users);
        return "admin-all";
    }

    @GetMapping("/{id}")
    public String readUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServices.readUser(id));
        return "admin-show"; 
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        addAllRolesToModel(model);
        model.addAttribute("user", new User());
        return "admin-new"; 
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        userServices.createUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        User user = userServices.readUser(id);
        addAllRolesToModel(model);
        model.addAttribute("user", user);
        return "admin-edit"; 
    }

    @PatchMapping("/{id}")
    public String master(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userServices.master(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServices.deleteUser(id);
        return "redirect:/admin/users";
    }
}
