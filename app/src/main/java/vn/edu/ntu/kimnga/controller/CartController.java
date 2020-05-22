package vn.edu.ntu.kimnga.controller;

import android.app.Application;

import java.util.ArrayList;

import vn.edu.ntu.kimnga.model.Product;

public class CartController extends Application implements ICartController {
    ArrayList<Product> listProduct = new ArrayList<>();
    ArrayList<Product> listShoppingCart = new ArrayList<>();
    public CartController() {
        listProduct.add(new Product("Xoài cát", 60000, "Xoài cát Hòa Lộc loại 1"));
        listProduct.add(new Product("Khoai lang", 45000, "Khoai lang tím giống Nhật"));
        listProduct.add(new Product("Me Thái", 45000, "Me Thái Lan nhập khẩu"));
        listProduct.add(new Product("Ổi", 60000, "Ổi chua"));
        listProduct.add(new Product("Mận tím", 50000, "Mận tím Tây Bắc"));
        listProduct.add(new Product("Táo đỏ", 60000, "Táo đỏ Mỹ"));
        listProduct.add(new Product("Sầu riêng Khánh Sơn", 70000, "Sầu riếng Khánh Sơn loại 1"));
    }

    @Override
    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    @Override
    public boolean addToShoppingCart(Product p) {
        if(!listShoppingCart.contains(p))
        {
            listShoppingCart.add(p);
            return  true;
        }
        return false;
    }

    @Override
    public ArrayList<Product> getShoppingCart() {
        return listShoppingCart ;
    }

    @Override
    public void clearShoppingCart() {
        listShoppingCart.clear();
    }
}
