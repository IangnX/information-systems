package ve.ucla.informationsystems.controller;



import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ve.ucla.informationsystems.dto.auth.AuthenticationRequest;
import ve.ucla.informationsystems.dto.auth.AuthenticationResponse;
import ve.ucla.informationsystems.persistence.entity.User;
import ve.ucla.informationsystems.service.auth.AuthenticationService;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest){

        AuthenticationResponse authResponse = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile(){

        User user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }
}
