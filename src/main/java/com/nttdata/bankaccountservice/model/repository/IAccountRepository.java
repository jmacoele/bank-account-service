package com.nttdata.bankaccountservice.model.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.bankaccountservice.model.document.Account;

@Repository
public interface IAccountRepository extends ReactiveMongoRepository<Account, String> {

}
