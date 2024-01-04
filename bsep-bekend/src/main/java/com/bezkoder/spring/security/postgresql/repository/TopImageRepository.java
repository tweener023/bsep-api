package com.bezkoder.spring.security.postgresql.repository;

import com.bezkoder.spring.security.postgresql.models.Guitar;
import com.bezkoder.spring.security.postgresql.models.TopImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopImageRepository extends CrudRepository<TopImage, Long> {

    TopImage findOneByGuitar(Guitar guitar);
}