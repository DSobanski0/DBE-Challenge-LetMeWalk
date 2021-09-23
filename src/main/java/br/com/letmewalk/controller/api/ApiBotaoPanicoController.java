package br.com.letmewalk.controller.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.letmewalk.model.BotaoPanico;
import br.com.letmewalk.repository.BotaoPanicoRepository;

@RestController
@RequestMapping("/api/panico")
public class ApiBotaoPanicoController {

	@Autowired
	BotaoPanicoRepository botaoPanicoRepository;
	
	@GetMapping
	public List<BotaoPanico> listAll() {
		return botaoPanicoRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<BotaoPanico> show(@PathVariable Long id) {
		return ResponseEntity.of(botaoPanicoRepository.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<BotaoPanico> create(@RequestBody BotaoPanico botaoPanico, UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/api/panico/{id}").buildAndExpand(botaoPanico.getId()).toUri();
		return ResponseEntity.created(uri).body(botaoPanicoRepository.save(botaoPanico));
	}
	
}
