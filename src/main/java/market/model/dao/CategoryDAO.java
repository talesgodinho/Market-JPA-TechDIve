package market.model.dao;

import javax.persistence.EntityManager;

import market.model.persistence.Category;

public class CategoryDAO {

	private EntityManager entityManager;
	
	public CategoryDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void create(Category category) {
		this.entityManager.persist(category);		
	}
	
}
