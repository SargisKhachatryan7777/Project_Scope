package main.java.com.example.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {
    private com.example.model.User user;

    public CurrentUser(com.example.model.User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(new String[]{user.getUserType().name()}));
        this.user = user;
    }

    public com.example.model.User getUser() {
        return this.user;
    }
}
