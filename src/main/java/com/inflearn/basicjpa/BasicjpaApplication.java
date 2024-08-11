package com.inflearn.basicjpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BasicjpaApplication {

	public static void main(String[] args) {

		SpringApplication.run(BasicjpaApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("inflearn");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {

			Member member = em.find(Member.class, 1L);
			log.info("Member : {}", member);
			member.setName("young-han");

			tx.commit();
		}
		catch (Exception e) {
			tx.rollback();
		}
		finally {
			em.close();
		}

		emf.close();
	}

}
