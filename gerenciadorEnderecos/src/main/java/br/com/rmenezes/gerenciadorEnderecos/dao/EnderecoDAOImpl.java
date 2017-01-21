package br.com.rmenezes.gerenciadorEnderecos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import br.com.rmenezes.gerenciadorEnderecos.entity.EnderecoEntity;

public class EnderecoDAOImpl implements EnderecoDAO {

	@PersistenceUnit(name="persistenceUnit")
	private EntityManagerFactory emf;
	private EntityManager em = emf.createEntityManager();
	
	@Override
	public void cadastrar(EnderecoEntity entity){
		em.getTransaction().begin();
		if(!em.contains(entity)){
			em.merge(entity);
		}
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public void atualizar(EnderecoEntity entity){
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public EnderecoEntity buscarEndereco(int id){
		em.getTransaction().begin();
		EnderecoEntity retorno = em.find(EnderecoEntity.class, id);
		em.close();
		emf.close();
		return retorno;
	}
	
	@Override
	public List<EnderecoEntity> buscarEnderecoCep(String cep) {
		em.getTransaction().begin();
		List<EnderecoEntity> retorno = em.createNamedQuery("buscarPorCep", EnderecoEntity.class).setParameter(1, cep).getResultList();
		em.close();
		emf.close();
		return retorno;
	};

	@Override
	public List<EnderecoEntity> buscarTodos(){
		em.getTransaction().begin();
		List<EnderecoEntity> enderecos = em.createNamedQuery("listaEnderecos").getResultList();
		em.close();
		emf.close();
		return enderecos;
	}

	@Override
	public void deletar(EnderecoEntity entity){
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
