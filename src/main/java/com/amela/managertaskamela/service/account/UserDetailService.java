package com.amela.managertaskamela.service.account;

import com.amela.managertaskamela.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    private final AccountServiceImpl accountService;

    public UserDetailService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findAccountByUsername(username);
        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
        grantList.add(authority);
        UserDetails userDetails = new User(account.getUsername(), account.getPassword(), grantList );
        return userDetails;
    }

    public Account getCurrentAccount(){
        Account account;
        String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            username = (((UserDetails) principal).getUsername());
        } else {
            username = principal.toString();
        }
        List<Account> accounts = accountService.findAllAccount();
        if (accountService.existsAccountByUsername(accounts,username)){
            account = accountService.findAccountByUsername(username);
        } else {
            account = new Account();
            account.setUsername("Anonymous");
        }

        return account;
    }

}
