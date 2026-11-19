package ir.maktabsharif.mapper;

import ir.maktabsharif.dto.SignupRequest;
import ir.maktabsharif.dto.UserResponse;
import ir.maktabsharif.model.UserInfoDao;

/**
 * UserInfoMapper
 */
public final class UserInfoMapper
{
    public static final UserResponse toResponse(UserInfoDao userInfoDao)
    {
        return new UserResponse(
                userInfoDao.getId(),
                userInfoDao.getUsername(),
                userInfoDao.getFullName(),
                userInfoDao.getAge());
    }

    public static final UserInfoDao toEntity(SignupRequest signupRequest)
    {
        return UserInfoDao.builder()
                .username(signupRequest.username())
                .password(signupRequest.password())
                .fullName(signupRequest.fullName())
                .age(signupRequest.age())
                .build();
    }
}