package com.Organizer.Snacky.DBRepos;
import com.Organizer.Snacky.DbEnteiies.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
    public List<User> findAllByUserName(Iterable<String> iterable);
    public List<User> findAllByUserName(String userName);
    public User findByUserName(String userName);
}

