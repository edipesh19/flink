package com.example.scope.demo.dao;

import com.example.scope.demo.model.UserDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserDAOService {

    private static Map<Integer, UserDTO> userMap = new HashMap<>();
    private static LocalDate ld = LocalDate.of(2017, 1, 13);

    private static int counter = 4;

    static {
        userMap.put(1, new UserDTO(1, "Dipesh", LocalDate.of(1990, 1, 14)));
        userMap.put(2, new UserDTO(2, "Sachin", LocalDate.of(1991, 2, 15)));
        userMap.put(3, new UserDTO(3, "Ram", LocalDate.of(1992, 3, 16)));
        userMap.put(4, new UserDTO(4, "Guru", LocalDate.of(1993, 4, 17)));
    }

    public List<UserDTO> findAll() {
        return userMap.values().stream().collect(Collectors.toList());
    }

    public UserDTO save(UserDTO user) {
        if (user.getId() == null) {
            user.setId(++counter);
        }
        userMap.put(user.getId(), user);
        return user;

    }

    public UserDTO findOne(int id) {
        return userMap.get(id);
    }

    public UserDTO deleteOne(int id) {
        return userMap.remove(id);
    }

}
