package com.amela.managertaskamela.repository;

import com.amela.managertaskamela.model.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    Account findAccountByUsername(@Param("username") String username);

    List<Account> findAllAccount();

    void saveAccount(@Param("account") Account account);
}
