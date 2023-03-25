package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.Product;
import kodlama.io.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
@Repository
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }
    @Override
    public Product getById(int id){
        checkIfBrandExists(id);
        return repository.findById(id).orElseThrow();
    }
    @Override
    public Product add(Product product) {
        validateProduct(product);
        return repository.save(product);
    }

    @Override
    public Product update(int id, Product product) {
        validateProduct(product);
        return repository.save(product);
    }
    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
    private void validateProduct(Product product){
        checkIfPriceValid(product);
        checkIfQuantityValid(product);
        checkIfDescriptionLengthValid(product);
    }
    private void checkIfPriceValid(Product product){
        if(product.getPrice() <= 0) throw new IllegalArgumentException("Price cannot be less than or equal to 0");
    }
    private void checkIfQuantityValid(Product product){
        if(product.getQuantity() < 0) throw new IllegalArgumentException("Quantity cannot be less than 0");
    }
    private void checkIfDescriptionLengthValid(Product product) {
        if(product.getDescription().length() < 10 ||
                product.getDescription().length() > 50 )
            throw new IllegalArgumentException("Description cannot be less than 10 and bigger than 50 characters");
    }

    private void checkIfBrandExists(int id){
        if(!repository.existsById(id)){
            throw  new RuntimeException("Can not find any product");
        }
    }

}
