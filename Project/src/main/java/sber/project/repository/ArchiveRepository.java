package sber.project.repository;

import sber.project.entity.ArchiveTask;
import sber.project.entity.Base;
import sber.project.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ArchiveRepository extends JpaRepository<ArchiveTask, Integer> {
    List<ArchiveTask> findAllByUser(User user);
    ArchiveTask findByIdAndUser(Integer id, User user);
    @Query(value = "select u from ArchiveTask u where (u.name = %?1% or u.description = %?1%) and u.user = ?2")
    List<Base> findByNameOrDescriptionAndUser(String search, User user);
}