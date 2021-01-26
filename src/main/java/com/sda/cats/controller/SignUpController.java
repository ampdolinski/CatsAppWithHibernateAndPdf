package com.sda.cats.controller;

import com.sda.cats.model.SignUpRequest;
import com.sda.cats.service.EmailSender;
import com.sda.cats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/register")
public class SignUpController {

    @Autowired
    private UserService userService;


    @Autowired
    private EmailSender emailSender;


    @PostMapping
    public ResponseEntity register(@RequestBody SignUpRequest signUpRequest){

        userService.createUser(signUpRequest);

        emailSender.sendMail("javagda23mail@gmail.com", "[ADAM] Account created",
                "What's the meaning of STOOO-NHEEEEEEENGE!, for: " + signUpRequest.getLogin());

        /*GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");

        Authentication authentication = new
                UsernamePasswordAuthenticationToken(signUpRequest.getLogin(), signUpRequest.getPassword(),
                List.of(authority)
//                Collections.singletonList(authority)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);*/

        return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
    }


}
