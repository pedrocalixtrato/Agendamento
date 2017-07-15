package br.com.agendamento.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.agendamento.dao.ClienteDAO;
import br.com.agendamento.domain.Cliente;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteDAO clienteDAO;
	private Cliente cliente;
	
	public void salvar(){
		try{		
		
		cliente = new Cliente();
		Messages.addGlobalInfo("Salvo com sucesso!");		
		}catch(RuntimeException e){
			Messages.addGlobalError("Erro ao salvar este cadastro.");			
		}
	}
	

	public Cliente getCliente() {if(cliente == null){
		cliente = new Cliente();		
	}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	
	

	
	
}
