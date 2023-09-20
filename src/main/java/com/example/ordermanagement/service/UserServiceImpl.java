package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserDetailsDTO;
import com.example.ordermanagement.model.DTO.UserListDTO;
import com.example.ordermanagement.model.DTO.UserRegisterDTO;
import com.example.ordermanagement.model.UserRole;
import com.example.ordermanagement.model.Users;
import com.example.ordermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

 @Autowired
    private UserRepository userRepository;
 @Autowired
 private PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterDTO saveUser(Users users) {

        if (users.getUserName().equals("My business") && users.getPassword().equals("password")){
            users.setUserRole(UserRole.OWNER);
        }else {
            users.setUserRole(UserRole.USER);
        }

        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUser = userRepository.saveAndFlush(users);
        return UserRegisterDTO.mapToDto(savedUser);
    }

    @Override
    public Optional<Users> searchUserName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public Optional<Users> searchUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<Users> serachById(Long ownerId) {
        return userRepository.findById(ownerId);
    }

    @Override
    public List<UserListDTO> getAllUsers() {
        List<Users> allUsers = userRepository.findAll();
        return UserListDTO.mapToDto(allUsers);
    }

    @Override
    public UserDetailsDTO updateById(UserDetailsDTO detailsDto, Long id) {

        Optional<Users> editOptional = serachById(id);
        if (editOptional.isEmpty()) {
            return null;
        }

        detailsDto.setUserName(detailsDto.getFirstName());
        detailsDto.setEmail(detailsDto.getEmail());
        detailsDto.setFirstName(detailsDto.getFirstName());
        detailsDto.setLastName(detailsDto.getLastName());
        detailsDto.setPassword(passwordEncoder.encode(detailsDto.getPassword()));
        detailsDto.setPassword(detailsDto.getPassword());

        System.out.println("editUsers after = " + detailsDto);
        Users saveEditUser = userRepository.save(editOptional.get());
        System.out.println(" after saveEditUser = " + saveEditUser);

        return UserDetailsDTO.mapToDto(saveEditUser);
    }

    @Override
    public void delete(Long appId) {
       userRepository.deleteById(appId);
    }
}

