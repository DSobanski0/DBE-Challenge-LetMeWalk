package br.com.letmewalk.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Embeddable
public class Endereco {
	
	@Column(name="uf_estado")
	@NotBlank(message = "O estado é obrigatório")
	@Size(min = 2 ,max = 2, message = "Digite o UF do estado")
	private String estado;
	
	@Column(name = "nm_cidade")
	@NotBlank(message = "Cidade é obrigatória")
	private String cidade;

	@Column(name = "nm_bairro")
	@NotBlank(message = "bairro é obrigatório")
	private String bairro;
	
	@Column(name = "ds_logradouro")
	@NotBlank(message = "Logradouro é obrigatório")
	private String logradouro;
	
	@Column(name = "nr_lar")
	@NotNull(message = "Número é obrigatório")
	private Integer numero;

	@Column(name = "ds_complemento")
	@NotBlank(message = "Complemento é obrigatório")
	private String complemento;
	
	@Column(name = "nr_cep")
	@NotBlank(message = "CEP é obrigatório")
	@Size(min = 8 ,max = 8, message = "CEP deve ter 8 caracteres")
	private String cep;
}
