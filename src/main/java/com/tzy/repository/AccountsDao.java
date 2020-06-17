package com.tzy.repository;

import com.tzy.model.Accounts;

import java.util.List;

public interface AccountsDao {

    List<Accounts> getAccounts();

    void save(Accounts account);

    boolean delete(Accounts account);

    Accounts findById(long id);



}
