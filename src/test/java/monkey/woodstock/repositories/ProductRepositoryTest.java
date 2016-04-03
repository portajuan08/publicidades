package monkey.woodstock.repositories;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import monkey.woodstock.configuration.RepositoryConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ProductRepositoryTest {

    /*
    @Test
    public void testSaveProduct(){
    	
        //setup product
        Chofer product = new Chofer();

        //save product, verify has ID value after save
        assertNull(product.getId()); //null before save
        productRepository.save(product);
        assertNotNull(product.getId()); //not null after save
        //fetch from DB
        Chofer fetchedProduct = productRepository.findOne(product.getId());

        //should not be null
        assertNotNull(fetchedProduct);

        //should equal
        assertEquals(product.getId(), fetchedProduct.getId());

        //update description and save
        productRepository.save(fetchedProduct);

        //get from DB, should be updated
        Chofer fetchedUpdatedProduct = productRepository.findOne(fetchedProduct.getId());

        //verify count of products in DB
        long productCount = productRepository.count();
        assertEquals(productCount, 1);

        //get all products, list should only have one
        Iterable<Chofer> products = productRepository.findAll();

        int count = 0;

        for(Chofer p : products){
            count++;
        }

        assertEquals(count, 1);
    }*/
}
