package com.narayanan.olx.features;

import com.narayanan.olx.datalayer.DataBase;
import com.narayanan.olx.model.Product;

import java.util.List;

class FeaturesModel {
    private FeaturesView featuresView;

    FeaturesModel(FeaturesView featuresView) {
        this.featuresView = featuresView;
    }

    public void addProduct(String name, double price, String category, String description, String about) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setDescription(description);
        product.setAbout(about);
        DataBase.getInstance().addProduct(product);
        featuresView.showMsg("Product Added Successfully");
    }

    public void getProducts() {
        List<Product> products = DataBase.getInstance().getProducts();
        if (products.size() == 0) {
            featuresView.showMsg("No Product Found");
        } else {
            featuresView.showMsg("print");
            for (Product product : products) {
                featuresView.showProduct(product);
            }
        }
    }

    public void getProduct(int id) {
        Product product = DataBase.getInstance().getProduct(id);
        if (product != null) {
            featuresView.showProductDesc(product);
        } else {
            featuresView.showMsg("No Product Found");
        }
    }

    public void buyProduct(int id) {
        Product product = DataBase.getInstance().getProduct(id);
        if (product != null) {
            featuresView.showProductDesc(product);
            featuresView.checkBuy(id);
        } else {
            featuresView.showMsg("No Product Found");
        }
    }

    public void confirmBuy(int id) {
        DataBase.getInstance().buyProduct(id);
        featuresView.showMsg("Product Buyed Successfully");
    }

    public void searchCategory(String category) {
        List<Product> products = DataBase.getInstance().searchCategory(category);
        if (products.size() == 0) {
            featuresView.showMsg("No Product Found");
        } else {
            featuresView.showMsg("print");
            for (Product product : products) {
                featuresView.showProduct(product);
            }
        }
    }

    public void addFavorite(int id) {
        boolean status = DataBase.getInstance().addFavorite(id);
        if(status){
            featuresView.showMsg("Favorite Added Successfully");
        }else {
            featuresView.showMsg("Invalid Product Id / Already added as Favorite");
        }
    }
}
