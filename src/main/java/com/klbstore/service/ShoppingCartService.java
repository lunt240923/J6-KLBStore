package com.klbstore.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klbstore.model.ChiTietGioHang;
import com.klbstore.model.GioHang;

import jakarta.servlet.http.HttpSession;

@Service
public class ShoppingCartService {
    private static final String CART_SESSION_KEY = "cart";

    @Autowired
    private HttpSession httpSession;

    public GioHang getCart() {
        GioHang cart = (GioHang) httpSession.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new GioHang();
            httpSession.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    public void addToCart(ChiTietGioHang cartItem) {
        GioHang cart = getCart();
        List<ChiTietGioHang> cartItems = cart.getGioHangChiTietGioHangs();
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            cart.setGioHangChiTietGioHangs(cartItems);
        }
        
        boolean existingItem = false;
        
        for (ChiTietGioHang item : cartItems) {
            if (item.getMauSac().getMauSacId() == cartItem.getMauSac().getMauSacId()) {
                item.setSoLuong(item.getSoLuong() + 1);
                existingItem = true;
                break;
            }
        }
        
        if (!existingItem) {
            cartItems.add(cartItem);
            cartItem.setGioHang(cart);
        }
    }
    
    public void removeCart(int colorId) {
        GioHang cart = getCart();
        List<ChiTietGioHang> cartItems = cart.getGioHangChiTietGioHangs();
        
        if (cartItems != null) {
            Iterator<ChiTietGioHang> iterator = cartItems.iterator();
            
            while (iterator.hasNext()) {
                ChiTietGioHang cartItem = iterator.next();
                
                if (cartItem.getMauSac().getMauSacId() == colorId) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    public double getCartTotalAmount() {
        GioHang cart = getCart();
        double totalAmount = 0.0;
        List<ChiTietGioHang> cartItems = cart.getGioHangChiTietGioHangs();
        if (cartItems != null) {
            for (ChiTietGioHang cartItem : cartItems) {
                double itemPrice = cartItem.getSanPham().getGiaBan();
                int quantity = cartItem.getSoLuong();
                totalAmount += itemPrice * quantity;
            }
        }
        return totalAmount;
    }
   public ChiTietGioHang updateCart(int colorId, int quantity) {
        GioHang cart = getCart();
        List<ChiTietGioHang> cartItems = cart.getGioHangChiTietGioHangs();

        if (cartItems != null) {
            for (ChiTietGioHang cartItem : cartItems) {
                if (cartItem.getMauSac().getMauSacId() == colorId) {
                    cartItem.setSoLuong(quantity);
                    return cartItem;
                }
            }
        }
        
        return null;
    }
}
