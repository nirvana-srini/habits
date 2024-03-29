package com.nirvana.habits.journal.user;

import com.nirvana.habits.journal.webclient.MovieDataBaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private MovieDataBaseClient movieDataBaseClient;

    public UserController(MovieDataBaseClient movieDataBaseClient) {
        this.movieDataBaseClient = movieDataBaseClient;
    }

    @RequestMapping("/user/movies")
    public String getMovie(){
        return movieDataBaseClient.getMovies();
    }
}
