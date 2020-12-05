package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean save(User user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean update(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String login) {
        if (userRepository.existsByLogin(login)) {
            return userRepository.findByLogin(login);
        }
        return null;
    }
}
