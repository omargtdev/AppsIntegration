package pe.isil.cliente_2978.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.isil.cliente_2978.model.Login;
import pe.isil.cliente_2978.model.User;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    private final String INDEX_API = "http://localhost:8090/api/user";
    private final String AUTHENTICATE_API = INDEX_API + "/authenticate";

    public AuthenticateResponse authenticate(Login login){
        ResponseEntity<AuthenticateResponse> responseFromApi = restTemplate.postForEntity(
                AUTHENTICATE_API,
                login,
                AuthenticateResponse.class
        );
        return responseFromApi.getBody();
    }

    @Data
    public static class AuthenticateResponse {

        private boolean authenticated;
        private String message;
        private User user;

    }
}
