package com.example.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class AuthorCreateViewModel {

    @NotEmpty
    private String name;
    private String nationality;

}
