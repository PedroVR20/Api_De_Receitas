package br.com.apidereceitas.domain.model.dtos;

import jakarta.validation.constraints.NotBlank;

public record CriarReceitaRequestDto(
	@NotBlank  String nome,
	@NotBlank String descricao,
	@NotBlank String ingredientes
		) {
}
