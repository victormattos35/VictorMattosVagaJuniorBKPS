package br.com.clientecrud.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.clientecrud.DAO.ClienteDAO;
import br.com.clientecrud.model.Cliente;

@ManagedBean(name = "MBCliente")
@ViewScoped
public class ClienteBean {

	private Cliente cliente;
	private List<Cliente> listCliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListCliente() {
		return listCliente;
	}

	public void setListCliente(List<Cliente> listCliente) {
		this.listCliente = listCliente;
	}

	@PostConstruct
	public void listar() {
		ClienteDAO dao = new ClienteDAO();
		listCliente = dao.listar();
		System.out.println(listCliente);
	}

	public void novo() {
		cliente = new Cliente();
	}

	public void salvar() {
		try {
			ClienteDAO dao = new ClienteDAO();
			dao.salvar(cliente);
			Messages.addGlobalInfo("Cliente Salvo com Sucesso!");
			listCliente = dao.listar();
			novo();
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um Erro ao tentar Salvar o Cliente!");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent event){
		cliente = (Cliente) event.getComponent().getAttributes().get("clienteSelecionado");
	}
	
	public void excluir(ActionEvent event){
		cliente = (Cliente) event.getComponent().getAttributes().get("clienteSelecionado");
		try {
			ClienteDAO dao = new ClienteDAO();
			dao.excluir(cliente);
			Messages.addGlobalInfo("Cliente: " + cliente.getNome() + " excluído com sucesso!");
			listCliente = dao.listar();
			novo();
		} catch (Exception e) {
			Messages.addGlobalError("Ocorreu um Erro ao tentar Excluir o Cliente!");
			e.printStackTrace();
		}
	}
}
