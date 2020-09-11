package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Cpf;
import repository.CpfRepository;

@RestController
@RequestMapping("/cpf")

public class CpfController {
	

	
	@Autowired
	private CpfRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Cpf>> getCpf(){
		
		List<Cpf> todos = (List<Cpf>)repository.findAll();
		
		return ResponseEntity.ok().body(todos);
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<Cpf> getCpf(@PathVariable String cpf){
		
		Cpf cpf1 = repository.findByCpf(cpf);
		
		if (cpf1 != null) {
			return new ResponseEntity<>(cpf1, HttpStatus.OK);	
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping
	public ResponseEntity<Cpf> postCpf(@Valid @RequestBody Cpf novo){
		Cpf existente = repository.findByCpf(novo.getCpf());
		
		if (existente != null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		repository.save(novo);
		return new ResponseEntity<>(novo, HttpStatus.CREATED);	
	}
	
	@PutMapping("/{Cpf}")
	public ResponseEntity<Cpf> put(@RequestBody Cpf modificado, @PathVariable String Cpf){
		
		Cpf existente = repository.findByCpf(Cpf);
		existente.setNome(modificado.getNome());
		existente.setSituacao(modificado.getSituacao());
		
		repository.save(existente);
		
		return ResponseEntity.ok().body(existente);	
	}
	
	@DeleteMapping("/{cpf}")
	public ResponseEntity<Cpf> deleteCpf(@PathVariable String cpf) {
		
		Cpf existente = repository.findByCpf(cpf);
		
		if (existente != null) {
			repository.delete(existente);			
			return new ResponseEntity<>(existente, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	

}
