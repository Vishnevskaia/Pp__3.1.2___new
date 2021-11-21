package com.vishnevskaia311.service;

import com.vishnevskaia311.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @PersistenceContext
    private EntityManager entityManager;

    public RoleServiceImpl() {
    }

    public Set<Role> getRoleSet() {
        try {
            return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r", Role.class)
                    .getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
