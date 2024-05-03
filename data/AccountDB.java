package com.pcpartsshop.data;

import jakarta.persistence.*;

import com.pcpartsshop.business.Account;
import com.pcpartsshop.util.DBUtil;

public class AccountDB {
	public static void insert(Account account) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		try {
			em.persist(account);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Account account) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		try {
			em.merge(account);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Account account) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		try {
			em.remove(em.merge(account));
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public static Account selectAccount(String username) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT a FROM Account a WHERE a.username = :username";
		TypedQuery<Account> query = em.createQuery(queryString, Account.class);
		query.setParameter("username", username);

		Account account = null;
		try {
			account = query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return account;
	}

	public static boolean usernameExists(String username) {
		Account account = selectAccount(username);
		return account != null;
	}
}
