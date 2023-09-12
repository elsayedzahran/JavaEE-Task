package org.example;

import repositories.ProductRepository;

public class Main {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        var products = repository.listProducts();


        for(var product : products){
            System.out.println(product.getName() + " : " + product.getId());
        }
    }
}