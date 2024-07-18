package sber.project.repository;

import sber.project.entity.Base;
import sber.project.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BaseRepository extends JpaRepository<Base, Integer> {
    List<Base> findAllByActive(Boolean active);
    List<Base> findAllByTime(LocalDateTime time);
    List<Base> findAllByCategory(Category category);
    @Query(value = "select u from Base u where u.name = %?1% or u.description = %?1%")
    List<Base> findByNameOrDescription(String search);
    @Query("select b from Base b order by b.rating asc")
    List<Base> findAllOrderByRatingAsc();
}