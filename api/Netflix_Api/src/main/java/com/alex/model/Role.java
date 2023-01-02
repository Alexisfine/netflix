package com.alex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "Role")
@Table(name = "role")
public class Role extends AbstractEntity{
    private String name;
    private String title;
}
