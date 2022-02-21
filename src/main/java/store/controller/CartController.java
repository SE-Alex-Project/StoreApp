package store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.models.User;
import store.repos.CartRepo;
import store.repos.UserRepo;
import store.security.JWT.JwtTokenUtil;
import store.services.Implementation.CartService;


@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    CartService cartService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CartRepo cartRepo;


    @PostMapping("/buy")
    public void buyCart(@RequestHeader("Authorization") String token) {
        String userEmail =  jwtTokenUtil.getUserEmailFromToken(jwtTokenUtil.parseHeaderAuth(token));
        cartService.BuyCart(userEmail,1);
        System.out.println("success");
    }

}
