package br.com.rmenezes.gerenciadorEnderecos.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.rmenezes.gerenciadorEnderecos.model.Endereco;
import br.com.rmenezes.gerenciadorEnderecos.service.EnderecoService;

@Service("enderecoService")
public class EnderecoServiceImpl implements EnderecoService {
	
	private static int countId = 0;
	private static Map<Integer, Endereco> mapEnderecos;
	
	static{
		mapEnderecos = initEnderecos();
	}
	
	@Override
	public void cadastrarEndereco(Endereco endereco) {
		endereco.setId(countId++);
		mapEnderecos.put(countId, endereco);
	}

	@Override
	public Map<Integer, Endereco> listarEnderecos() {
		return mapEnderecos;
	}

	@Override
	public Map<Integer, Endereco> buscarEnderecoPorCep(String cep) {
		Map<Integer, Endereco> enderecos = new HashMap<Integer,Endereco>();
		int count = 0;
		for (Integer id : mapEnderecos.keySet()) {
			Endereco iEndereco = mapEnderecos.get(id);
			if(iEndereco.getCep().equals(cep)){
				enderecos.put(count++,iEndereco);
			}
		}
		if(enderecos.isEmpty() && !"00000000".equals(cep)){
			enderecos = alterarUltimosDigitos(cep);
		}
		return enderecos;
	}

	private Map<Integer, Endereco> alterarUltimosDigitos(String cep) {
		Map<Integer, Endereco> retorno;
		char[] array = cep.toCharArray();
		char zero = '0';
		for(int i = array.length -1; i >= 0; i--){
			if(array[i] != zero){
				array[i] = zero;
				break;
			}
		}
		
		cep = String.valueOf(array);
		retorno = buscarEnderecoPorCep(cep);
		return retorno;
	}

	@Override
	public Endereco buscarEnderecoPorId(Integer id) {
		return mapEnderecos.get(id);
	}
	
	@Override
	public Endereco buscarEndereco(Endereco endereco){
		Endereco retorno = null;
		for (Integer id : mapEnderecos.keySet()) {
			Endereco iEndereco = mapEnderecos.get(id);
			if(endereco.equals(iEndereco)){
				retorno = iEndereco;
			} 
		}
		return retorno;
	}

	@Override
	public void atualizarEndereco(Integer id, Endereco endereco) {
		mapEnderecos.put(id, endereco);
	}

	@Override
	public void deletarEndereco(Integer id) {
		mapEnderecos.remove(id);
	}
	
	public static Map<Integer, Endereco> initEnderecos(){
		
		Map<Integer, Endereco> enderecos = new HashMap<Integer,Endereco>();
		enderecos.put(countId, new Endereco(countId++, "04194260", "Av dos Ourives", 632, "Torre 1 Ap 24", "Pq Bristol", "São Paulo", "SP"));
		enderecos.put(countId, new Endereco(countId++, "04194260", "Av dos Ourives", 632, "Torre 1 Ap 24", "Pq Bristol", "São Paulo", "SP"));
		enderecos.put(countId, new Endereco(countId++, "04194000", "Av dos Ourives", 632, "Torre 1 Ap 24", "Pq Bristol", "São Paulo", "SP"));
		return enderecos;
	}

}
