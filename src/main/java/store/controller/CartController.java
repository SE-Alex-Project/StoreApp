package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.models.Cart;
import store.models.Product;
import store.security.JWT.JwtTokenUtil;
import store.services.interfaces.CartService;

import javax.validation.Valid;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    CartService cartService;


    @PostMapping("/buy")
    public void buyCart(@RequestHeader("Authorization") String token) {
        String userEmail =  jwtTokenUtil.getUserEmailFromToken(jwtTokenUtil.parseHeaderAuth(token));
        cartService.buyCart(userEmail,1);
    }

    @PostMapping("/get")
    public Cart getCart(@RequestHeader("Authorization") String token) {
        String userEmail =  jwtTokenUtil.getUserEmailFromToken(jwtTokenUtil.parseHeaderAuth(token));
        return cartService.getCart(userEmail);
    }

    @PostMapping("/add")//validate_addToCart
    public void addToCart(@RequestHeader("Authorization") String token ,@Valid @RequestBody Map<Product,Integer> products ) {
        String userEmail =  jwtTokenUtil.getUserEmailFromToken(jwtTokenUtil.parseHeaderAuth(token));
        cartService.addToCart(userEmail,products);
    }


    @PostMapping("/modify")
    public void modify(@RequestHeader("Authorization") String token ,@Valid @RequestBody Map<Product,Integer> products) {
        String userEmail =  jwtTokenUtil.getUserEmailFromToken(jwtTokenUtil.parseHeaderAuth(token));
        cartService.modifyCart(userEmail,products);
    }

}
