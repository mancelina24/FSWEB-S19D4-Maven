package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.repository.MovieRepository;
import com.workintech.s19d1.util.HollywoodValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService{


    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(()-> new ApiException("Movie is not found with id: "+id, HttpStatus.NOT_FOUND));
    }
    @Transactional
    @Override
    public Movie update(Long id, Movie movie) {
        Movie updatedMovie = findById(id);
        updatedMovie.setId(movie.getId());
        updatedMovie.setRating(movie.getRating());
        updatedMovie.setReleaseDate(movie.getReleaseDate());
        updatedMovie.setName(movie.getName());
        updatedMovie.setDirectorName(movie.getDirectorName());
        updatedMovie.setActors(movie.getActors());
        return movieRepository.save(updatedMovie);
    }

    @Transactional
    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }
    @Transactional
    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);

    }
}
