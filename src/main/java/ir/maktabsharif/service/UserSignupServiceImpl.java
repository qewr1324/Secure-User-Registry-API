package ir.maktabsharif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ir.maktabsharif.dto.SignupRequest;
import ir.maktabsharif.dto.UserResponse;
import ir.maktabsharif.exception.UsernameAlreadyExistsException;
import ir.maktabsharif.mapper.UserInfoMapper;
import ir.maktabsharif.model.UserInfoDao;
import ir.maktabsharif.repository.UserInfoRepository;
import jakarta.transaction.Transactional;

@Service
public class UserSignupServiceImpl implements UserSignupService
{

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserSignupServiceImpl(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder)
    {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserResponse> findALl()
    {
        return this.userInfoRepository.findALl().stream()
                .map(UserInfoMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<UserResponse> findByUsername(String username)
    {
        return this.userInfoRepository.findByUsername(username)
                .map(UserInfoMapper::toResponse);
    }

    @Transactional
    @Override
    public UserResponse signup(SignupRequest sighnupRequest)
    {
        if (this.findByUsername(sighnupRequest.username()).isPresent())
            throw new UsernameAlreadyExistsException(sighnupRequest.username());

        UserInfoDao entity = UserInfoMapper.toEntity(sighnupRequest);

        String encodedPassword = this.passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);

        return UserInfoMapper.toResponse(
                this.userInfoRepository.create(entity));
    }

}
