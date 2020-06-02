package com.example.finassistant.domain;

/**
 * The type User.
 */
public class User {

    private String name;
    private Email email;
    private String password;
    private int id;

    /**
     * Instantiates a new User.
     */
    public User(){ }

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param email    the email
     * @param password the password
     * @param id       the id
     */
    public User(String name, Email email, String password,int id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() { return id; }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) { this.id = id; }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(Email email) {
        this.email = email;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        if(password.length() >= 8)  this.password = password;
        else System.out.println("Not enough characters");
    }
}
