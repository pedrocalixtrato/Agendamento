package br.com.agendamento.util.jpa;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@RequestScoped
public class EntityManagerProducer {
	
	@PersistenceContext(unitName = "AgendamentoDS")			
	private EntityManager em;

	@Produces
	@RequestScoped
	public EntityManager create() {
		return this.em;
	}

	
	
}
