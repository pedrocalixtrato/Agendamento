package br.com.agendamento.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericoDAO<T, ID extends Serializable> {
	
	public T buscarPeloCodigo(ID id);
		
	public void salvar(T entidade);
	
	public List<T> filtrar(T entidade, String... propriedades);
	
	
	
	

}
