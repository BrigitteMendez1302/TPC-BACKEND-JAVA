package com.acme.tpc_backend.resource;
public class SaveSuggestionResource{
    private String message;
    private Long userId;

    public Long getUserId()
    {
        return userId;
    }
    public SaveSuggestionResource setUserId(Long userId)
    {
        this.userId = userId;
        return this;
    }
    public String getMessage() {
        return message;
    }

    public SaveSuggestionResource setMessage(String message) {
        this.message = message;
        return this;
    }

}