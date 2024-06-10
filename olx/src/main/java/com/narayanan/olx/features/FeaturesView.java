package com.narayanan.olx.features;

import com.narayanan.olx.model.Product;
import com.narayanan.olx.setup.SetUpView;

import java.util.Scanner;

public class FeaturesView {
    private FeaturesModel featuresModel;
    private Scanner scanner;

    public FeaturesView() {
        this.featuresModel = new FeaturesModel(this);
        this.scanner = new Scanner(System.in);
    }

    public void init() {
        while (true) {
            System.out.println("\n1.View Product List\n2.View Product Details\n3.Search \n4.Buy Product\n5.Sell Product\n6.Add To Cart\n9.Log Out\n10.Exit\nEnter Your Choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewProductList();
                    break;
                case 2:
                    viewProductDesc();
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    buyProduct();
                    break;
                case 5:
                    sellProduct();
                    break;
                case 6:
                    setFavorite();
                    break;
                case 9:
                    new SetUpView().init();
                    return;
                case 10:
                    System.out.println("\t\t\tThank You For Useing App");
                    return;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    private void setFavorite() {
        System.out.println("Enter Product id :");
        int id = scanner.nextInt();
        featuresModel.addFavorite(id);
    }

    private void search() {
        System.out.print("Enter Category : ");
        String category = scanner.next();
        featuresModel.searchCategory(category);
    }

    private void buyProduct() {
        System.out.print("Enter Product id :");
        int id = scanner.nextInt();
        featuresModel.buyProduct(id);
    }

    private void viewProductDesc() {
        System.out.print("Enter Product id :");
        int id = scanner.nextInt();
        featuresModel.getProduct(id);
    }

    private void viewProductList() {
        featuresModel.getProducts();
    }

    private void sellProduct() {
        System.out.print("Enter Product Name :");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter Product Price :");
        double price = scanner.nextDouble();
        System.out.print("Enter Product Category :");
        String category = scanner.next();
        scanner.nextLine();
        System.out.print("Enter Product Description :");
        String description = scanner.nextLine();
        System.out.print("Enter about Product :");
        String about = scanner.nextLine();
        featuresModel.addProduct(name, price, category, description, about);
    }

    public void showMsg(String msg) {
        if (msg.equals("print")) {
            System.out.printf("%-2s | %-15s | %-8s | %-20S ", "Id", "Nmae", "Price", "Category");
            System.out.println();
            System.out.println("-----------------------------------------------");
        } else
            System.out.println(msg);
    }

    public void showProduct(Product product) {
        System.out.printf("%-2d | %-15s | %-8.2f | %-20S ", product.getId(), product.getName(), product.getPrice(), product.getCategory());
        System.out.println();
    }

    public void showProductDesc(Product product) {
        System.out.println("ID: " + product.getId());
        System.out.println("Name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Category: " + product.getCategory());
        System.out.println("Description: " + product.getDescription());
        System.out.println("About: " + product.getAbout());
        System.out.println("Owner Email: " + product.getOwnerEmail());
    }

    public void checkBuy(int id) {
        System.out.println("Do you confirm buy Type yes");
        if (scanner.next().equals("yes")) {
            featuresModel.confirmBuy(id);
        }
    }
}
