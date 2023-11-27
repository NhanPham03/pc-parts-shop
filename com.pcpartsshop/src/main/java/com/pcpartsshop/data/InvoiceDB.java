package com.pcpartsshop.data;

import jakarta.persistence.*;

import com.pcpartsshop.business.Invoice;
import com.pcpartsshop.util.DBUtil;

public class InvoiceDB {
	public static void insert(Invoice invoice) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		try {
			em.persist(invoice);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(Invoice invoice) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		try {
			em.merge(invoice);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void delete(Invoice invoice) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		try {
			em.remove(em.merge(invoice));
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}
	
	public static Invoice selectInvoice(long customerID, long cartID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT i FROM Invoice i WHERE i.customerID = :customerID AND i.cartID = :cartID";
		TypedQuery<Invoice> query = em.createQuery(queryString, Invoice.class);
		query.setParameter("customerID", customerID);
		query.setParameter("cartID", cartID);
		
		Invoice invoice = null;
		try {
			invoice = query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return invoice;
	}
}
