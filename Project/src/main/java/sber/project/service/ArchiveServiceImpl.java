package sber.project.service;

import sber.project.entity.ArchiveTask;
import sber.project.entity.User;
import sber.project.repository.ArchiveRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    private ArchiveRepository archiveRepository;

    @Autowired
    public void ArchiveService(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArchiveTask> getAllArchive(User user) {
        List<ArchiveTask> bases = (List<ArchiveTask>) archiveRepository.findAllByUser(user);
        if (bases.size() > 0) {
            return bases;
        } else {
            return new ArrayList<ArchiveTask>();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ArchiveTask getArchiveById(int id, User user) {
        return archiveRepository.findByIdAndUser(id, user);
    }

    @Override
    @Transactional
    public ArchiveTask createOrUpdateArchive(ArchiveTask archiveTask, User user) {
        archiveTask = archiveRepository.save(archiveTask);
        return archiveTask;
    }

    @Override
    @Transactional
    public void deleteTaskArchiveById(int id, User user) {
        ArchiveTask base = archiveRepository.findByIdAndUser(id, user);
        archiveRepository.deleteById(id);
    }
}
