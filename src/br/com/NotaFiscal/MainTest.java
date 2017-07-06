package br.com.NotaFiscal;

import javax.persistence.EntityManager;

import br.com.NotaFiscal.dao.JPAUtil;
import br.com.NotaFiscal.modelo.NotaFiscal;

public class MainTest {
	
	public static void main(String[] args) {
		
		JPAUtil.startup();
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
	
		
		em.getTransaction().commit();
		em.close();
		JPAUtil.shutdown();
		
		
			
			
	}

}
