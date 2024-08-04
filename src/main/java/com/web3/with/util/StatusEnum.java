package com.web3.with.util;

public enum StatusEnum {
        INVITE,
        DECLINE,
        WAITING,
        REMOVE,
        ARCHIVE;

        public static Long fromString(String text) {
            for (StatusEnum priority : StatusEnum.values()) {
                if (priority.name().equals(text)) {
                    return (long) priority.ordinal() + 1;
                }
            }
            throw new IllegalArgumentException("Unknown Status: " + text);
        }

}
