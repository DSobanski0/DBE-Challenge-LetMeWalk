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

import br.com.letmewalk.model.User;
import br.com.letmewalk.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping
	public List<User> listAll() {
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> show(@PathVariable Long id) {
		return ResponseEntity.of(userRepository.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
		userRepository.save(user);
		URI uri = uriBuilder.path("api/user/create/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User newUser) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(optionalUser.isEmpty()) return ResponseEntity.notFound().build();
		
		User user = optionalUser.get();
		newUser.setId(user.getId());
		return ResponseEntity.ok(userRepository.save(newUser));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(optionalUser.isEmpty()) return ResponseEntity.notFound().build();
		
		userRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
