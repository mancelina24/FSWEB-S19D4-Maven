package com.workintech.s19d1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "movie", schema = "fsweb")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;


    @Column(name = "name")
    @NotBlank(message = "Film adı boş olamaz.")
    @Size(min = 2, max = 255, message = "Film adı 2 ile 255 karakter arasında olmalıdır.")
    private String name;


    @Column(name = "director_name")
    @NotNull(message = "yönetmen adı null olamaz")
    private String directorName;

    @Column(name = "rating")
    @Positive(message = "Rating pozitif bir sayı olmalıdır.")
    private Integer rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name="movie_actor",
                schema="fsweb",
                joinColumns = @JoinColumn(name = "movie_id"),
                inverseJoinColumns = @JoinColumn(name="actor_id"))
    private List<Actor> actors = new ArrayList<>();

    public void addActor(Actor actor){
        actors.add(actor);
    }
}