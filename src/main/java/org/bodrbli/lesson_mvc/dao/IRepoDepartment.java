package org.bodrbli.lesson_mvc.dao;

import org.bodrbli.lesson_mvc.model.Department;
import org.springframework.data.repository.CrudRepository;

public interface IRepoDepartment extends CrudRepository<Department, Integer> {

}
