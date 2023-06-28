package com.example.ordermanagement.service;

import com.example.ordermanagement.model.DTO.UserLoginDTO;
import com.example.ordermanagement.model.UserRole;
import com.example.ordermanagement.model.Users;
import com.example.ordermanagement.security.UserPrinciple;
import com.example.ordermanagement.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutheticationServiceImpl implements AutheticationService {

     @Autowired
     private AuthenticationManager authenticationManager;
     @Autowired
     private UserService userService;

     @Autowired
     private JwtProvider jwtProvider;



    /**
     * Signs in and returns JWT token
     *
     * @param signInRequest User with specified username and password to sign in
     * @return Signed in User
     */
    @Override
    public UserLoginDTO signInAndReturnJWT(Users signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword())
        );

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrinciple);

        Users signInUser = userPrinciple.getUsers();
        signInUser.setToken(jwt);
        UserLoginDTO userLoginDto = UserLoginDTO.mapToDto(signInUser);

        return userLoginDto;
    }

    /**
     * Checks if current User has ADMIN role.
     *
     * @return "true" if user has Admin role, "false" if user has other role.
     */
    @Override
    public boolean isOwner() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        Optional<Users> currentUserEntityOptional = userService.searchUserName(currentUserName);
        if (currentUserEntityOptional.isPresent()) {
            Users currentUserEntity = currentUserEntityOptional.get();
            if (currentUserEntity.getUserRole().equals(UserRole.OWNER)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if client is logged in.
     * @return "true" if client is logged in, "false" if client is not logged in
     */
    @Override
    public boolean idLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
    }

}
