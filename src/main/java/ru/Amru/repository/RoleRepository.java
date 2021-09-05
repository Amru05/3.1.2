package ru.Amru.repository;

import org.springframework.data.repository.CrudRepository;
import ru.Amru.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
