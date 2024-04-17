package com.example.spring.practice.model.dto;


import com.example.spring.practice.model.entities.RoleEntiy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtos extends CommonDtos {


    private long id;


    private String name;


    private String password;

    private String category;

    private int rating;

    private String description;

    private RoleEntiy role;


}