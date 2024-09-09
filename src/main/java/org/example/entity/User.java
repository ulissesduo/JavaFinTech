package org.example.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User extends Person {

    private String email;
    private String password;
    public enum Role{
         ADMIN, USER
    }
    private Role roles;
    public static final Map<Long, User> userStore = new HashMap<>();

    public User(Long id, String name, String lastName, Integer age, Date birthdate, String email, String password, Role role) {
        super(id, name, lastName, age, birthdate);
        this.email = email;
        this.password = password;
        this.roles = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public static User createUser(Long id, String name, String lastName, Integer age, Date birthdate, String email, String password, Role roles) {
        User user = new User(id, name, lastName, age, birthdate, email, password, roles );
        userStore.put(id, user);
        return user;
    }


    public static User getById(Long id) {
        return userStore.get(id);
    }

    public static User updateUser(Long id, String name, String lastName, Integer age, Date birthdate, String email) {
        User user = userStore.get(id);
        if (user != null) {
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            user.setBirthdate(birthdate);
            user.setEmail(email);
        }
        return user;
    }

    public static boolean deleteUser(Long id) {
        if (userStore.containsKey(id)) {
            userStore.remove(id);
            return true;
        }
        return false;
    }

    public void displayUserDetails() {
        System.out.println("User ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Last Name: " + getLastName());
        System.out.println("Age: " + getAge());
        System.out.println("Birthdate: " + getBirthdate());
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("role: " + getRoles());
    }
}
