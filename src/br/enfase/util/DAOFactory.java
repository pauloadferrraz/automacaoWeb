package br.enfase.util;

import br.enfase.conta.ContaDAO;
import br.enfase.conta.ContaDAOHibernate;
import br.enfase.usuario.*;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO(){
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	
	public static ContaDAO criarContaDAO(){
		ContaDAOHibernate contaDAO = new ContaDAOHibernate();
		contaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return contaDAO;
	}
	
	
}
