package com.Organizer.Snacky.DBRepos;
import com.Organizer.Snacky.DbEnteiies.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    public List<User> findAllByUserName(Iterable<String> iterable);
    public List<User> findAllByUserName(String userName);
    public Optional<User> findByUserName(String userName);
}

