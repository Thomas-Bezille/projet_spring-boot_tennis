package com.dyma.tennis.service;

public class PlayerDataRetrivalException extends RuntimeException {
    public PlayerDataRetrivalException(Exception e) {
        super("Could not be retrieve player data", e);
    }
}
