package br.com.rmenezes.gerenciadorEnderecos.service;

import java.util.Map;

import br.com.rmenezes.gerenciadorEnderecos.model.Endereco;

public interface EnderecoService {

	void cadastrarEndereco(Endereco endereco);
	Map<Integer, Endereco> listarEnderecos();
	Map<Integer, Endereco> buscarEnderecoPorCep(String cep);
	Endereco buscarEnderecoPorId(Integer id);
	Endereco buscarEndereco(Endereco endereco);
	void atualizarEndereco(Integer id, Endereco endereco);
	void deletarEndereco(Integer id);
}
