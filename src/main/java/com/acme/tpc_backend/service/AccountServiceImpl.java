package com.acme.tpc_backend.service;

import  com.acme.tpc_backend.domain.model.Account;
import com.acme.tpc_backend.domain.repository.AccountRepository;
import com.acme.tpc_backend.domain.service.AccountService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("Account", "Id", accountId));
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long accountId, Account accountDetails) {
        return accountRepository.findById(accountId)
                .map(account -> {
                    account.setPassword(accountDetails.getPassword());
                    return accountRepository.save(account);
                }).orElseThrow(()-> new ResourceNotFoundException("Account", "Id", accountId));
    }

    @Override
    public ResponseEntity<?> deleteAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .map(account -> {
                    accountRepository.delete(account);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Account", "Id", accountId));
    }
}
