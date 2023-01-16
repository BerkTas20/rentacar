package com.berktas.rentacar.model.entity.user;

import com.berktas.rentacar.model.enums.Role;
import com.berktas.rentacar.model.enums.UserType;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
    public Admin(){
        setRole(Role.ROLE_ADMIN);
        setUserType(UserType.Admin);
    }

    public static Admin create(String firstName, String lastName, String userName, String encodedPassword) {
        Admin admin = new Admin();
        admin.setRole(Role.ROLE_ADMIN);
        admin.setUserType(UserType.Admin);
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setPhone("5535011595");
        admin.setEmail("admin@aselsis.tr");
        admin.setUsername(userName);
        admin.setPassword(encodedPassword);
        return admin;
    }
}
