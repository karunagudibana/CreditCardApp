package com.cred.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cred.model.Cred;

@Repository
public interface CredRepository extends JpaRepository<Cred, Integer> {
}
