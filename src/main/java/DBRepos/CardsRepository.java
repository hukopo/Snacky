package DBRepos;
import DbEnteiies.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardsRepository extends JpaRepository<Card, Integer> {
    Card Add(Card card);
}
