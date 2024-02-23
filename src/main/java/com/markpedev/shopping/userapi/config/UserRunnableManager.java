package com.markpedev.shopping.userapi.config;


public class UserRunnableManager {
	private static UserRunnable userRunnable;
	
	public static void setUserRunnable(UserRunnable runnable) {
		userRunnable = runnable;
    }

    public static UserRunnable getUserRunnable() {
        return userRunnable;
    }
}
