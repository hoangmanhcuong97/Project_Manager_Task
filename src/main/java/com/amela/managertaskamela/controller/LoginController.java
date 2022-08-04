package com.amela.managertaskamela.controller;

import com.amela.managertaskamela.model.Account;
import com.amela.managertaskamela.service.account.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("")
public class LoginController {

    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("/account/login");
        return mav;
    }

    @GetMapping("/signup")
    public ModelAndView showFormSignUp(){
        ModelAndView mav = new ModelAndView("/account/register");
        mav.addObject("FormSignUp", new Account());
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView createAccount (@ModelAttribute("FormSignUp") Account account){
        ModelAndView modelAndView = new ModelAndView("/account/register");
        List<Account> accounts = accountService.findAllAccount();
        if (accountService.existsAccountByUsername(accounts,account.getUsername())){
            modelAndView.addObject("message", "Username is existed!");
            return modelAndView;
        }
        Account accountData = new Account();
        accountData.setUsername(account.getUsername());
        String password = account.getPassword();
        String re_password = account.getRe_password();


        if (password.compareTo(re_password) == 0){
            accountData.setPassword(new BCryptPasswordEncoder().encode(password));
        }else {
            modelAndView.addObject("message", "Password not correct");
        }
        accountData.setRe_password(new BCryptPasswordEncoder().encode(re_password));

        accountService.saveAccount(accountData);
        modelAndView.addObject("FormSignUp", new Account());
        modelAndView.addObject("message", "New account created successfully!");
        return modelAndView;
    }


}
