package com.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dto.AccountDto;
import com.banking.entity.Account;
import com.banking.mapper.AccountMapper;
import com.banking.repository.AccountRepo;
import com.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account save = accountRepo.save(account);
		return AccountMapper.mapToAccountDto(save);
	}

	@Override
	public AccountDto getAccountById(Long id) {

		Account accountbyid = accountRepo
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		return AccountMapper.mapToAccountDto(accountbyid);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		 List<Account> allAccounts = accountRepo.findAll();
	        return allAccounts.stream()
	                .map(AccountMapper::mapToAccountDto)
	                .collect(Collectors.toList());
	    }

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepo
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		double total=account.getBalance() + amount;
		account.setBalance(total);
		Account save = accountRepo.save(account);
		return AccountMapper.mapToAccountDto(save);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepo
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exists"));
		
		if(account.getBalance()< amount) {
			throw new RuntimeException("Insuficient amount");
		}
		
		double total=account.getBalance() - amount;
		account.setBalance(total);
		Account save = accountRepo.save(account);
		return AccountMapper.mapToAccountDto(save);
	}
	
	@Override
    public void deleteAccountById(Long id) {
        Account account = accountRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        accountRepo.delete(account);
    }

}
