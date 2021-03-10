package com.by.bharadwaj.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class Employee {

    @JsonProperty("employeeName")
    @NotNull(message = "Name cannot be null")
    @Size(min=3,message="Employee Name cannot be less than 10 Charecters")
    private String employeeName;

    @JsonProperty("employeeId")
    @NotNull(message = "Id cannot be null")
    @Size(min=10,message="Employee ID cannot be less than 10 Charecters")
    private String employeeId;

    @JsonProperty("address")
    @NotNull(message = "Address cannot be null")
    @Size(max=50,message = "Address cannnot be more than 50 charecters")
    private String address;

    @JsonProperty("org")
    @NotNull(message = "Organization cannot be null")
    @Size(min=5,message = "Organization cannnot be less than 5 charecters")
    private String org;

    @JsonProperty("role")
    @NotNull(message = "Role cannot be null")
    @Size(min=3,message = "Role cannnot be less than 5 charecters")
    private String role;

}
