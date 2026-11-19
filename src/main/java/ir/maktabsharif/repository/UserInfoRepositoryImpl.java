package ir.maktabsharif.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ir.maktabsharif.model.UserInfoDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserInfoDao> findALl()
    {
        return this.entityManager.createQuery("FROM UserInfoDao", UserInfoDao.class).getResultList();
    }

    @Override
    public Optional<UserInfoDao> findByUsername(String username)
    {
        return this.entityManager.createQuery("FROM UserInfoDao u WHERE u.username =: username", UserInfoDao.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    @Override
    public UserInfoDao create(UserInfoDao userInfoDao)
    {
        this.entityManager.persist(userInfoDao);
        return userInfoDao;
    }

}
