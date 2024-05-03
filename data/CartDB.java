package com.pcpartsshop.data;

import jakarta.persistence.*;

import com.pcpartsshop.business.Cart;
import com.pcpartsshop.util.DBUtil;

public class CartDB {
	public static void insert(Cart cart) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		try {
			em.persist(cart);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(Cart cart) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		try {
			em.merge(cart);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void delete(Cart cart) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		try {
			em.remove(em.merge(cart));
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}
	
	public static Cart selectCartByCustomerID(long customerID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT c FROM Cart c WHERE c.customer.customerID = :customerID AND c.active = true";
		TypedQuery<Cart> query = em.createQuery(queryString, Cart.class);
		query.setParameter("customerID", customerID);
		
		Cart cart = null;
		try {
			cart = query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return cart;
	}

	public static int getItemCount(Cart cart) {
		return cart.getItems().size();
	}
}
