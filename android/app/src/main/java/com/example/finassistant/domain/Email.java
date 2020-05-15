package com.example.finassistant.domain;

public class Email{
    private String email;

    public Email(){ }

    public Email(String email){
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(){

        return this.email;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof Email)) return false;

        Email theEmail = (Email) other;
        return email == null ? theEmail.getEmail() == null
                : email.equals(theEmail.getEmail());
    }

    @Override
    public int hashCode() { return email == null ? 0 : email.hashCode(); }
}
