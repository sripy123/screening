package com.ms.screening;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ms.screening.dao.Account;
import com.ms.screening.dao.AccountDAO;

/**
 * Implementation of ApplicationRunner class
 * @author Sriram
 *
 */
@Component
public class AppStartupRunner implements ApplicationRunner {
	@Autowired
	private AccountDAO dao;

	@Override
    public void run(ApplicationArguments args) throws Exception {
        callAccountDAO();
    }
    
    private void callAccountDAO() {
		Account account1 = new Account("JohnDoe1",1000);
		Account account2 = new Account("JohnDoe2",2000);
		Account account3 = new Account("JohnDoe3",3000);
		Account account4 = new Account("JohnDoe4",4000);
		

		dao.add(account1);
		dao.add(account2);
		dao.add(account3);
		dao.add(account4);
		
		System.out.println(dao.get(account1.getAccountId()).getAccountName()); // get from cache
		
		try {
			Thread.currentThread().sleep(11000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(dao.get(account1.getAccountId()).getAccountName());// get from db
	}
}