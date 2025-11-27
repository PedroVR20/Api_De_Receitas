package br.com.apidereceitas.domain.model.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_receitas")
@Data
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "ingredientes", nullable = false)
	private String ingredientes;
	
}
