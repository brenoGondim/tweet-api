package com.challenge.livesponsor.tweetapi.user;

public abstract class UserContext {
    private static final ThreadLocal<String> currentEmail = new ThreadLocal<>();

    public static String getCurrentEmail() {
        return currentEmail.get();
    }

    public static void setCurrentEmail(String email) {
        currentEmail.set(email);
    }

    public static void clearCurrentEmail() {
        currentEmail.remove();
    }
}