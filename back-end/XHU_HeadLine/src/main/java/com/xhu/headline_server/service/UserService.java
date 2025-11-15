package com.xhu.headline_server.service;

import com.xhu.headline_server.entity.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    User getUserById(Long id);

    void saveUser(User user) ;

    boolean delUserById(Long id);

    void addUser(User user);
}
