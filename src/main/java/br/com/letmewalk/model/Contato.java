package br.com.letmewalk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_CONTATO")
@SequenceGenerator(name = "contato", sequenceName = "SQ_CONTATO", initialValue = 1, allocationSize = 1)
public class Contato {

	@Id
	@Column(name="cd_contato")
	@GeneratedValue(generator = "contato", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "O nome é obrigatória")
	@Column(name="nm_contato")
	private String nome;
	
	@NotNull(message = "O DDD é obrigatório")
	@Column(name="nr_ddd")
	private Integer ddd;
	
	@NotNull(message = "O telefone é obrigatório")
	@Column(name="nr_telefone")
	private Integer telefone;
	
	@ManyToOne
	@JoinColumn(name="cd_user")
	private User user;
	
}
