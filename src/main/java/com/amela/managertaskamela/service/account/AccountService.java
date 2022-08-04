package com.amela.managertaskamela.service.account;
import com.amela.managertaskamela.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAllAccount();

    Account findAccountByUsername(String username);

    boolean existsAccountByUsername(List<Account> accounts, String username);

    void saveAccount(Account account);
}