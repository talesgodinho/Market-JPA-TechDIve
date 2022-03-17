package market.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import market.model.dao.ProductDAO;
import market.model.persistence.Product;

public class ProductService {

	private Logger LOG = LogManager.getLogger(ProductService.class);

	private EntityManager entityManager;

	private ProductDAO productDAO;

	public ProductService(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.productDAO = new ProductDAO(entityManager);
	}
	
	private void getBeginTransaction() {
		this.LOG.info("Abrindo transação");
		entityManager.getTransaction().begin();
	}

	private void commitAndCloseTransaction() {
		this.LOG.info("Commitando e fechando transação com o banco");
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void create(Product product) {
		this.LOG.info("Preparando para a criação de um produto");
		if (product == null) {
			this.LOG.error("o produto informado está nulo");
			throw new RuntimeException("The product is null");
		}
		try {
			getBeginTransaction();
			this.productDAO.create(product);

			commitAndCloseTransaction();
		} catch (Exception e) {
			this.LOG.error("Erro ao criar um produto, causado por: " + e.getMessage());
			throw new RuntimeException(e);
		}
		this.LOG.info("Produto criado com sucesso");
	}

	public void delete(Long id) {
		
		this.LOG.info("Preparando para buscar produto.");
		
		if (id == null) {
			this.LOG.error("O ID do produto informado está nulo.");
			throw new RuntimeException("The ID is null");
		}
		Product product = this.productDAO.getById(id);
		
		if (product == null) {
			this.LOG.error("O produto não existe.");
			throw new EntityNotFoundException("The product not found");
		}
		
		this.LOG.info("Produto encontrado com sucesso.");
		
		
		getBeginTransaction();
		this.productDAO.delete(product);
		commitAndCloseTransaction();
		this.LOG.info("Produto deletado com sucesso.");
		
	}
	

}
