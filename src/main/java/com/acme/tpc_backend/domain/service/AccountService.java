package com.acme.tpc_backend.domain.service;
 import com.acme.tpc_backend.domain.model.Account;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.http.ResponseEntity;
 
 public interface AccountService {
 	Account getAccountById(Long accountId);
 	Account createAccount(Account account);
 	Account updateAccount(Long AccountId, Account accountDetails);
 	ResponseEntity<?> deleteAccount(Long accountId);
 }