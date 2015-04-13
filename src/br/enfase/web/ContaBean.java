package br.enfase.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.enfase.conta.Conta;
import br.enfase.conta.ContaRN;
import br.enfase.web.util.ContextoUtil;

@ManagedBean(name="contaBean")
@RequestScoped
public class ContaBean {
	
	private Conta selecionada = new Conta();
	
	private List<Conta> lista = null;
	
	public void salvar(){
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.selecionada.setUsuario(contextoBean.getUsuarioLogado());

		ContaRN contaRN = new ContaRN();
		contaRN.salvar(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
	}
	
	public void excluir(){
		ContaRN contaRN = new ContaRN();
		contaRN.excluir(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
	}
	
	public List<Conta> getLista() { 
		if (this.lista == null) {
			ContextoBean contextoBean = ContextoUtil.getContextoBean();

			ContaRN contaRN = new ContaRN();
			this.lista = contaRN.listar(contextoBean.getUsuarioLogado());
		}
		return this.lista;
	}
	
	public void tornarFavorita() { 
		ContaRN contaRN = new ContaRN();
		contaRN.tornarFavorita(this.selecionada);
		this.selecionada = new Conta();
	}
	
	

	public Conta getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Conta selecionada) {
		this.selecionada = selecionada;
	}

	

	public void setLista(List<Conta> lista) {
		this.lista = lista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((selecionada == null) ? 0 : selecionada.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaBean other = (ContaBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (selecionada == null) {
			if (other.selecionada != null)
				return false;
		} else if (!selecionada.equals(other.selecionada))
			return false;
		return true;
	}
	
	
	
	
}
