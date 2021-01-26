package com.sda.cats.repository;

import com.sda.cats.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {



}
