package com.tzy.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "allowed_resource")
    private String allowedResource;

    @Column(name = "allowed_read")
    private boolean allowedRead;

    @Column(name = "allowed_create")
    private boolean allowedCreate;

    @Column(name = "allowed_update")
    private boolean allowedUpdate;


    @Column(name = "allowed_delete")
    private boolean allowedDelete;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return id == role.id &&
                allowedRead == role.allowedRead &&
                allowedCreate == role.allowedCreate &&
                allowedUpdate == role.allowedUpdate &&
                allowedDelete == role.allowedDelete &&
                Objects.equals(name, role.name) &&
                Objects.equals(allowedResource, role.allowedResource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, allowedResource, allowedRead, allowedCreate, allowedUpdate, allowedDelete);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public String getAllowedResource() {
        return allowedResource;
    }

    public void setAllowedResource(String allowedResource) {
        this.allowedResource = allowedResource;
    }

    public boolean isAllowedRead() {
        return allowedRead;
    }

    public void setAllowedRead(boolean allowedRead) {
        this.allowedRead = allowedRead;
    }

    public boolean isAllowedCreate() {
        return allowedCreate;
    }

    public void setAllowedCreate(boolean allowedCreate) {
        this.allowedCreate = allowedCreate;
    }

    public boolean isAllowedUpdate() {
        return allowedUpdate;
    }

    public void setAllowedUpdate(boolean allowedUpdate) {
        this.allowedUpdate = allowedUpdate;
    }

    public boolean isAllowedDelete() {
        return allowedDelete;
    }

    public void setAllowedDelete(boolean allowedDelete) {
        this.allowedDelete = allowedDelete;
    }


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}
