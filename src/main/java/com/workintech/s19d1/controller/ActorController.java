package com.workintech.s19d1.controller;


import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.repository.ActorRepository;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.service.ActorServiceImpl;
import com.workintech.s19d1.util.ActorConverter;
import com.workintech.s19d1.util.HollywoodValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    private ActorService actorService;

@Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> findAll(){
    return actorService.findAll();
    }

    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable Long id){
        Actor actor =actorService.findById(id);
        return ActorConverter.actorResponseConvert(actor);
    }
    @PostMapping
    public ActorResponse save(@Validated @RequestBody ActorRequest actorRequest){
        Actor actor = actorRequest.getActor();
        List<Movie> movies = actorRequest.getMovies();
        for(Movie movie:movies){
            actor.addMovie(movie);
        }
        Actor savedActor = actorService.save(actor);
        return ActorConverter.actorCreateResponseConverter(savedActor);
    }
    @PutMapping("/{id}")
    public ActorResponse update(@PathVariable long id, @RequestBody Actor actor){
        Actor foundActor = actorService.findById(id);
        actor.setMovies(foundActor.getMovies());
        actor.setId(id);
        actorService.save(actor);
        return ActorConverter.actorResponseConvert(actor);
    }

    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable Long id)
    {
        Actor deletedActor = actorService.findById(id);
        actorService.delete(deletedActor);
        return ActorConverter.actorResponseConvert(deletedActor);
    }

}
