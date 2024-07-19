package sber.project.service;

import sber.project.entity.Base;
import sber.project.entity.User;
import sber.project.enums.Category;
import sber.project.enums.Repitable;
import sber.project.repository.BaseRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BaseServiceImpl implements BaseService {
    private BaseRepository baseRepository;

    @Autowired
    public void BaseService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<Base> getAllBase(User user) {
        List<Base> bases = (List<Base>) baseRepository.findAllByUser(user);
        if (bases.size() > 0) {
            return bases;
        } else {
            return new ArrayList<Base>();
        }
    }

    @Override
    public Base getBaseById(int id, User user) {
        Base base = baseRepository.findByIdAndUser(id, user);
        return base;
    }

    @Override
    @Transactional
    public Base createOrUpdateBase(Base base, User user) {
        if (base.getId() == 0) {
            base = baseRepository.save(base);
            return base;
        } else {
            Base baseOld = baseRepository.findByIdAndUser(base.getId(), user);
            baseOld.setId(base.getId());
            baseOld.setName(base.getName());
            baseOld.setTime(base.getTime());
            baseOld.setActive(base.getActive());
            baseOld.setRating(base.getRating());
            baseOld.setCategory(base.getCategory());
            baseOld.setRepeatable(base.getRepeatable());
            baseOld.setUser(user);
            baseRepository.save(baseOld);
            return baseOld;
        }
    }

    @Override
    @Transactional
    public void deleteBaseById(int id, User user) {
        Base base = baseRepository.findByIdAndUser(id, user);
        baseRepository.deleteById(id);
    }

    @Override
    public List<Base> findByActive(Boolean active, User user) {
        return baseRepository.findAllByActiveAndUser(active, user);
    }

    @Override
    public List<Base> findByTime(LocalDateTime time, User user) {
        return baseRepository.findByTimeBeforeAndUser(time,user);
    }

    @Override
    public List<Base> findByCategory(Category category, User user) {
        return baseRepository.findAllByCategoryAndUser(category, user);
    }

    @Override
    public List<Base> findByName(String search, User user) {
        return baseRepository.findByNameOrDescriptionAndUser(search, user);
    }

    @Override
    public List<Base> sortByRating(User user) {
        return baseRepository.findByUserOrderByRatingAsc(user);
    }

    @Override
    @Transactional
    public void nextTime(List<Base> bases) {
        for (Base bas : bases) {
            if (bas.getRepeatable() == Repitable.DAY) {
                bas.setTime(bas.getTime().plusDays(1));
            } else if (bas.getRepeatable() == Repitable.HOURS) {
                bas.setTime(bas.getTime().plusHours(1));
            } else if (bas.getRepeatable() == Repitable.WEEK) {
                bas.setTime(bas.getTime().plusWeeks(1));
            }
        }
    }
}
