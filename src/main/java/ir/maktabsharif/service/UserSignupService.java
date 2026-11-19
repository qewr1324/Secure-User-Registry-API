package ir.maktabsharif.service;

import java.util.List;
import java.util.Optional;

import ir.maktabsharif.dto.SignupRequest;
import ir.maktabsharif.dto.UserResponse;

public interface UserSignupService
{
    List<UserResponse> findALl();

    Optional<UserResponse> findByUsername(String username);

    UserResponse signup(SignupRequest signupRequest);
}
