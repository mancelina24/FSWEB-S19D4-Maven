package com.workintech.s19d1.util;

import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;

import java.util.ArrayList;
import java.util.List;

public class ActorConverter {

    public static List<ActorResponse> actorResponseConvert(List<Actor> actors) {
        List<ActorResponse> actorResponses = new ArrayList<>();
        for(Actor actor:actors){
            actorResponses.add(new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate(), actor.getMovies())); //buraya alttakini yazdıktan sonra şöyle de yazabilriiz;  actorResponses.add(actorResponseConvert(actor));
        }
        return actorResponses;
    }

    public static ActorResponse actorResponseConvert(Actor actor) {
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate(), null);
    }

    public static ActorResponse actorCreateResponseConverter(Actor savedActor){
        return new ActorResponse(savedActor.getId(), savedActor.getFirstName(), savedActor.getLastName(), savedActor.getBirthDate(), savedActor.getMovies());
    }
}
