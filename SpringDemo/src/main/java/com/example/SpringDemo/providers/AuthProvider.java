package com.example.SpringDemo.providers;
import com.example.SpringDemo.model.User;
import com.example.SpringDemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthProvider(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    UserRepo userRepo;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("authenticate called");
        String  username = authentication.getName(); // login ввел пользователь
        String password = authentication.getCredentials().toString(); // пароль ввел пользователь

        User user = userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User name not found"));

        if(passwordEncoder.matches(password, user.getPassword())){
            return new UsernamePasswordAuthenticationToken(username, password);
        }else {
            throw new BadCredentialsException("Неправильный пароль!");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
