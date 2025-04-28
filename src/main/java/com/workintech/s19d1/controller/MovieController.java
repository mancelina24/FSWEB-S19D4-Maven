package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;

    @GetMapping
    public List<Movie> findAll(){
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id){
        return movieService.findById(id);
    }

    @PostMapping
    public Movie save(@RequestBody Movie movie){
        return movieService.save(movie);
    }
    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie movie){
        return movieService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Movie deletedMovie=findById(id);
        movieService.delete(deletedMovie);
    }


}
