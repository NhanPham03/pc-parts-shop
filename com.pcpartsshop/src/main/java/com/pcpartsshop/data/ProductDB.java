package com.pcpartsshop.data;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

import com.pcpartsshop.business.Product;
import com.pcpartsshop.util.DBUtil;

public class ProductDB {
	public static void insert(Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		try {
			em.persist(product);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		try {
			em.merge(product);
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		try {
			em.remove(em.merge(product));
			transaction.commit();
		} catch (Exception ex) {
			System.out.println(ex);
			transaction.rollback();
		} finally {
			em.close();
		}
	}

	public static Product selectProductByID(String productID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT p FROM Product p WHERE p.productID = :id";
		TypedQuery<Product> query = em.createQuery(queryString, Product.class);
		query.setParameter("id", productID);

		Product product = null;
		try {
			product = query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return product;
	}
	
	public static Product selectProductByName(String name) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT p FROM Product p WHERE p.name = :name";
		TypedQuery<Product> query = em.createQuery(queryString, Product.class);
		query.setParameter("name", name);
		
		Product product = null;
		try {
			product = query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return product;
	}
	
	public static List<Product> selectAllProducts() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT p FROM Product p";
		TypedQuery<Product> query = em.createQuery(queryString, Product.class);

		List<Product> productList = new ArrayList<>();
		try {
			productList = query.getResultList();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return productList;
	}
	
	public static List<Product> filterByType(String type) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT p FROM Product p WHERE p.type = :type";
		TypedQuery<Product> query = em.createQuery(queryString, Product.class);
		query.setParameter("type", type);

		List<Product> productList = new ArrayList<>();
		try {
			productList = query.getResultList();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return productList;
	}
	
	public static List<Product> searchProductByName(String name) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT p FROM Product p WHERE p.name LIKE CONCAT('%', :name, '%')";
		TypedQuery<Product> query = em.createQuery(queryString, Product.class);
		query.setParameter("name", name.toLowerCase());
		
		List<Product> productList = new ArrayList<>();
		try {
			productList = query.getResultList();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return productList;
	}
	
	public static int getTotalCount() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT p FROM Product p";
		TypedQuery<Product> query = em.createQuery(queryString, Product.class);
		
		List<Product> productList = new ArrayList<>();
		try {
			productList = query.getResultList();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return productList.size();
	}
	
	public static int getCountOfType(String type) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT p FROM Product p WHERE p.type = :type";
		TypedQuery<Product> query = em.createQuery(queryString, Product.class);
		query.setParameter("type", type);
		
		List<Product> productList = new ArrayList<>();
		try {
			productList = query.getResultList();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return productList.size();
	}
	
	public static int getCountOfName(String name) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT p FROM Product p WHERE p.name LIKE CONCAT('%', :name, '%')";
		TypedQuery<Product> query = em.createQuery(queryString, Product.class);
		query.setParameter("name", name.toLowerCase());
		
		List<Product> productList = new ArrayList<>();
		try {
			productList = query.getResultList();
		} catch (NoResultException ex) {
			System.out.println(ex);
		} finally {
			em.close();
		}
		return productList.size();
	}
	
	public static boolean productExists(String productID) {
		Product product = selectProductByID(productID);
		return product != null;
	}
}
