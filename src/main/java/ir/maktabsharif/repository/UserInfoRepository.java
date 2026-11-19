package ir.maktabsharif.repository;

import java.util.List;
import java.util.Optional;

import ir.maktabsharif.model.UserInfoDao;

public interface UserInfoRepository
{
    List<UserInfoDao> findALl();

    Optional<UserInfoDao> findByUsername(String username);

    UserInfoDao create(UserInfoDao userInfoDao);
}
