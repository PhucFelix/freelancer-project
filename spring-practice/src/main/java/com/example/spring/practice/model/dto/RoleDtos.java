package com.example.spring.practice.model.dto;


import com.example.spring.practice.model.entities.UserEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
public class RoleDtos extends CommonDtos{


    private long id;

    private String nameRole;

    private Set<UserEntity> users;




}