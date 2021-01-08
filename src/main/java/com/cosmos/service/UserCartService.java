package com.cosmos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.model.Item;
import com.cosmos.model.UserCart;
import com.cosmos.repository.ItemRepository;
import com.cosmos.repository.UserCartRepository;

@Service
public class UserCartService {
	@Autowired
	private UserCartRepository userCartRepository;
	@Autowired
	private ItemRepository itemRepository;

	public UserCart getUserCart(Long mobileNumber) {
		// TODO Auto-generated method stub
		
		Optional<UserCart> dbcart = userCartRepository.findById(mobileNumber);
		if(dbcart.isPresent()) {
			return dbcart.get();
		}else {
			return null;
		}
	}

	public UserCart saveUserCart(UserCart userCart) {
		// TODO Auto-generated method stub
		UserCart cart = null;
		Optional<UserCart> dbcart = userCartRepository.findById(userCart.getMobileNumber());
		if(dbcart.isPresent()) {
			cart= dbcart.get();
			cart.getItems().stream()
			.forEach(i->System.out.println("Before adding UI products i.e only db items: "+i.getItemId()+" "+i.getProductId()+" "+i.getQuantityOfProduct()));
			cart.getItems().addAll(userCart.getItems());
			cart.getItems().stream()
			.forEach(item->item.setCart(userCart));
			cart =userCartRepository.save(cart);
		}else{
			userCart.getItems().stream()
			.forEach(item->item.setCart(userCart));
			cart =userCartRepository.save(userCart);
		}
		cart.getItems().stream()
		.forEach(i->System.out.println(i.getItemId()+" "+i.getProductId()));
		return cart;
	}

	public void updateUserCart(Long mobileNumber, UserCart cart) {
		System.out.println("updateUserCart called");
		cart.getItems().stream()
		.forEach(i->System.out.print(i.getItemId()));
		cart.getItems().stream()
		.forEach(item->item.setCart(cart));
		cart.getItems().stream()
		.forEach(i->itemRepository.save(i));
		System.out.println("updateUserCart called and ended successfully");
		
	}

}
