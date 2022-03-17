package market.application;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import market.connection.JpaConnectionFactory;
import market.model.persistence.Category;
import market.model.persistence.Product;
import market.services.CategoryService;
import market.services.ProductService;

public class Program {

	private static Logger LOG = LogManager.getLogger(Program.class);

	public static void main(String[] args) {
		EntityManager entityManager = new JpaConnectionFactory().getEntityManager();
		ProductService productService = new ProductService(entityManager);
//		CategoryService categoryService = new CategoryService(entityManager);

//		Product product = new Product("Cheetos", "Requeijão 180g", new BigDecimal(12.99), 
//				new Category("Alimento"));

//		productService.create(product);
		productService.delete(2L); //long precisa do L
	}
}
