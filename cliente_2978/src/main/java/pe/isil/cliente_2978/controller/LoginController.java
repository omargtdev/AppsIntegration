package pe.isil.cliente_2978.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pe.isil.cliente_2978.model.Login;
import pe.isil.cliente_2978.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping
    public String login(@Valid @ModelAttribute("login") Login login, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "login";
        }

        UserService.AuthenticateResponse authenticateResponse = userService.authenticate(login);
        if(!authenticateResponse.isAuthenticated()){
            model.addAttribute("messageError", "Credenciales incorrectas.");
            return "login";
        }

        session.setAttribute("user", authenticateResponse.getUser());
        return "redirect:/home";
    }
}
