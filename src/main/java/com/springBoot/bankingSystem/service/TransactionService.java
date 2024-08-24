package com.springBoot.bankingSystem.service;

import com.springBoot.bankingSystem.entity.Account;
import com.springBoot.bankingSystem.entity.Transaction;
import com.springBoot.bankingSystem.exception.AccountNotFoundException;
import com.springBoot.bankingSystem.exception.InsufficientFundsException;
import com.springBoot.bankingSystem.repository.AccountRepository;
import com.springBoot.bankingSystem.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

private final TransactionRepository transactionRepository;
private final AccountRepository accountRepository;


    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }
@Transactional
    public Transaction deposit(Long accountId , BigDecimal amount){
        Account account = this.accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
        account.setBalance(account.getBalance().add(amount));
        this.accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .type("DEPOSIT") //Best use enums
                .timeStamp(LocalDateTime.now())
                .build();
        return this.transactionRepository.save(transaction);
    }
@Transactional
    public Transaction withdraw(Long accountId , BigDecimal amount){
        Account account = this.accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
        if(account.getBalance().compareTo(amount) < 0){
            throw new InsufficientFundsException(accountId);
        }
        account.setBalance(account.getBalance().add(amount));
        this.accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .type("WITHDRAW") //Best use enums
                .timeStamp(LocalDateTime.now())
                .build();
        return this.transactionRepository.save(transaction);
    }

@Transactional
public Transaction transfer(Long fromAccountId , Long toAccountId , BigDecimal amount){
    Account fromAccount = this.accountRepository.findById(fromAccountId).orElseThrow(() -> new AccountNotFoundException(fromAccountId));
    Account toAccount = this.accountRepository.findById(fromAccountId).orElseThrow(() -> new AccountNotFoundException(fromAccountId));
    this.withdraw(fromAccountId,amount);
    Transaction transferTransaction = Transaction.builder()
            .account(fromAccount)
            .amount(amount)
            .type("TRANSFER") //Best use enums
            .timeStamp(LocalDateTime.now())
            .build();
    this.deposit(toAccountId,amount);
    return this.transactionRepository.save(transferTransaction);
}

    public List<Transaction> getTransactionHistory(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

}
