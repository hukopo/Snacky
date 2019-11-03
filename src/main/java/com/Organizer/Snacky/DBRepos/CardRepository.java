package com.Organizer.Snacky.DBRepos;
import com.Organizer.Snacky.DbEnteiies.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
