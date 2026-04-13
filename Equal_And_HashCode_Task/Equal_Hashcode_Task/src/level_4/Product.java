package level_4;

import java.util.Objects;

public class Product {

    int code;
    double price;

    public Product(int id,double price){
        this.code =id;
        this.price=price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return code == product.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Product{code='" + code + "', price=" + price + "}";
    }
}
