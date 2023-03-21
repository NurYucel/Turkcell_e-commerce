package kodlama.io.ecommerce.repository.concretes;

import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class InMemoryProductRepository implements ProductRepository {
   private List<Product> products; //globaldeğişken
   public InMemoryProductRepository(){
       products = new ArrayList<>();
       products.add(new Product(1,"Iphone 14",10,3000,"apple"));
       products.add(new Product(2,"PS5",5,2000,"sony"));
       products.add(new Product(3,"Xbox",20,4000,"microsoft"));
       products.add(new Product(4,"Ipad Mini",50,5000,"apple"));

   }
    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getById(int id) {
        for (Product product : products) {
            if(product.getId() == id)return product;
        }
        return null;
    }

    @Override
    public Product add(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product update(int id, Product product) {
        return products.set(id - 1,product);
    }

    @Override
    public void delete(int id) {
       products.remove(id-1);
    }
}
