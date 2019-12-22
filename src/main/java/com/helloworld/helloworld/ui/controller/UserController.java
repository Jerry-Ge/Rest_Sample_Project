package com.helloworld.helloworld.ui.controller;
import com.helloworld.helloworld.model.request.UpdateUserDetailRequestModel;
import com.helloworld.helloworld.model.request.UserDetailRequestModel;
import com.helloworld.helloworld.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    Map<String, UserRest> users;

    /* Return all Users in DB */
    @GetMapping()
    public String getUser(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                          @RequestParam(value = "limit", defaultValue = "50") int limit,
                          @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort)
    {
        return "get user was called with page:" + page + " and limit=" + limit + " and sort=" + sort;
    }


    /* Return a single User given Id */
    @GetMapping(path="/{userId}",
                produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE
                })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId)
    {

        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /* Register a new User */
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailRequestModel userDetails)
    {
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        String userId = UUID.randomUUID().toString();
        returnValue.setUserId(userId);
        if (users == null) {
            users = new HashMap<>();
        }

        users.put(userId, returnValue);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }

    @PutMapping(path="/{userId}",
                consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailRequestModel userDetails)
    {
        UserRest storedUserDetails = users.get(userId);

        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        return storedUserDetails;
    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId)
    {
        users.remove(userId);

        return ResponseEntity.noContent().build();
    }
}
