package com.Organizer.Snacky.DBRepos;

import com.Organizer.Snacky.DbEnteiies.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
}
