package br.com.letmewalk.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Table(name="TB_BOTAO_PANICO")
@SequenceGenerator(name = "botaoPanico", sequenceName = "SQ_BOTAO_PANICO", allocationSize = 1, initialValue = 1)
@Entity
public class BotaoPanico {

	@Id
	@Column(name="cd_click")
	@GeneratedValue(generator = "botaoPanico", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@CreationTimestamp
	@Column(name="hr_click")
	private Timestamp horaClick;
	
	@ManyToOne
	@JoinColumn(name = "cd_user")
	private User user;
	
}
