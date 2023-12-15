package ve.ucla.informationsystems.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ve.ucla.informationsystems.dto.RegisteredUser;
import ve.ucla.informationsystems.dto.SaveUser;
import ve.ucla.informationsystems.service.auth.AuthenticationService;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CostumerController {

    private final AuthenticationService authenticationervice;


    @PostMapping
    public ResponseEntity<RegisteredUser> registerOne(@RequestBody @Valid SaveUser newUser){

        RegisteredUser registeredUser = authenticationervice.registerOneCustomer(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
