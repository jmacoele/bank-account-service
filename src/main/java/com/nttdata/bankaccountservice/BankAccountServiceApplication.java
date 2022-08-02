package com.nttdata.bankaccountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Bank Account Service Application.
 *
 * @author jmacoele
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
public class BankAccountServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankAccountServiceApplication.class, args);
  }

}
