package com.github.sats17.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sats17.model.Villans;

public interface VillanLayer extends JpaRepository<Villans, Integer> {

}
