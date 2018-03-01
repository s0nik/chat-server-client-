
package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    public UserDetails getByUsername(String username) throws Exception;
}
