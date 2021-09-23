package br.com.letmewalk.controller.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.letmewalk.model.Contato;
import br.com.letmewalk.repository.ContatoRepository;

@RestController
@RequestMapping("/api/contatos")
public class ApiContatoController {

	@Autowired
	ContatoRepository contatoRepository;
	
	@GetMapping
	public List<Contato> listAll() {
		return contatoRepository.findAll();
	}

	@GetMapping("{contatoId}")
	public ResponseEntity<Contato> show(@PathVariable Long contatoId) {
		return ResponseEntity.of(contatoRepository.findById(contatoId));
	}
	
	@PostMapping
	public ResponseEntity<Contato> create(@RequestBody @Valid Contato contato, UriComponentsBuilder uriBuilder) {
		contatoRepository.save(contato);
		
		URI uri = uriBuilder.path("api/contatos/{contatoId}").buildAndExpand(contato.getId()).toUri();
		
		return ResponseEntity.created(uri).body(contato);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato newContato) {
		Optional<Contato> optionalContato = contatoRepository.findById(id);
		
		if(optionalContato.isEmpty()) return ResponseEntity.notFound().build();
		
		Contato contato = optionalContato.get();
		newContato.setId(contato.getId());
		return ResponseEntity.ok(contatoRepository.save(newContato));
	}

	@DeleteMapping("/{contatoId}")
	public ResponseEntity<Contato> delete(@PathVariable Long contatoId) {
		Optional<Contato> optionalContato = contatoRepository.findById(contatoId);
		if(optionalContato.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		contatoRepository.delete(optionalContato.get());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
