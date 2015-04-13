package br.enfase.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.enfase.conta.Conta;
import br.enfase.conta.ContaRN;
import br.enfase.usuario.Usuario;
import br.enfase.usuario.UsuarioRN;

@ManagedBean(name = "contextoBean")
@SessionScoped
public class ContextoBean {

	private Usuario usuarioLogado = null;
	private Conta contaAtiva = null;

	
	/*Obtem o usuario logado no sistema*/
	public Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		
		//verifica se a instância do usuario carregado é do usuario logado
		if (this.usuarioLogado == null || !login.equals(this.usuarioLogado.getLogin())) {

			if (login != null) {
				UsuarioRN usuarioRN = new UsuarioRN();
				this.usuarioLogado = usuarioRN.buscarPorLogin(login);
				this.contaAtiva = null;
			}
		}
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuario) {
		this.usuarioLogado = usuario;
	}
	
	
	/*Fornece a conta ativa no momento*/
	public Conta getContaAtiva() {
		if (this.contaAtiva == null) {
			Usuario usuario = this.getUsuarioLogado();

			ContaRN contaRN = new ContaRN();
			this.contaAtiva = contaRN.buscarFavorita(usuario);

			if (this.contaAtiva == null) {
				List<Conta> contas = contaRN.listar(usuario);
				if (contas != null) {
					for (Conta conta : contas) {
						this.contaAtiva = conta;
						break;
					}
				}
			}
		}
		return this.contaAtiva;
	}
	
	public void setContaAtiva(ValueChangeEvent event) {

		Integer conta = (Integer) event.getNewValue();

		ContaRN contaRN = new ContaRN();
		this.contaAtiva = contaRN.carregar(conta);
	}
	
	
}
