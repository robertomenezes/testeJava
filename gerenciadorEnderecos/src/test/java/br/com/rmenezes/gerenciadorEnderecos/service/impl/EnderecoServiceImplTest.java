package br.com.rmenezes.gerenciadorEnderecos.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.rmenezes.gerenciadorEnderecos.config.AppWebConfiguration;
import br.com.rmenezes.gerenciadorEnderecos.controllers.ServicesController;
import br.com.rmenezes.gerenciadorEnderecos.model.Endereco;
import br.com.rmenezes.gerenciadorEnderecos.service.EnderecoService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppWebConfiguration.class, ServicesController.class})
@ComponentScan(basePackages="br.com.rmenezes.gerenciadorEnderecos")
public class EnderecoServiceImplTest {

	@InjectMocks
	ServicesController controller = new ServicesController();
	
	@Mock
	EnderecoService enderecoService;
	
	@Mock
	Endereco enderecoMock;
	
	
	private void mockEndereco() {
		enderecoMock = new Endereco();
		enderecoMock.setBairro("Jabaquara");
		enderecoMock.setCep("04045004");
		enderecoMock.setCidade("São Paulo");
		enderecoMock.setComplemento("A");
		enderecoMock.setEstado("SP");
		enderecoMock.setLogradouro("Av Jabaquara");
		enderecoMock.setNumero(100);
		
	}

	@Test
	public void testCadastrarEndereco() {
		mockEndereco();
		enderecoService.cadastrarEndereco(enderecoMock);
		Mockito.verify(enderecoService, Mockito.times(1)).cadastrarEndereco(enderecoMock);
	}

	@Test
	public void testListarEnderecos() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarEnderecoPorCep() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarEnderecoPorId() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarEndereco() {
		fail("Not yet implemented");
	}

	@Test
	public void testAtualizarEndereco() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletarEndereco() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitEnderecos() {
		fail("Not yet implemented");
	}

}
