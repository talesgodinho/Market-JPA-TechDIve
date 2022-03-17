package market.services;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import market.model.dao.CategoryDAO;
import market.model.dao.ProductDAO;
import market.model.persistence.Category;
import market.model.persistence.Product;

public class CategoryService {
	
	private Logger LOG = LogManager.getLogger(ProductService.class);
	
	private EntityManager entityManager;
	
	private CategoryDAO categoryDAO;
	
	public CategoryService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void create(Category category) {
		if (category == null) {
			this.LOG.error("o produto informado está nulo");
			throw new RuntimeException("O produto está nulo");
		}
		try {
			getBeginTransaction();
			this.categoryDAO.create(category);
			
			commitAndCloseTransaction();
		} catch (Exception e) {
			this.LOG.error("Erro ao criar um produto, causado por: " + e.getMessage());
			throw new RuntimeException(e);
		}

	}

	private void getBeginTransaction() {
		entityManager.getTransaction().begin();
	}

	private void commitAndCloseTransaction() {
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
