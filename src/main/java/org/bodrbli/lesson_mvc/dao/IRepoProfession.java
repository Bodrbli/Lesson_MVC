package org.bodrbli.lesson_mvc.dao;

import org.bodrbli.lesson_mvc.model.Profession;
import org.springframework.data.repository.CrudRepository;

public interface IRepoProfession extends CrudRepository<Profession, Integer> {
}
