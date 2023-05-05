package Question1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Product {
    private String id;
    private String name;
    private boolean warranty;
    private String category;

    public Product(String id, String name, boolean warranty, String category) {
        this.id = id;
        this.name = name;
        this.warranty = warranty;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasWarranty() {
        return warranty;
    }

    public String getCategory() {
        return category;
    }

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("1", "Samsung Galaxy S21", true, "Mobile"),
                new Product("2", "Dell XPS 13", true, "Laptop"),
                new Product("3", "LG OLED TV", false, "TV"),
                new Product("4", "Whirlpool Fridge", true, "Refrigerator"),
                new Product("5", "OnePlus Nord", false, "Mobile"),
                new Product("6", "MacBook Air", false, "Laptop"),
                new Product("7", "Sony Bravia TV", true, "TV"),
                new Product("8", "Samsung Side-by-Side Fridge", true, "Refrigerator")
        );

        String filterCategory = "Mobile";

        Predicate<Product> hasWarranty = Product::hasWarranty;

        List<Product> withWarranty = new ArrayList<>();
        List<Product> withoutWarranty = new ArrayList<>();

        for (Product product : products) {
            if (product.getCategory().equals(filterCategory)) {
                if (hasWarranty.test(product)) {
                    withWarranty.add(product);
                } else {
                    withoutWarranty.add(product);
                }
            }
        }

        List<List<Product>> result = Arrays.asList(withWarranty, withoutWarranty);

        result.forEach(list -> {
            System.out.println("--------");
            list.forEach(product -> System.out.println(product.getName()));
        });
    }
}
