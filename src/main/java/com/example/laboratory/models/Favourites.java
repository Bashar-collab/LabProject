package com.example.laboratory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Favourites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @NotBlank
    @Column(name = "favourite_id")
    private Long favouriteId;

    @NotBlank
    @Column(name = "favourite_type")
    private Long favouriteType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Long getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(Long favouriteId) {
        this.favouriteId = favouriteId;
    }

    public Long getFavouriteType() {
        return favouriteType;
    }

    public void setFavouriteType(Long favouriteType) {
        this.favouriteType = favouriteType;
    }
}
