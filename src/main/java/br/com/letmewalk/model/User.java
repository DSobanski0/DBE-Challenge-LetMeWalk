package br.com.letmewalk.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_USER")
@SequenceGenerator(name = "user", sequenceName = "SQ_USER", initialValue = 1, allocationSize = 1)
public class User {

	@Id
	@Column(name="cd_user")
	@GeneratedValue(generator = "user", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name="nm_user")
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@Column(name="nr_ddd")
	@NotNull(message = "O ddd é obrigatório")
	private Integer ddd;
	
	@Column(name="nr_telefone")
	@NotNull(message = "O telefone é obrigatório")
	private Integer telefone;

	@Column(name="ds_senha")
	@NotBlank(message = "A senha é obrigatória")
	@Size(min = 8)
	private String senha;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Contato> contatos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<BotaoPanico> botaoPanico;
	
	@Embedded
	@Valid
	private Endereco endereco;
	
}