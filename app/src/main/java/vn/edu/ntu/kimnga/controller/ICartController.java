package vn.edu.ntu.kimnga.controller;

import java.util.ArrayList;

import vn.edu.ntu.kimnga.model.Product;

public interface ICartController {
    public ArrayList<Product> getListProduct();
    public boolean addToShoppingCart(Product p);
    public ArrayList<Product> getShoppingCart();
    public void clearShoppingCart();
}
