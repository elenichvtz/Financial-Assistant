package com.example.finassistant.domain;

/**
 * The type Email.
 */
public class Email{
    private String email;

    /**
     * Instantiates a new Email.
     */
    public Email(){ }

    /**
     * Instantiates a new Email.
     *
     * @param email the email
     */
    public Email(String email){
        this.email = email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get email string.
     *
     * @return the string
     */
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
