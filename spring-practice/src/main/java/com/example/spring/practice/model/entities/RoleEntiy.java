package com.example.spring.practice.model.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROLE")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntiy extends CommonEntiy {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME_ROLE")
    private String nameRole;

    @OneToMany
    private Set<UserEntity> users;




}