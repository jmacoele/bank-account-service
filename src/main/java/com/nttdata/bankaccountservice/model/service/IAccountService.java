package com.nttdata.bankaccountservice.model.service;

import com.nttdata.bankaccountservice.model.document.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountService {

  Mono<Account> findById(String id);

  Flux<Account> findAll();

  Mono<Boolean> existsById(String id);

  Mono<Account> save(Account account) throws Exception;

  Mono<Account> save(String id, Account account) throws Exception;

  Mono<Account> delete(String id);

}
