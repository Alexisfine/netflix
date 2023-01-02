package com.alex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "Role")
@Table(name = "role")
@Data
public class Role extends AbstractEntity{
    private String name;
    private String title;
}
