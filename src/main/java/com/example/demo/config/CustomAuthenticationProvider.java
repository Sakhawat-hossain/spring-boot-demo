package com.example.demo.config;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    private UserService userService;

    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        logger.debug("Name: " + authentication.getName() + ", Principal: " + authentication.getPrincipal() +
            ", Credentials: " + authentication.getCredentials());

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.authenticateUser(username, password);
        logger.debug("User: " + (user == null ? "null" : user.toString()));
        if (user == null) {
            throw new BadCredentialsException("Username or password is wrong");
        }

        Authentication authenticated = new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());

        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
