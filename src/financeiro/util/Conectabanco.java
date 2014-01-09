package financeiro.util;

import org.hibernate.Session;

public class Conectabanco {
	
	public static void main(String[] args){
		Session s = null;
		
		try{
			s = HibernateUtil.getSessionFactory().openSession();
			System.out.println("Conectou ao banco");
		}finally{
			s.close();
		}
	}

}
