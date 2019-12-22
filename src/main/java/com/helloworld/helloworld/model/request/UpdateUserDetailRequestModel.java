package com.helloworld.helloworld.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDetailRequestModel {
    @NotNull(message = "First name cannot be empty")
    @Size(min = 2, message = "First name must not be less than 2 chars")
    private String firstName;

    @Size(min = 2, message = "Last name must not be less than 2 chars")
    @NotNull(message = "Last name cannot be empty")
    private String lastName;

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
}
