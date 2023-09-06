package com.jsp.Ecommerce.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.Ecommerce.dao.CartRepository;
import com.jsp.Ecommerce.dao.OrderCartRepository;
import com.jsp.Ecommerce.dao.ProductRepositry;
import com.jsp.Ecommerce.dao.UserRepository;
import com.jsp.Ecommerce.dto.Cart;
import com.jsp.Ecommerce.dto.OrderCart;
import com.jsp.Ecommerce.dto.Product;
import com.jsp.Ecommerce.dto.TempProduct;
import com.jsp.Ecommerce.dto.User;
import com.jsp.Ecommerce.dto.Wishlist;

@Service
public class CartService {
	@Autowired
	OrderCartRepository orderCartRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	ProductRepositry productRepositry;
	@Autowired
	OrderCartService orderCartService;

	public Cart addToCart(TempProduct tempProduct) {
	    try {
	        User user = userRepository.findByEmail(tempProduct.getEmail());
	        Product product = productRepositry.findById(tempProduct.getId()).orElse(null);
	        int Userid = user.getUser_id();

	        if (user != null && product != null && product.getQuantity() >= tempProduct.getQuantity()) {
	            int userId = user.getUser_id();
	            Cart cart = cartRepository.findById(userId).orElseGet(() -> {
	                Cart newCart = new Cart();
	                newCart.setUser(user);
	               
	                newCart.setOrderCarts(new ArrayList<>()); // Create a new list of OrderCart
	                user.setCart(newCart);
	                Cart cart1 = cartRepository.save(newCart);
	                return cart1;
	            });
	            int cartid = cart.getId();
	            List<OrderCart> orderCarts = cart.getOrderCarts();

	            boolean productExistsInCart = false;

	            for (OrderCart orderCart : orderCarts) {
	                if (product.getId() == orderCart.getProductid()) {
	                    orderCart.setQuantity(orderCart.getQuantity() + tempProduct.getQuantity());
	                    orderCart.setName(product.getName());
	                    orderCart.setDescription(product.getDescription());
	                    orderCart.setPrice(product.getPrice());
	                    orderCart.setCart(cart);
	                   
	                    productExistsInCart = true;

	                    Cart updatedCart = updateCartById(cartid, cart);
	                    productService.updateProductById(tempProduct.getId(), product);
	                    orderCartService.saveProduct(orderCart);

	                    return updatedCart;
	                }
	            }

	            // If the product does not exist in the cart, add it
	            OrderCart orderCart = new OrderCart();
	            orderCart.setProductid(tempProduct.getId());
	            orderCart.setQuantity(tempProduct.getQuantity());
	            orderCart.setName(product.getName());
	            orderCart.setDescription(product.getDescription());
	            orderCart.setPrice(product.getPrice());
	            orderCart.setCart(cart);

	            orderCarts.add(orderCart);
	            cart.setOrderCarts(orderCarts);
	            cart.setUser(user);

	            // Deduct quantity from the product
	            product.setQuantity(product.getQuantity() - tempProduct.getQuantity());

	            userService.updateByEmail(tempProduct.getEmail(), user);

	            Cart updatedCart = cartRepository.save(cart);
	            productService.updateProductById(tempProduct.getId(), product);
	            orderCartService.saveProduct(orderCart);

	            return updatedCart;
	        }
	    } catch (Exception e) {
	        // Handle exceptions
	    }

	    return null;
	}

	private Cart updateCartById(int cartid, Cart cart) {
		Cart cart2 = cartRepository.findById(cartid).get();
		Cart cart3 = cartRepository.save(cart);
		return cart3;
	}
	 // ... Other service methods ...

	public Cart updateCart(TempProduct tempProduct) {
	    try {
	        User user = userRepository.findByEmail(tempProduct.getEmail());
	        Product product = productRepositry.findById(tempProduct.getId()).orElse(null);
	        System.out.println("========================");
	        if (user != null && product != null) {
	            Cart cart = cartRepository.findById(user.getUser_id()).orElse(null);
	            System.out.println(cart.getId());

	            if (cart != null) {
	                System.out.println("=======cart=============");
	                List<OrderCart> orderCarts = cart.getOrderCarts();
System.out.println("===========cart1================");
	                for (OrderCart orderCart : orderCarts) {
	                	System.out.println("===============cart2===============");
	                	System.out.println(orderCart.getProductid());
	                    if (orderCart.getProductid() == tempProduct.getId()) {
	                        System.out.println("===========1====================");
	                        if (tempProduct.getQuantity() > 0 && orderCart.getQuantity() <= tempProduct.getQuantity()) {
	                            // Update the quantity of the product in the cart
	                            System.out.println("==============2======================");
	                            orderCart.setQuantity(orderCart.getQuantity() + tempProduct.getQuantity());

	                            // Update the product quantity in the database (set it to the new quantity)
	                            product.setQuantity(product.getQuantity() - tempProduct.getQuantity()); // Update the product quantity

	                            orderCart.setCart(cart);

	                            // Save the changes to the database
	                            cart.setOrderCarts(orderCarts);
	                            productRepositry.save(product);
	                            orderCartRepository.save(orderCart);
	                            cartRepository.save(cart);

	                            System.out.println("========update cart===============");
	                            // productService.updateProductById(id, product); // You might not need this if the product quantity is correctly updated

	                            return cart;
	                        } else if (tempProduct.getQuantity() > 0 && orderCart.getQuantity() > tempProduct.getQuantity()) {
	                            System.out.println("============3==========================");
	                            // Update the quantity of the product in the cart
	                            orderCart.setQuantity(orderCart.getQuantity() - tempProduct.getQuantity());

	                            // Update the product quantity in the database (increase it by the removed quantity)
	                            product.setQuantity(product.getQuantity() + tempProduct.getQuantity());

	                            orderCart.setCart(cart);

	                            // Save the changes to the database
	                            cart.setOrderCarts(orderCarts);
	                            productRepositry.save(product);
	                            orderCartRepository.save(orderCart);
	                            cartRepository.save(cart);

	                            System.out.println("========update cart===============");
	                            // productService.updateProductById(id, product); // You might not need this if the product quantity is correctly updated

	                            return cart;
	                        } else if (tempProduct.getQuantity() < 0) {
	                            // If the user wants to remove the product from the cart
	                            System.out.println("==========remove method===========");

	                            // Update the product quantity in the database (restore the stock)
	                            product.setQuantity(product.getQuantity() + orderCart.getQuantity());

	                            // Remove the orderCart from the list and database
	                            orderCarts.remove(orderCart);
	                            orderCartRepository.delete(orderCart);
	                            cart.setOrderCarts(orderCarts);

	                            // Save the changes to the database
	                            cartRepository.save(cart);
	                            productRepositry.save(product);

	                            return cart;
	                        }
	                    }
	                }
	            }
	        }
	    } catch (Exception e) {
	        // Handle exceptions
	    }

	    return null;
	}

}