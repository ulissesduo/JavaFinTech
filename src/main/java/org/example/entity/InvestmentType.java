package org.example.entity;

import java.util.HashMap;
import java.util.Map;

public enum InvestmentType {
    STOCKS("Stocks"),
    BONDS("Bonds"),
    REAL_ESTATE("Real Estate"),
    MUTUAL_FUNDS("Mutual Funds"),
    REAL_ESTATE_INVESTMENT_TRUST( "Real Estate Investment Trust"),
    CRYPTOCURRENCY("Cryptocurrency");

    private final String typeName;
    InvestmentType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static InvestmentType fromString(String typeName) {
        for (InvestmentType type : InvestmentType.values()) {
            if (type.typeName.equalsIgnoreCase(typeName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with type name " + typeName);
    }
}
