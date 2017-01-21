package br.com.rmenezes.gerenciadoEnderecos.business;

import java.util.List;

import br.com.rmenezes.gerenciadorEnderecos.model.Endereco;

public interface EnderecoBusiness {

	void cadastrar(Endereco end);
	
	void atualizar(Endereco end);

	Endereco buscarEndereco(Endereco end);
	
	List<Endereco> buscarEnderecoCep(String cep);
	
	List<Endereco> buscarTodos();
	
	void deletar(Endereco end);
}
