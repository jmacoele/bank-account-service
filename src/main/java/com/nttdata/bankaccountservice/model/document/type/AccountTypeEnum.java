package com.nttdata.bankaccountservice.model.document.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountTypeEnum {
	SAVINGS("S"),
	CHECKINGACCOUNT("C"),
	TIMEDEPOSIT("T");

	private String value;

    @JsonValue
    public String getValue() {
        return value;
    }

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
