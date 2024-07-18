package sber.project.service;

import sber.project.entity.Base;
import sber.project.enums.Category;
import sber.project.enums.Repitable;

import java.time.LocalDateTime;
import java.util.List;

public interface BaseService {
    List<Base> getAllBase();

    Base getBaseById(int id);

    Base createOrUpdateBase(Base base);

    void deleteBaseById(int id);

    List<Base> findByActive(Boolean active);

    List<Base> findByTime(LocalDateTime time);

    List<Base> findByName(String search);

    List<Base> findByCategory(Category category);

    List<Base> sortByRating();

    void nextTime(List<Base> bases);
}
