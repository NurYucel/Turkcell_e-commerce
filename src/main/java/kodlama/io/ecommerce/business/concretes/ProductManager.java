package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service//otomatik new'leme
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    public ProductManager(ProductRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Product> getAll() {
        return repository.getAll();
    }
    @Override
    public Product getById(int id){
        return repository.getById(id);
    }
    @Override
    public Product add(Product product) {
        validateProduct(product);
        return repository.add(product);
    }

    @Override
    public Product update(int id, Product product) {
        validateProduct(product);
        return update(id,product);
    }
    @Override
    public void delete(int id) {
        repository.delete(id);
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
}
