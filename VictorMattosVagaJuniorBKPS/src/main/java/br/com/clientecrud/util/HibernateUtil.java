package br.com.clientecrud.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory = fabricaDeSessoes();

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private static SessionFactory fabricaDeSessoes() {
		try {
			Configuration configuration = new Configuration().configure();

			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
					.build();
			SessionFactory fabrica = configuration.buildSessionFactory(registro);
			return fabrica;
		} catch (Throwable ex) {
			System.err.println("Erro ao Criar a SessionFactory");
			throw new ExceptionInInitializerError(ex);
		}
	}
}
