package com.github.sats17.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.sats17.model.Heros;

@Repository
public interface HeroLayer extends JpaRepository<Heros, Integer>{

}
