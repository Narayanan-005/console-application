package com.narayanan.olx.datalayer;

import com.narayanan.olx.model.Credential;
import com.narayanan.olx.model.Product;
import com.narayanan.olx.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static DataBase dataBase;
    private String currentEmail;
    private User currentUser;
    private int productId;
    List<User> users;
    List<Credential> credentials;
    List<Product> products;

    private DataBase() {
        this.users = new ArrayList<>();
        this.credentials = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public boolean userExist(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void addUser(User user, String password) {
        users.add(user);
        Credential credential = new Credential();
        credential.setEmail(user.getEmail());
        credential.setPasseword(password);
        credentials.add(credential);
    }

    public boolean validateUser(String email, String password) {
        for (Credential credential : credentials) {
            if (credential.getEmail().equals(email) && credential.getPasseword().equals(password)) {
                this.currentEmail = email;
                this.currentUser = getUser(email);
                this.currentUser.setFavourites(new ArrayList<>());
                return true;
            }
        }
        return false;
    }

    private User getUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        product.setId(++this.productId);
        product.setOwnerEmail(this.currentEmail);
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void buyProduct(int id) {
        Product product = getProduct(id);
        updateRevenue(product.getOwnerEmail(), product.getPrice());
        products.remove(product);

    }

    private void updateRevenue(String ownerEmail, double price) {
        for (User user : users) {
            if (user.getEmail().equals(ownerEmail)) {
                user.setRevenue(user.getRevenue() + price);
                break;
            }
        }
    }

    public List<Product> searchCategory(String category) {
        List<Product> products1 = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                products1.add(product);
            }
        }
        return products1;
    }

    public boolean addFavorite(int id) {
        Product product = getProduct(id);
        if (product != null) {
            List<Product> products1 = currentUser.getFavourites();
            for (Product product1 : products1) {
                if (product1.getId() == product.getId()) {
                    return false;
                }
            }
            return products1.add(product);
        }
        return false;
    }
}
