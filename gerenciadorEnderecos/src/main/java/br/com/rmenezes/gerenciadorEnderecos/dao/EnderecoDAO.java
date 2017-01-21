package br.com.rmenezes.gerenciadorEnderecos.dao;

import java.util.List;

import br.com.rmenezes.gerenciadorEnderecos.entity.EnderecoEntity;

public interface EnderecoDAO {

	void cadastrar(EnderecoEntity entity);
	
	void atualizar(EnderecoEntity entity);
	
	EnderecoEntity buscarEndereco(int id);
	
	List<EnderecoEntity> buscarEnderecoCep(String cep);
	
	List<EnderecoEntity> buscarTodos();
	
	void deletar(EnderecoEntity entity);
}
