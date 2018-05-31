package com.ms.screening.dao;

import java.util.Objects;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ms.screening.caching.Cache;

@Repository
public class AccountDAO {

	private Random random = new Random();

    private final ConcurrentHashMap<String, Account> dbMap = new ConcurrentHashMap<>();
    
    @Autowired
    private Cache cacheImpl; 

	private String generateRandomAccountId() {
		return String.valueOf(random.nextInt(101));
	}
	
	public void add(Account account) {
		String id = generateRandomAccountId();
		account.setAccountId(id);
		dbMap.put(id, account);
		cacheImpl.add(id, account);
		System.out.println("***Account Successfully Added to DB***");
	}
	
	public void update(Account account) {
		dbMap.replace(account.getAccountId(), account);
		System.out.println("***Account Successfully Updated to DB***");
	}
	
	public void delete(String accountId) {
		dbMap.remove(accountId);
		cacheImpl.remove(accountId);
		System.out.println("***Account Successfully Deleted to DB***");
	}
	
	public Account get(String accountId) {
		Account account = (Account)cacheImpl.get(accountId);
		if (Objects.nonNull(account)) {
			System.out.println("***Getting Account from CACHE***");
			return account;
		} else {
			System.out.println("***Getting Account from DB***");
			return dbMap.get(accountId);
		}
	}
	
	
}
