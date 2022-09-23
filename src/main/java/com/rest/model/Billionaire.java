package com.rest.model;

public class Billionaire {
    private Long id;

    private String firstName;

    private String lastName;

    private String company;

    private String wealth;

    public Billionaire() {
    }

    public Billionaire(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWealth() {
        return wealth;
    }

    public void setWealth(String wealth) {
        this.wealth = wealth;
    }

    @Override
    public String toString() {
        return "Billionaire{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                ", wealth='" + wealth + '\'' +
                '}';
    }
}
