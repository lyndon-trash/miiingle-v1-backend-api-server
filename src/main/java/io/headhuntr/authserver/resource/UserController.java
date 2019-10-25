package io.headhuntr.authserver.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    /**
     * curl -X GET \
     *   http://localhost:8081/auth/user/me \
     *   -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImJhciIsInJlYWQiLCJ3cml0ZSJdLCJvcmdhbml6YXRpb24iOiJhZG1pbmxtTkwiLCJleHAiOjE1NzIwMjQyMDgsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJjNGFkNjFkOS04ZTRlLTQyY2MtYTNlNC04NTYyODhhNTg0MDgiLCJjbGllbnRfaWQiOiJjbGllbnQxMjM0In0.ECwtEZTwHJO5gaYHnDrwvRZrQ2uQdx1SdEMTZ5HjI6A' \
     *   -H 'Content-Type: application/json' \
     *   -H 'cache-control: no-cache'
     */
    @GetMapping("user/me")
    public Principal findUser(Principal principal) {
        return principal;
    }
}
