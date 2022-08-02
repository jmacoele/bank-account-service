package com.nttdata.bankaccountservice.model.repository;

import com.nttdata.bankaccountservice.model.document.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
/**
 * IAccountRepository Interface.
 *
 * @author jmacoele
 *
 */

@Repository
public interface IAccountRepository
    extends ReactiveMongoRepository<Account, String> {

}
