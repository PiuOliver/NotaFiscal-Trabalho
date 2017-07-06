package br.com.NotaFiscal.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.apache.derby.impl.sql.catalog.SYSROUTINEPERMSRowFactory;

import com.sun.media.sound.ModelAbstractChannelMixer;

import br.com.NotaFiscal.modelo.EmitenteDestinatario;
import br.com.NotaFiscal.modelo.Item;
import br.com.NotaFiscal.modelo.NotaFiscal;

public class NotaDAO_Hibernate implements NotaDAO{

	
	public NotaDAO_Hibernate() {
		
		JPAUtil.startup();
		
	}
	
	private EntityManager em;

	@Override
	public NotaFiscal inserir(NotaFiscal nota) {
		em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(nota);
		em.getTransaction().commit();
		em.close();
		
		return nota;
	}
	
	@Override
	public NotaFiscal atualizar(NotaFiscal nota) {
		em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<NotaFiscal> query = em.createQuery("select n from Nf n join fetch n.itens where n.notaFiscalNumero = :numero", NotaFiscal.class);
		NotaFiscal nf = query.getSingleResult();

		em.merge(nf);
		
		em.getTransaction().commit();
		em.close();
		return null;
	}
	
	public NotaFiscal remover(Integer codigo) {
		em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		TypedQuery<NotaFiscal> queryNota = em.createQuery("select nf from notafiscal nf where codigo = :numero", NotaFiscal.class);
		TypedQuery<Item> queryItem = em.createQuery("select it from item it where codigo = :numero", Item.class);
		queryNota.setParameter("numero", codigo);
		queryItem.setParameter("numero", codigo);
		
		em.remove(queryNota.getSingleResult());
		em.remove(queryItem.getSingleResult());
		em.getTransaction().commit();
		return null;
	}

	@Override
	public List<NotaFiscal> listarTodos() {
		em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<NotaFiscal> query = em.createQuery("select n from notafiscal n", NotaFiscal.class);
		List<NotaFiscal> lista = query.getResultList();
		
		return lista;
	}

	public NotaFiscal buscaPorCodigo(Integer codigo) {
		em = JPAUtil.getEntityManager();
		TypedQuery<NotaFiscal> query = em.createQuery("select nf from notafiscal nf where codigo = :numero", NotaFiscal.class);
		query.setParameter("numero", codigo);
		return query.getSingleResult();
	}

	@Override
	public Item inserirItem(Item item) {
		em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		em.close();
		
		return item;
	}
	
	@Override
	public List<Item> listarItens() {
		em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Item> query = em.createQuery("select DESCRICAO_PRODUTO FROM notafiscal", Item.class);
		List<Item> listaItem = query.getResultList();
		return listaItem;
	}

	public Long resultadoTotal(){
		em = JPAUtil.getEntityManager();
		TypedQuery<Long> query = em.createQuery("select count(n) from notafiscal n", Long.class);
		return query.getSingleResult();

	}
	
	public Double resultadoMedia(){
		em = JPAUtil.getEntityManager();
		TypedQuery<Double> query2 = em.createQuery("select AVG(n.quantidadeItens) from notafiscal n", Double.class);
		Double result2 = query2.getSingleResult();

		return result2;
	}
	
	public Double MediaItens(){
		em = JPAUtil.getEntityManager();
		TypedQuery<Double> query3 = em.createQuery("select SUM(n.valor)/SUM(n.quantidadeItens) from notafiscal n", Double.class);
		Double result3 = query3.getSingleResult();
		
		return result3;
	}
	
	public Double MaiorValor(){
		em = JPAUtil.getEntityManager();
		TypedQuery<Double> query4 = em.createQuery("select MAX(n.valor) from notafiscal n", Double.class);
		Double result4 = query4.getSingleResult();
		
		return result4;
	}
	
	public Object EstadoMaiorDestinatario(){
		em = JPAUtil.getEntityManager();
		TypedQuery<Object[]> query7 = em.createQuery("select n.destinatario.estado, count(*) as num from notafiscal n GROUP by n.destinatario.estado order by num desc", Object[].class);
		Object[] result7 = query7.setMaxResults(1).getSingleResult();
		
		String estado = ""+result7[0];
		
		return estado;
	}
	
	public Object cnpjMaiorCompradora(){
		em = JPAUtil.getEntityManager();
		TypedQuery<Object[]> query8 = em.createQuery("select n.destinatario.socialNome, sum(n.valor) as soma from notafiscal n GROUP by n.destinatario.socialNome order by soma desc", Object[].class);
		Object[] result8 = query8.setMaxResults(1).getSingleResult();
		
		String razaoSocial = ""+result8[0];
		return razaoSocial;
	}
	
	public Object cnpjMaiorVendedora(){
		em = JPAUtil.getEntityManager();
		TypedQuery<Object[]> query9 = em.createQuery("select n.emitende.socialNome, sum(n.valor) as soma from notafiscal n GROUP by n.emitende.socialNome order by soma desc", Object[].class);
		Object[] result9 = query9.setMaxResults(1).getSingleResult();
		
		String razaoSocial2 = ""+result9[0];
		return razaoSocial2;
	}
	
	public Long totalNotasSuperior(){
		em = JPAUtil.getEntityManager();	
		TypedQuery<Long> query10 = em.createQuery("select count(n.valor) from notafiscal n where n.valor > 10000", Long.class);
		Long result10 = query10.getSingleResult();
		
		return result10;
	}
	
	public Long notaMais10(){
		em = JPAUtil.getEntityManager();
		TypedQuery<Long> query11 = em.createQuery("select count(n.quantidadeItens) from notafiscal n where n.quantidadeItens > 10", Long.class);
		Long result11 = query11.getSingleResult();
		
		return result11;
	}
	
	public Object estadoMaiorNotas(){
		em = JPAUtil.getEntityManager();
		TypedQuery<Object[]> query5 = em.createQuery("select n.emitende.estado, count(*) from notafiscal n GROUP by n.emitende.estado", Object[].class);
		List<Object[]> result5 = query5.getResultList();
		TypedQuery<Object[]> query6 = em.createQuery("select n.destinatario.estado, count(*) from notafiscal n GROUP by n.destinatario.estado", Object[].class);
		List<Object[]> result6 = query6.getResultList();
		
		TreeMap<String, Long> resultado = new TreeMap();
		
		for(Object[] itens : result5){
			String name = (String) itens[0];
			long quant = (Long) itens[1];
			
			resultado.put(name, quant);
		}
		for(Object[] itens : result6){
			String name = (String) itens[0];
			long quant = (Long) itens[1];
			
			if(resultado.containsKey(name)){
				long i =resultado.get(name)+quant;
				resultado.put(name, i);
			}else{
				resultado.put(name, quant);
			}
		}
		
		return resultado.firstKey();
	}
	
}
