package com.pcpartsshop.data;

import jakarta.persistence.*;

import com.pcpartsshop.business.Customer;
import com.pcpartsshop.util.DBUtil;

public class CustomerDB {
	public static void insert(Customer customer) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		try {
			em.persist(customer);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Customer customer) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		try {
			em.merge(customer);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Customer customer) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		try {
			em.remove(em.merge(customer));
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public static Customer selectCustomerByID(Long customerID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT c FROM Customer c WHERE c.customerID = :customerID";
		TypedQuery<Customer> query = em.createQuery(queryString, Customer.class);
		query.setParameter("customerID", customerID);

		Customer customer = null;
		try {
			customer = query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return customer;
	}
	
	public static Customer selectCustomerByEmail(String email) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT c FROM Customer c WHERE c.email = :email";
		TypedQuery<Customer> query = em.createQuery(queryString, Customer.class);
		query.setParameter("email", email);

		Customer customer = null;
		try {
			customer = query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return customer;
	}
	
	public static Customer selectCustomerByCardNumber(String cardNumber) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT c FROM Customer c WHERE c.cardNumber = :cardNumber";
		TypedQuery<Customer> query = em.createQuery(queryString, Customer.class);
		query.setParameter("cardNumber", cardNumber);

		Customer customer = null;
		try {
			customer = query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return customer;
	}

	public static boolean emailExists(String email) {
		Customer customer = selectCustomerByEmail(email);
		return customer != null;
	}
	
	public static boolean cardNumberExists(String cardNumber) {
		Customer customer = selectCustomerByCardNumber(cardNumber);
		return customer != null;
	}
}
