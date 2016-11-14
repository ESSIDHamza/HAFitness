package application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entites.Fiche;

public interface GestionFicheRepository extends JpaRepository<Fiche, Integer> {
}