package hu.pizzaorder.backend.service;

import hu.pizzaorder.backend.dto.AccountDTO;
import hu.pizzaorder.backend.model.Account;
import hu.pizzaorder.backend.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    public void register(AccountDTO accountDTO) {
        accountRepository.save(modelMapper.map(accountDTO, Account.class));
    }

    public Optional<AccountDTO> getAccountByUsername(String username) {
        Optional<Account> account = accountRepository.findByUsername(username);
        return account.map(value -> modelMapper.map(value, AccountDTO.class));
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream().map(acc -> modelMapper.map(acc, AccountDTO.class)).collect(Collectors.toList());
    }
}
