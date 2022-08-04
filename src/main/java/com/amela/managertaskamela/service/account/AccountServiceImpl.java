package com.amela.managertaskamela.service.account;

import com.amela.managertaskamela.model.Account;
import com.amela.managertaskamela.repository.AccountMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountMapper.findAccountByUsername(username);
    }

    @Override
    public List<Account> findAllAccount() {
        return accountMapper.findAllAccount();
    }

    @Override
    public boolean existsAccountByUsername(List<Account> accounts,String username) {
        for (Account ac: accounts) {
            if (ac.getUsername().compareTo(username) == 0) return true;
        }
        return false;
    }

    @Override
    public void saveAccount(Account account) {
        accountMapper.saveAccount(account);
    }
}
