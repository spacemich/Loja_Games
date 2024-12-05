package com.loja.games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.loja.games.model.LjGamesModel;

public interface lJGamesRepository extends JpaRepository<LjGamesModel,Long>{
    public List<LjGamesModel>findAllByPlataformaContainingIgnoreCase(@Param("plataforma") String plataforma);
}