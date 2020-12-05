package main.java.com.example.security;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl() {
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User byUsername = (User)this.userRepository.findByEmail(s).orElseThrow(() -> {
            return new UsernameNotFoundException("User not found");
        });
        return new CurrentUser(byUsername);
    }
}
