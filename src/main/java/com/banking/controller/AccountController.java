package com.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.AccountDto;
import com.banking.service.AccountService;

@RestController
@RequestMapping("api/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/addAccount")
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}

	@GetMapping("/getAccountById/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
		AccountDto accountById = accountService.getAccountById(id);
		return ResponseEntity.ok(accountById);
	}

	@GetMapping("/getAllAccouts")
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		List<AccountDto> allAccount = accountService.getAllAccounts();
		return ResponseEntity.ok(allAccount);
	}

	@PutMapping("/deposit/{id}")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestParam double amount) {
		AccountDto deposit = accountService.deposit(id, amount);
		return ResponseEntity.ok(deposit);
	}
	
	@PutMapping("/withdraw/{id}")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestParam double amount) {
		AccountDto withdraw = accountService.withdraw(id, amount);
		return ResponseEntity.ok(withdraw);
	}
	
	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Void> deleteAccountById(@PathVariable Long id) {
	        accountService.deleteAccountById(id);
	        return ResponseEntity.noContent().build();
	    }

}
