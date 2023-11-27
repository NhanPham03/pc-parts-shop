package com.pcpartsshop.data;

import jakarta.persistence.*;

import com.pcpartsshop.business.Item;
import com.pcpartsshop.util.DBUtil;

public class ItemDB {
	public static void insert(Item item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		try {
			em.persist(item);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(Item item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		try {
			em.merge(item);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void delete(Item item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		try {
			em.remove(em.merge(item));
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}
	
	public static Item selectItem(String productID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT i FROM Item i WHERE i.productID = :productID";
		TypedQuery<Item> query = em.createQuery(queryString, Item.class);
		query.setParameter("productID", productID);
		
		Item item = null;
		try {
			item = query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return item;
	}
}
