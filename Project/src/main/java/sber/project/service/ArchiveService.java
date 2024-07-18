package sber.project.service;

import sber.project.entity.ArchiveTask;
import java.util.List;

public interface ArchiveService {
    List<ArchiveTask> getAllArchive();

    ArchiveTask getArchiveById(int id);

    ArchiveTask createOrUpdateArchive(ArchiveTask archiveTask);

    void deleteTaskArchiveById(int id);
}