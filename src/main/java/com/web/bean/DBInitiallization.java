package com.web.bean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.web.model.User;

@Component
public class DBInitiallization implements InitializingBean{

	@Autowired
	private SessionFactory sessionFactory; 
	@Autowired
	private HibernateTransactionManager transactionManager;
	
	public void afterPropertiesSet() throws Exception {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		String query = "from User u where u.username = 'admin'";
		
		User user = (User) sessionFactory.getCurrentSession()
			.createQuery(query)
			.uniqueResult();
		
		if(user == null){
			try {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword("admin");
				admin.setRole(Role.ADMIN);
				
				Session session = sessionFactory.getCurrentSession();
				session.save(admin);
				
				transactionManager.commit(status);
			} catch (Exception e) {
				e.printStackTrace();
				transactionManager.rollback(status);
			}
		}
	}
}
