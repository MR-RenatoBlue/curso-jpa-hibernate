package com.algaworks.curso.jpa2.modelo;

import java.util.ArrayList;
import java.util.List;

public enum Categoria {

	HATCH_COMPACTO,
	HATCH_MEDIO,
	SEDAN_COMPACTO,
	SEDAN_MEDIO,
	SEDAN_GRANDE,
	MINIVAN,
	ESPORTIVO,
	UTILITARIO_COMERCIAL,;
	
	public static List<String> ordinaisPorNome(Categoria[] categorias) {
		List<String> ordinais = new ArrayList<String>();
		for (Categoria categoria : categorias) {
			ordinais.add(String.valueOf(categoria.ordinal()));
		}
		
		return ordinais;
	}
	
}
