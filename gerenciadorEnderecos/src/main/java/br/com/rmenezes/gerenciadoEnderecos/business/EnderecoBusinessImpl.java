package br.com.rmenezes.gerenciadoEnderecos.business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.rmenezes.gerenciadorEnderecos.dao.EnderecoDAO;
import br.com.rmenezes.gerenciadorEnderecos.entity.EnderecoEntity;
import br.com.rmenezes.gerenciadorEnderecos.model.Endereco;

public class EnderecoBusinessImpl implements EnderecoBusiness {

	@Inject
	private EnderecoDAO dao;
	
	@Override
	public void cadastrar(Endereco end){
		EnderecoEntity entity = parseModelToEntity(end);
		
		dao.cadastrar(entity);
	}

	@Override
	public void atualizar(Endereco end){
		
		EnderecoEntity entity = parseModelToEntity(end);
		
		dao.atualizar(entity);
		 
	}

	@Override
	public Endereco buscarEndereco(Endereco end){
		
		EnderecoEntity entity = dao.buscarEndereco(end.getId());
		
		return parseEntityToModel(entity);
		
	}
	
	@Override
	public List<Endereco> buscarEnderecoCep(String cep) {
		List<EnderecoEntity> enderecos = dao.buscarEnderecoCep(cep);
		List<Endereco> endList = new ArrayList<Endereco>();
		
		if(endList.isEmpty()){
			alterarUltimosDigitos(cep);
		}
		for (EnderecoEntity enderecoEntity : enderecos) {
			endList.add(parseEntityToModel(enderecoEntity));
		}
		return endList;
		
	}
	
	private List<Endereco> alterarUltimosDigitos(String cep) {
		List<Endereco> retorno = new ArrayList<Endereco>();
		char[] array = cep.toCharArray();
		char zero = '0';
		for(int i = array.length -1; i > 0; i--){
			if(array[i] != zero){
				array[i] = zero;
				break;
			}
		}
		
		cep = String.valueOf(array);
		retorno = buscarEnderecoCep(cep);
		return retorno;
	}

	@Override
	public List<Endereco> buscarTodos(){
		List<EnderecoEntity> enderecos = dao.buscarTodos();
		List<Endereco> endList = new ArrayList<Endereco>();
		for (EnderecoEntity enderecoEntity : enderecos) {
			endList.add(parseEntityToModel(enderecoEntity));
		}
		return endList;
	}

	@Override
	public void deletar(Endereco end){
		EnderecoEntity entity = new EnderecoEntity();
		entity = parseModelToEntity(end);
		dao.deletar(entity);
	}
	
	private EnderecoEntity parseModelToEntity(Endereco end) {
		EnderecoEntity entity = new EnderecoEntity();
		
		if(end.getBairro() != null)
			entity.setBairro(end.getBairro());
		if(end.getComplemento() != null)
			entity.setComplemento(end.getComplemento());
		entity.setCep(end.getCep());
		entity.setCidade(end.getCidade());
		entity.setEstado(end.getEstado());
		entity.setLogradouro(end.getLogradouro());
		entity.setNumero(end.getNumero());
		return entity;
	}
	private Endereco parseEntityToModel(EnderecoEntity entity) {
		Endereco end = new Endereco();
		
		if(entity.getBairro() != null)
			end.setBairro(entity.getBairro());
		if(entity.getComplemento() != null)
			end.setComplemento(entity.getComplemento());
		end.setCep(entity.getCep());
		end.setCidade(entity.getCidade());
		end.setEstado(entity.getEstado());
		end.setLogradouro(entity.getLogradouro());
		end.setNumero(entity.getNumero());
		end.setId(entity.getId());
		return end;
	}
	
	
}
