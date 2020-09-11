package controller;

import java.util.List;

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

import domain.Cnpj;
import repository.CnpjRepository;

@RestController
@RequestMapping("/cnpj")
public class CnpjController {
	
	@Autowired
	private CnpjRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Cnpj>> getCnpj(){
		
		List<Cnpj> todos = (List<Cnpj>)repository.findAll();
		
		return ResponseEntity.ok().body(todos);
	}
	
	@GetMapping("/{cnpj}")
	public ResponseEntity<Cnpj> getCnpj(@PathVariable String cnpj){
		
		Cnpj cnpj1 = repository.findByCnpj(cnpj);
		
		if (cnpj1 != null) {
			return new ResponseEntity<>(cnpj1, HttpStatus.OK);	
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping
	public ResponseEntity<Cnpj> postCpf(@RequestBody Cnpj novo){
		Cnpj existente = repository.findByCnpj(novo.getCnpj());
		
		if (existente != null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		repository.save(novo);
		return new ResponseEntity<>(novo, HttpStatus.CREATED);	
	}
	
	@PutMapping("/{Cnpj}")
	public ResponseEntity<Cnpj> put(@RequestBody Cnpj modificado, @PathVariable String Cnpj){
		
		Cnpj existente = repository.findByCnpj(Cnpj);
		existente.setNome(modificado.getNome());
		existente.setSituacao(modificado.getSituacao());
		
		repository.save(existente);
		
		return ResponseEntity.ok().body(existente);	
	}
	@DeleteMapping("/{cnpj}")
	public ResponseEntity<Cnpj> deleteCnpj(@PathVariable String cnpj) {
		
		Cnpj existente = repository.findByCnpj(cnpj);
		
		if (existente != null) {
			repository.delete(existente);			
			return new ResponseEntity<>(existente, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
