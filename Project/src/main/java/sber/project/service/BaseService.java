package sber.project.service;

import sber.project.entity.Base;
import sber.project.entity.User;
import sber.project.enums.Category;

import java.time.LocalDateTime;
import java.util.List;

public interface BaseService {
    List<Base> getAllBase(User user);

    Base getBaseById(int id, User user);

    Base createOrUpdateBase(Base base, User user);

    void deleteBaseById(int id, User user);

    List<Base> findByActive(Boolean active, User user);

    List<Base> findByTime(LocalDateTime time, User user);

    List<Base> findByCategory(Category category, User user);

    List<Base> findByName(String search, User user);

    List<Base> sortByRating(User user);

    void nextTime(List<Base> bases);
}
