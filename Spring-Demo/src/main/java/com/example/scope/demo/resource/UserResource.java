package com.example.scope.demo.resource;

import com.example.scope.demo.dao.UserDAOService;
import com.example.scope.demo.exception.UserNotFoundException;
import com.example.scope.demo.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private UserDAOService userDAOService;

    //retrive All user
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userDAOService.findAll();
    }

    // retrive specific user
    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable int id) {
        UserDTO user = userDAOService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("id - " + id);
        }

        // HATEOAS
        //Resource<UserDTO>
        return user;
    }

    // create User
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO user) {
        // 1. Validation
//        Set<ConstraintViolation<UserDTO>> constraintViolations = validator.validate(user);
//        if (constraintViolations.size() > 0) {
//            throw new BadRequestException(constraintViolations.iterator().next().getMessage());
//        }

        // 2. Create
        UserDTO savedUser = userDAOService.save(user);

        // 3. Response Building
        // Created
        // /users/{id}
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri();
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        UserDTO user = userDAOService.deleteOne(id);
        if (user == null) {
            throw new UserNotFoundException("id - " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
