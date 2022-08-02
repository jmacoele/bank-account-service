package com.nttdata.bankaccountservice.model.document.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * Account Client Association Type Enum
 *
 * @author jmacoele
 *
 */

public enum AccountClientAssociationTypeEnum {
  UNIQUEOWNER("P"),
  CONJUNCTIVEOWNERS("C"),
  DISJUNCTIVEOWNERS("D");

  AccountClientAssociationTypeEnum(String value) {
    this.value = value;
  }

  private String value;

  @JsonCreator
  public static AccountClientAssociationTypeEnum of(String value) {
    if (null == value) {
      return null;
    }

    for (AccountClientAssociationTypeEnum item : AccountClientAssociationTypeEnum.values()) {
      if (value.equals(item.getValue())) {
        return item;
      }
    }

    throw new RuntimeException("AccountClientAssociationTypeEnum: unknown value: " + value);
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}