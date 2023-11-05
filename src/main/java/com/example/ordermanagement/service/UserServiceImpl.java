package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserDetailsDTO;
import com.example.ordermanagement.model.DTO.UserListDTO;
import com.example.ordermanagement.model.DTO.UserRegisterDTO;
import com.example.ordermanagement.model.UserRole;
import com.example.ordermanagement.model.User;
import com.example.ordermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserRegisterDTO saveUser(User user) {

        if (user.getUserName().equals("My business") && user.getPassword().equals("password")){
            user.setUserRole(UserRole.OWNER);
        }else {
            user.setUserRole(UserRole.USER);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.saveAndFlush(user);
        return UserRegisterDTO.mapToDto(savedUser);
    }

    @Override
    public Optional<User> searchUserName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    public Optional<User> searchUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> serachById(Long ownerId) {
        return userRepository.findById(ownerId);
    }

    @Override
    public List<UserListDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return UserListDTO.mapToDto(allUsers);
    }

    @Override
    public UserDetailsDTO updateById(UserDetailsDTO detailsDto, Long id) {

        Optional<User> editOptional = serachById(id);
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
        User saveEditUser = userRepository.save(editOptional.get());
        System.out.println(" after saveEditUser = " + saveEditUser);

        return UserDetailsDTO.mapToDto(saveEditUser);
    }

    @Override
    public void delete(Long appId) {
       userRepository.deleteById(appId);
    }
}

