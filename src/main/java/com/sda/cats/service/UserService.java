package com.sda.cats.service;

import com.sda.cats.exception.CatNotFoundException;
import com.sda.cats.model.Profile;
import com.sda.cats.model.Role;
import com.sda.cats.model.SignUpRequest;
import com.sda.cats.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class UserService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void createUser(SignUpRequest signUpRequest) {

        // 1. sposób
        //        profileRepository.findById(signUpRequest.getLogin())
        //        .ifPresent(i -> {throw new CatNotFoundException();
        //        });

        // 2. sposób - ZAPOMNIEĆ!!!
        //        Optional<Profile> existingProfile = profileRepository.findById(signUpRequest.getLogin());
        //
        //        if (!existingProfile.isPresent()) {
        //            //create
        //        } else {
        //            throw new CatNotFoundException();
        //        }

//        Profile profile = new Profile();
//                    profile.setUsername(signUpRequest.getLogin());
//                    profile.setPassword(encoder.encode(signUpRequest.getPassword()));
//                    Role role =  new Role();
//                    role.setName("ROLE_USER");
//                    profile.setRoles(Collections.singletonList(role));
//                    profileRepository.save(profile);

        // 3. sposób - tak ma być :)

        profileRepository.findById(signUpRequest.getLogin())
                .orElseGet(() -> {
                    Profile profile = new Profile();
                    profile.setUsername(signUpRequest.getLogin());
                    profile.setPassword(encoder.encode(signUpRequest.getPassword()));
                    Role role =  new Role();
                    role.setName("ROLE_ADMIN");
                    profile.setRoles(Collections.singletonList(role));
                    return profileRepository.save(profile);
                }
            );


    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return profileRepository.findById(username)
                .map(existingProfile -> new User(existingProfile.getUsername(),
                        existingProfile.getPassword(),
                        parseRoles(existingProfile.getRoles())))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private Collection<? extends GrantedAuthority> parseRoles(List<Role> roles) {
        return roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toSet());
    }
}
