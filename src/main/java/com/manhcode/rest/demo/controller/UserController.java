package com.manhcode.rest.demo.controller;

import com.manhcode.rest.demo.dao.PostRespository;
import com.manhcode.rest.demo.dao.UserRepository;
import com.manhcode.rest.demo.entity.Post;
import com.manhcode.rest.demo.entity.User;
import com.manhcode.rest.demo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
//    @Autowired
//    private UserDaoService userDaoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRespository postRepository;

    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable int id) {
        Optional<User> rs = userRepository.findById(id);
        if (!rs.isPresent()) {
            throw  new UserNotFoundException("User with id: "+id+ " Not found!");
        }
        User user = rs.get();
        //HEATOAS
        EntityModel<User> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).findAll());

        resource.add(linkTo.withRel("all-users"));
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User newUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> findAllPostOfUser(@PathVariable int id) {
        Optional<User> rs = userRepository.findById(id);
        if (!rs.isPresent()) {
            throw  new UserNotFoundException("User with id: "+id+ " Not found!");
        }
        User user = rs.get();
        //HEATOAS
        EntityModel<User> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).findAll());

        resource.add(linkTo.withRel("all-users"));
        return user.getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> savePost(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> rs = userRepository.findById(id);
        if (!rs.isPresent()) {
            throw  new UserNotFoundException("User with id: "+id+ " Not found!");
        }
        User user = rs.get();

        post.setUser(user);
        user.addPost(post);

        postRepository.save(post);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
