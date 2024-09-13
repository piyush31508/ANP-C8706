package com.ui;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.dto.Products;

public class UiMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("products");
		EntityManager em = emf.createEntityManager();


//		Products pro = em.find(Products.class, 3);
//		if (pro != null) {
//			System.out.println(pro);
//		} else {
//			System.out.println("Product not found");
//		}
		
		
//		Products p4 = new Products(4, "Earbuds", 399.2);
//		em.getTransaction().begin();
//
//		em.persist(p4);
//
//		em.getTransaction().commit();
//		System.out.println("Product added successfully");
//		em.close();

//		Products pro ;
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Write the ID of the product which you want to remove");
//		int id = sc.nextInt();
//		em.getTransaction().begin();
//
//		pro = em.find(Products.class,id);
//		if(pro!=null) {
//			em.remove(pro);
//			System.out.println("Product whose id is "+id+" have been removed sucessfully");
//		}
//		else {
//			System.out.println("Not in database");
//		}
//		em.getTransaction().commit();
//		sc.close();
//		em.close();
		Scanner sc = new Scanner(System.in);
		System.out.println("Write the id of the product which you want to update");
		int id = sc.nextInt();
		Products p;
		
		p=em.find(Products.class, id);
		if(p!=null) {
			em.getTransaction().begin();
			p.setPrice(p.getPrice()+500);
			em.getTransaction().commit();
			em.close();
		}
		else
			System.out.println("Not found in DB");
		
	}
}
