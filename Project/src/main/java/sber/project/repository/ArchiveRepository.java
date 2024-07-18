package sber.project.repository;

import sber.project.entity.ArchiveTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends JpaRepository<ArchiveTask, Integer> {}