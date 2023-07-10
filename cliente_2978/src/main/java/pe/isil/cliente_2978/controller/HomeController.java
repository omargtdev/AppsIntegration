package pe.isil.cliente_2978.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.isil.cliente_2978.model.User;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String index(Model model, HttpSession session){
        User userLogged = (User) session.getAttribute("user");
        if(userLogged == null)
            return "redirect:/login";

        model.addAttribute("userLogged", userLogged);
        return "home";
    }
}
