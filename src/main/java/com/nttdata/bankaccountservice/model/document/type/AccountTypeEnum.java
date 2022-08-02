package com.nttdata.bankaccountservice.model.document.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * Account Type Enum
 *
 * @author jmacoele
 *
 */

public enum AccountTypeEnum {
  SAVINGS("S"),
  CHECKINGACCOUNT("C"),
  TIMEDEPOSIT("T");

  private String value;

  @JsonValue
  public String getValue() {
    return value;
  }
  /**
   * Function of that validate string Enum value.
   *
   * @param value Enum value string-converted.
   * @return AccountTypeEnum
   */
  
  @JsonCreator
  public static AccountTypeEnum of(String value) {
    if (null == value) {
      return null;
    }

    for (AccountTypeEnum item : AccountTypeEnum.values()) {
      if (value.equals(item.getValue())) {
        return item;
      }
    }

    throw new RuntimeException("AccountTypeEnum: unknown value: " + value);
  }

  AccountTypeEnum(String value) {
    this.value = value;
  }

}
