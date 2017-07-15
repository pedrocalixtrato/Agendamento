package br.com.agendamento.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.agendamento.dao.LancamentosServicosDAO;
import br.com.agendamento.domain.LancamentosServicos;

@Named
@ViewScoped
public class LancamentosServicosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LancamentosServicos servicos;
	private ScheduleModel eventModel;
	private List<LancamentosServicos> listaServicos;
	
	@Inject
	private LancamentosServicosDAO servicoDAO;

	
	@PostConstruct
	public void init(){
		
		servicos = new LancamentosServicos();
		eventModel = new DefaultScheduleModel();
		try{
			
		listaServicos = servicoDAO.listar(servicos);
		
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		for(LancamentosServicos ls : listaServicos ){
			DefaultScheduleEvent event = new DefaultScheduleEvent();
			event.setEndDate(ls.getDataFim());
			event.setStartDate(ls.getDataInicio());
			event.setTitle(ls.getCliente());
			event.setData(ls.getId());
			event.setDescription(String.valueOf(ls.getValor()));
			event.setAllDay(false);
			event.setEditable(true);
			
			eventModel.addEvent(event);
		}
		
		
		
	}
	
	public void salvar(){
		try{
			if(servicos.getDataFim().getTime()<= servicos.getDataFim().getTime()){						
				servicoDAO.salvar(servicos);
				Messages.addGlobalInfo("Agendado com sucesso!");
			}
		
		}catch(RuntimeException e){
			e.printStackTrace();
			Messages.addGlobalError("Erro ao agendar este serviço");
		}
	}
	
	public void eventoSelecionado(SelectEvent selectEvent){
		
		ScheduleEvent evento1 = (ScheduleEvent) selectEvent.getObject();
		
		for(LancamentosServicos ls : listaServicos){
			if(ls.getId() == (Long) evento1.getData()){
			
				servicos = ls;
				break;				
			}			
		}		
	}
	
	public void quandoNovo(SelectEvent selectEvent){
		
		ScheduleEvent event = new DefaultScheduleEvent("",(Date)selectEvent.getObject(), (Date)selectEvent.getObject());
		servicos = new LancamentosServicos();
		servicos.setDataInicio(new java.sql.Date(event.getStartDate().getTime()));
		servicos.setDataFim(new java.sql.Date(event.getEndDate().getTime()));
		
		
	}
	
	 public void onEventMove(ScheduleEntryMoveEvent event) {
		 for(LancamentosServicos ls : listaServicos){
			 if(ls.getId() == (Long)event.getScheduleEvent().getData()){
				 servicos = ls;
				 try{
					 servicoDAO.salvar(servicos);
					 init();
				 }catch(RuntimeException e){
					 e.printStackTrace();
					 Messages.addGlobalError("Erro ao salvar serviço");
				 }
				 break;
			 }
		  }	       
	    }
	
	 public void onEventResize(ScheduleEntryResizeEvent event) {
		 for(LancamentosServicos ls : listaServicos){
			 if(ls.getId() == (Long)event.getScheduleEvent().getData()){
				 servicos = ls;
				 try{
					 servicoDAO.salvar(servicos);
					 init();
				 }catch(RuntimeException e){
					 e.printStackTrace();
					 Messages.addGlobalError("Erro ao salvar serviço");
				 }
				 break;
			 }
		  }	      
	    }
	
	public LancamentosServicos getServicos() {
		return servicos;
	}

	public void setServicos(LancamentosServicos servicos) {
		this.servicos = servicos;
	}



	public ScheduleModel getEventModel() {
		return eventModel;
	}


	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}


	public List<LancamentosServicos> getListaServicos() {
		return listaServicos;
	}

	public void setListaServicos(List<LancamentosServicos> listaServicos) {
		this.listaServicos = listaServicos;
	}
	

}
