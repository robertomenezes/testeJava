package br.com.rmenezes.gerenciadorEnderecos.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rmenezes.gerenciadorEnderecos.model.Endereco;
import br.com.rmenezes.gerenciadorEnderecos.model.ErrorMessage;
import br.com.rmenezes.gerenciadorEnderecos.service.EnderecoService;

@RestController
@RequestMapping(value = "service")
public class ServicesController {

	@Autowired
	EnderecoService enderecoService;
	
	ErrorMessage errorMessage = new ErrorMessage();
	ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "listarEnderecos", method = RequestMethod.GET)
	public ResponseEntity<Map<Integer, Endereco>> listarEnderecos() {
		Map<Integer, Endereco> retorno = enderecoService.listarEnderecos();
		return new ResponseEntity<Map<Integer, Endereco>>(retorno, HttpStatus.OK);
	}

	@RequestMapping(value = "cadastrarEndereco", method = RequestMethod.POST)
	public ResponseEntity<String> cadastrarEndereco(@RequestBody Endereco endereco) {
		boolean enderecoValido = validarEndereco(endereco);
		if(!enderecoValido){
			errorMessage.setMessage("Endereço invalido");
			return new ResponseEntity<String>(errorMessage.toJson(), HttpStatus.OK);
		}
		Endereco buscarEndereco = enderecoService.buscarEndereco(endereco);
		if (buscarEndereco != null) {
			errorMessage.setMessage("Endereço já existe");
			return new ResponseEntity<String>(errorMessage.toJson(), HttpStatus.OK);
		}
		enderecoService.cadastrarEndereco(endereco);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private boolean validarEndereco(Endereco endereco) {
		boolean valido = true;
		if(endereco == null){
			valido = false;
		} else if(endereco.getCep() == null){
			valido = false;
		} else if(endereco.getCidade() == null){
			valido = false;
		} else if(endereco.getEstado() == null){
			valido = false;
		} else if(endereco.getLogradouro() == null){
			valido = false;
		} else if(endereco.getNumero() == null){
			valido = false;
		}
		return valido;
	}

	@RequestMapping(value = "buscarEnderecoPorCep/{cep}", method = RequestMethod.GET)
	public ResponseEntity<Map<Integer, Endereco>> buscarEnderecoPorCep(@PathVariable("cep") String cep) {

		Map<Integer, Endereco> endereco = enderecoService.buscarEnderecoPorCep(cep);
		if (endereco.isEmpty()) {
			errorMessage.setMessage("Cep não encontrado");
			return new ResponseEntity<Map<Integer, Endereco>>(endereco, HttpStatus.OK);
		}
		return new ResponseEntity<Map<Integer, Endereco>>(endereco, HttpStatus.OK);

	}

	@RequestMapping(value = "buscarEnderecoPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> buscarEnderecoPorId(@PathVariable("id") Integer id) {

		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
		Endereco endereco = enderecoService.buscarEnderecoPorId(id);
		if (endereco == null) {
			errorMessage.setMessage("Endereço não encontrado");
			return new ResponseEntity<String>(errorMessage.toJson(), HttpStatus.OK);
		}
		String enderecoJson;
		
		try {
			enderecoJson = mapper.writeValueAsString(endereco);
			response = new ResponseEntity<String>(enderecoJson, HttpStatus.OK);
		} catch (JsonProcessingException e) {
		}
		return response;

	}

	@RequestMapping(value = "deletarPorId/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletarPorId(@PathVariable("id") Integer id) {
		
		enderecoService.deletarEndereco(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "atualizarEndereco/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> atualizarEndereco(@PathVariable("id") Integer id, @RequestBody Endereco endereco) {

		Endereco buscarEndereco = enderecoService.buscarEnderecoPorId(id);
		if(!validarEndereco(endereco)){
			errorMessage.setMessage("Endereço invalido");
			return new ResponseEntity<String>(errorMessage.toJson(), HttpStatus.OK);
		} else if (buscarEndereco == null) {
			errorMessage.setMessage("Endereço não cadastrado");
			return new ResponseEntity<String>(errorMessage.toJson(), HttpStatus.PRECONDITION_FAILED);
		}

		enderecoService.atualizarEndereco(id, endereco);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
