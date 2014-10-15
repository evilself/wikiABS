package com.americanbanksystems.wiki.exception;

import com.americanbanksystems.wiki.domain.User;

public class UserDeleteException extends Exception{
	
    private User user;
 
    public UserDeleteException(User user) {
        this.user = user;
    }
 
    public User getUser() {
        return user;
    }
	
}