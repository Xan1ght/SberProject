package sber.project.service;

import sber.project.entity.ArchiveTask;
import sber.project.repository.ArchiveRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    private ArchiveRepository archiveRepository;

    @Autowired
    public void ArchiveService(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Override
    public List<ArchiveTask> getAllArchive() {
        List<ArchiveTask> bases = (List<ArchiveTask>) archiveRepository.findAll();
        if (bases.size() > 0) {
            return bases;
        } else {
            return new ArrayList<ArchiveTask>();
        }
    }

    @Override
    public ArchiveTask getArchiveById(int id) {
        Optional<ArchiveTask> base = archiveRepository.findById(id);
        if (base.isPresent()) {
            return base.get();
        } else {
            System.out.println("запись не найдена");
            return new ArchiveTask();
        }
    }

    @Override
    public ArchiveTask createOrUpdateArchive(ArchiveTask archiveTask) {
        if (archiveTask.getId() == 0) {
            archiveTask = archiveRepository.save(archiveTask);
            return archiveTask;
        } else {
            Optional<ArchiveTask> baseOld = archiveRepository.findById(archiveTask.getId());
            if (baseOld.isPresent()) {
                ArchiveTask newBase = baseOld.get();
                newBase.setName(archiveTask.getName());
                newBase.setTime(archiveTask.getTime());
                newBase.setActive(archiveTask.getActive());
                newBase.setRating(archiveTask.getRating());
                archiveRepository.save(newBase);
                return newBase;
            } else {
                archiveTask = archiveRepository.save(archiveTask);
                return archiveTask;
            }
        }
    }

    @Override
    public void deleteTaskArchiveById(int id) {
        Optional<ArchiveTask> base = archiveRepository.findById(id);
        if (base.isPresent()) {
            archiveRepository.deleteById(id);
        } else {
            System.out.println("Такого задания нет");
        }
    }
}
