package br.com.agendamento.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.agendamento.dao.ClienteDAO;
import br.com.agendamento.domain.Cliente;
import br.com.agendamento.filter.FiltroCliente;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteDAO clienteDAO;

	private Cliente cliente;
	private List<Cliente> clientes;
	private FiltroCliente filtro;
	
	public void pesquisar() {

		clienteDAO.filtrar(cliente, "nome");

	}
	
	public PesquisaClienteBean() {		
		cliente = new Cliente();
		filtro = new FiltroCliente();
		clientes = new ArrayList<>();
		
	}	
	
	public void filtrar(){
		
		clientes = clienteDAO.filtrar(cliente, "nome");
		
		
	}
	

	public String salvar() {		
		clienteDAO.salvar(cliente);
		return "pm:list?transition=flip";
	}
	
	public String remover(){
		
		clienteDAO.excluir(cliente.getCodigo());
		return "pm:list?transition=flip";
		
	}
	

	public String prepareNovoCliente() {
		cliente = new Cliente();
		return "pm:edit?transition=flip";
	}

	public Cliente getCliente() {if(cliente==null){
		cliente = new Cliente();
	}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {if(cliente==null){
		clientes = new ArrayList<>();
	}
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public FiltroCliente getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroCliente filtro) {
		this.filtro = filtro;
	}
	
	
	

}
