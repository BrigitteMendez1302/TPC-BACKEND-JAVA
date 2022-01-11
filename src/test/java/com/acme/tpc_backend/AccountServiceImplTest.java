package com.acme.tpc_backend;

import com.acme.tpc_backend.domain.model.Account;
import com.acme.tpc_backend.domain.repository.AccountRepository;
import com.acme.tpc_backend.domain.service.AccountService;
import com.acme.tpc_backend.exception.ResourceNotFoundException;
import com.acme.tpc_backend.service.AccountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AccountServiceImplTest {
    @MockBean
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @TestConfiguration
    static class AccountServiceImplTestConfiguration {
        @Bean
        public AccountService accountService() {
            return new AccountServiceImpl();
        }
    }

    @Test
    @DisplayName("when SaveAccount With Valid Account Then Returns Success") //happy path
    public void whenSaveAccountWithValidAccountThenReturnsSuccess() {
        Long id = 1L;
        String name = "example@upc.edu.pe";
        String password = "Nota#20";
        Account account = new Account().setId(id).setAccountNumber(name).setPassword(password);
        when(accountRepository.save(account)).thenReturn(account);
        Account savedAccount = accountService.createAccount(account);
        assertThat(savedAccount).isEqualTo(account);
    }

    @Test
    @DisplayName("when GetAccountById With Valid Id Then Returns Account") //happy path
    public void whenGetAccountByIdWithValidIdThenReturnsAccount() {
        //Arrange
        Long id = 1L;
        Account account = new Account().setId(id);
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        //Act
        Account foundAccount = accountService.getAccountById(id);
        //Assert
        assertThat(foundAccount.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetAccountById With Invalid Id Then Returns ResourceNotFoundException") //unhappy path
    public void whenGetAccountByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(accountRepository.findById(id)).thenReturn(Optional.empty());
        String exceptedMessage = String.format(template, "Account", "Id", id);
        //Act
        Throwable exception = catchThrowable(() ->{
            Account foundAccount = accountService.getAccountById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(exceptedMessage);
    }

    @Test
    @DisplayName("when UpdateAccount With Valid Account Then Returns Success") //happy path
    public void whenUpdateAccountWithValidAccountThenReturnsSuccess() {
        //Arrange
        Long id = 1L;
        String name = "example@upc.edu.pe";
        String password = "Nota#20";
        Account account = new Account().setId(id).setAccountNumber(name).setPassword(password);

        String newPassword = "Nota@20";
        account.setPassword(newPassword);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        Account saved = accountService.updateAccount(id, account);
        Optional<Account> updateAccount = accountRepository.findById(id);
        assertThat(saved).isEqualTo(account);
    }
}
