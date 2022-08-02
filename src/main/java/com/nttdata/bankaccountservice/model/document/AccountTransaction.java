package com.nttdata.bankaccountservice.model.document;


import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "accounttransactions")
public class AccountTransaction {

  @Id
  private Long id;

  @CreatedDate
  private LocalDateTime date;

  private String shortDescription;

  private Double amount;

  private String clientAnnotation;

}
