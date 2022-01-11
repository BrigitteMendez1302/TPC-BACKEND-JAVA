package com.acme.tpc_backend.controller;

import com.acme.tpc_backend.domain.model.Account;
import com.acme.tpc_backend.domain.service.AccountService;
import com.acme.tpc_backend.domain.service.UniversityService;
import com.acme.tpc_backend.resource.AccountResource;
import com.acme.tpc_backend.resource.SaveAccountResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UniversityService universityService;

    @Operation(summary = "List accounts", description = "Lists accounts", tags = {"accounts"})
    @GetMapping("/accounts/{accountId}")
    public AccountResource getAccountById(@PathVariable Long accountId) {
        return convertToResource(accountService.getAccountById(accountId));
    }

    @Operation(summary = "Create account", description = "Create account", tags = {"accounts"})
    @PostMapping("/accounts")
    public AccountResource createAccount(@Valid @RequestBody SaveAccountResource resource) {
        return convertToResource(accountService.createAccount(convertToEntity(resource)));
    }

    @Operation(summary = "Update account", description = "Update account", tags = {"accounts"})
    @PutMapping("/accounts/{accountId}")
    public AccountResource updateAccount(@PathVariable Long accountId, @Valid @RequestBody SaveAccountResource resource) {
        return convertToResource(accountService.updateAccount(accountId, convertToEntity(resource)));
    }

    private Account convertToEntity(SaveAccountResource resource)
    {
        Account account = new Account();
        account.setAccountNumber(resource.getAccountNumber());
        account.setPassword(resource.getPassword());
        account.setUniversity(universityService.getUniversityById(resource.getUniversityId()));
        return account;
    }

    private AccountResource convertToResource(Account entity) {
        return mapper.map(entity, AccountResource.class);
    }
}