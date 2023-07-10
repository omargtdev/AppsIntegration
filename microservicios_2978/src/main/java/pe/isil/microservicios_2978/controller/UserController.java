package pe.isil.microservicios_2978.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pe.isil.microservicios_2978.exception.ModelNotFoundException;
import pe.isil.microservicios_2978.model.Login;
import pe.isil.microservicios_2978.model.User;
import pe.isil.microservicios_2978.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity create(@RequestBody User user){
        Map<String, Object> response = new HashMap<>();
        userService.addUser(user);

        response.put("success", true);
        response.put("message", "User was created!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody Login login) throws ModelNotFoundException {
        Map<String, Object> response = new HashMap<>();
        response.put("authenticated", false);

        try {
            boolean isAuthenticated = userService.authenticate(login);
            response.put("authenticated", isAuthenticated);
            response.put("message", isAuthenticated
                    ? "User was authenticated"
                    : "User was not authenticated");
            if(isAuthenticated){
                User user = userService.getUser(login.getEmail()).get();
                user.setPassword(""); // Blank the password
                response.put("user", user); // Adding the user to the response
            }

            return ResponseEntity.ok(response);

        }catch (ModelNotFoundException ex){
            response.put("message", ex.getMessage());
           return ResponseEntity
                   .status(HttpStatus.NOT_FOUND)
                   .body(response);
        }
    }

}
