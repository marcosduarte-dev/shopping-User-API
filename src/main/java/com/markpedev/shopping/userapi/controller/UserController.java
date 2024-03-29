package com.markpedev.shopping.userapi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.markpedev.shopping.userapi.config.UserRunnable;
import com.markpedev.shopping.userapi.config.UserRunnableManager;
import com.markpedev.shopping.userapi.dto.UserDTO;
import com.markpedev.shopping.userapi.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService; 
	
	@GetMapping
	public List<UserDTO> getUser() {
		return userService.getAll();
	}
	
	@GetMapping("/pageable")
	public Page<UserDTO> getUsersPage(Pageable pageable) { 
		return userService.getAllPage(pageable);
	}

	@GetMapping("/cpf/{cpf}")
	public UserDTO getUserByCpf(@PathVariable String cpf) {
		return userService.findByCpf(cpf);
	}
	
	@GetMapping("/{id}")
	public UserDTO findById(@PathVariable Long id) { 
		return userService.findById(id);
	}
	
	@GetMapping("/search")
	public List<UserDTO> queryByName(@RequestParam(name="nome", required = true) String nome) {
		return userService.queryByName(nome);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO add(@RequestBody @Valid UserDTO userDTO) {
		UserDTO savedUser = userService.save(userDTO);
		
		UserRunnable userRunnable = UserRunnableManager.getUserRunnable();
		
		 if (userRunnable != null) {
			 userRunnable.run();
		 }
		
		return savedUser;
	}
	
	@PatchMapping("/{id}")
	public UserDTO editUser(@PathVariable Long id,@RequestBody UserDTO userDTO) { 
		return userService.editUser(id, userDTO);
	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id)/* throws UserNotFoundException*/{
		userService.delete(id);
	}
}
