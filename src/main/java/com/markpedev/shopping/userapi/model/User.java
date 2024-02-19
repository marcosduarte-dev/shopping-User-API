package com.markpedev.shopping.userapi.model;

import java.time.LocalDateTime;

import com.markpedev.shopping.userapi.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cpf;
	private String endereco;
	private String email;
	private String telefone;
	private LocalDateTime dataCadastro;
	
	public static User convert(UserDTO userDTO) {
		User user = new User();
		user.setNome(userDTO.getNome());
		user.setCpf(userDTO.getCpf());
		user.setEndereco(userDTO.getEndereco());
		user.setEmail(userDTO.getEmail());
		user.setTelefone(userDTO.getTelefone());
		user.setDataCadastro(userDTO.getDataCadastro());
		
		return user;
	}
}
