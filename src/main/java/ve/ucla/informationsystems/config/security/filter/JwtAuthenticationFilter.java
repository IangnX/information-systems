package ve.ucla.informationsystems.config.security.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ve.ucla.informationsystems.exception.ObjectNotFoundException;
import ve.ucla.informationsystems.service.UserService;
import ve.ucla.informationsystems.service.auth.JwtService;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1. Get Authorizationh eader
        String authorizationHeader = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        //2 Get token from Authorization header
        String jwt = authorizationHeader.split(" ")[1];

        //3 Get Subject/username from token (this step validate token)
        String username = jwtService.extractUsername(jwt);

        //4 Set Authentication Object in SecurityContextHolder
        UserDetails userDetails = userService.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + username));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //5 Execute filter log
        filterChain.doFilter(request, response);
    }
}
