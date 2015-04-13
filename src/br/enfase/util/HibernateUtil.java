package br.enfase.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory	sessionFactory	= buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			
			AnnotationConfiguration cfg = new AnnotationConfiguration();
			
			// carrega as configura��es de conex�o do arquivo
			cfg.configure("hibernate.cfg.xml");
			
			//cria o SessionFactory
			return cfg.buildSessionFactory();
			
		} catch (Throwable e) {
			System.out.println("Cria��o inicial do objeto SessionFactory falhou. Erro: " + e);
			throw new ExceptionInInitializerError(e);
		}
	} 

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
