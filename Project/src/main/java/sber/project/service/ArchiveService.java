package sber.project.service;


import sber.project.entity.ArchiveTask;
import sber.project.entity.User;
import java.util.List;

public interface ArchiveService {
    List<ArchiveTask> getAllArchive(User user);

    ArchiveTask getArchiveById(int id, User user);

    ArchiveTask createOrUpdateArchive(ArchiveTask archiveTask, User user);

    void deleteTaskArchiveById(int id, User user);
}