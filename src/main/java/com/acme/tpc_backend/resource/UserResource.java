package com.acme.tpc_backend.resource;
public class UserResource{
    private Long id;
    private AccountResource account;
    private String firstName;
    private String lastName;
    private Integer role;

    public  Integer getRole(){return role;}
    public void setRole(Integer role){ this.role=role;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountResource getAccount() {
        return account;
    }

    public void setAccount(AccountResource account) {
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String mail;
    private Long phoneNumber;
}