package ve.ucla.informationsystems.service.auth;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ve.ucla.informationsystems.dto.RegisteredUser;
import ve.ucla.informationsystems.dto.SaveUser;
import ve.ucla.informationsystems.dto.auth.AuthenticationRequest;
import ve.ucla.informationsystems.dto.auth.AuthenticationResponse;
import ve.ucla.informationsystems.persistence.entity.User;
import ve.ucla.informationsystems.service.UserService;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Slf4j
@Service
public class AuthenticationService {

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public RegisteredUser registerOneCustomer(SaveUser newUser) {

        User user = userService.registerOneCustomer(newUser);

        RegisteredUser userDto = new RegisteredUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userDto.setJwt(jwt);

        return userDto;
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);

        UserDetails userDetails = userService.findByUsername(authenticationRequest.getUsername()).get();
        String jwt = jwtService.generateToken(userDetails, generateExtraClaims((User) userDetails));
        AuthenticationResponse authResp = new AuthenticationResponse();
        authResp.setJwt(jwt);
        return authResp;

    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());
        return extraClaims;
    }

    public boolean validateToken(String jwt) {
        try {
            jwtService.extractUsername(jwt);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}
