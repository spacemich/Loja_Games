package com.loja.games.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.loja.games.model.LjGamesModel;
import com.loja.games.repository.lJGamesRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LjGamesController {

	@Autowired
	private lJGamesRepository repository;

	@GetMapping
	public List<LjGamesModel> findAll() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<LjGamesModel> findById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/plataforma/{plataforma}")
	private ResponseEntity<List<LjGamesModel>> getByPlataforma(@PathVariable String plataforma){
        return ResponseEntity.ok(repository.findAllByPlataformaContainingIgnoreCase(plataforma));
	
	}

	@PostMapping
	public ResponseEntity<LjGamesModel> save(@Valid @RequestBody LjGamesModel game) {
		return ResponseEntity.status(201).body(repository.save(game));
	}

	@PutMapping("/{id}")
	public ResponseEntity<LjGamesModel> update(@PathVariable Long id, @Valid @RequestBody LjGamesModel game) {
		return repository.findById(id).map(existingGame -> {
			game.setId(existingGame.getId());
			return ResponseEntity.ok(repository.save(game));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Optional<LjGamesModel> postagem = repository.findById(id);
		if (postagem.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		repository.deleteById(id);

	}

}
