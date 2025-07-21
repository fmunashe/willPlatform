package zw.co.zim.willplatform.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zw.co.zim.willplatform.dto.forms.LoginForm;

@Controller
@RequestMapping("/web")
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "web/login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") LoginForm form){

        if ("admin@gmail.com".equals(form.getUsername()) && "password".equals(form.getPassword())) {
            return "redirect:/web/dashboard";
        }
        return "redirect:/web/login?error";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "web/forgot-password";
    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "web/home";
    }
}
