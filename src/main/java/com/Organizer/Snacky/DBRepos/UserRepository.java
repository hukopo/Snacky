package com.Organizer.Snacky.DBRepos;
import com.Organizer.Snacky.DbEnteiies.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    public Iterable<User> findAllByUserName(String userName);
    public Iterable<User> findAllByUserName(Iterable<String> userNames);
    public User findByUserName(String userName);
}

