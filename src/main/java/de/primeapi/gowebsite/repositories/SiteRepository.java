package de.primeapi.gowebsite.repositories;

import de.primeapi.gowebsite.entitites.Site;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Integer> {

    Site findByName(String name);

    List<Site> findAll();

}