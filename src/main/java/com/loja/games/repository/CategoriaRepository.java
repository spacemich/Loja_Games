package com.loja.games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.loja.games.model.LJGamesCategoria;

public interface CategoriaRepository extends JpaRepository<LJGamesCategoria, Long> {
    public List<LJGamesCategoria> findAllByNomeContainingIgnoringCase(@Param("nome") String nome);
}