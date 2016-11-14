package application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entites.Coache;

public interface GestionCoacheRepository extends JpaRepository<Coache, Integer> {
}