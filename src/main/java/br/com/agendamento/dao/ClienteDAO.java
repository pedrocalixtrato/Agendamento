package br.com.agendamento.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.agendamento.domain.Cliente;
import br.com.agendamento.filter.FiltroCliente;


@SuppressWarnings("serial")
public class ClienteDAO extends HibernateGenericDAO<Cliente, Long> implements Serializable {

	
	@PersistenceContext
	private EntityManager em;
	
	public List<Cliente> filtrados(FiltroCliente filtroCliente) {
		Session session = this.em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Cliente.class)
				
				// fazemos uma associação (join) com cliente e nomeamos como "c"
				.createAlias("cliente", "c");		
		
		if (StringUtils.isNotBlank(filtroCliente.getNome())) {
			// acessamos o nome do cliente associado ao pedido pelo alias "c", criado anteriormente
			criteria.add(Restrictions.ilike("c.nome", filtroCliente.getNome(), MatchMode.ANYWHERE));}
		
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.addOrder(Order.asc("id")).list();
	}
		

}
		