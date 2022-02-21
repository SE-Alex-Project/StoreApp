package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.security.JWT.JwtTokenUtil;
import store.models.Product;
import store.services.interfaces.ProductService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public Product add(@RequestHeader("Authorization") String token, @RequestBody Product product) {
        String userEmail = jwtTokenUtil.getUserEmailFromToken(jwtTokenUtil.parseHeaderAuth(token));
        return productService.add(userEmail, product);
    }

    @PostMapping("/edit")
    public Product edit(@RequestBody Product product) {
        return productService.edit(product);
    }

    @GetMapping("/getById")
    public Product getById(@RequestParam Integer id) {
        return productService.getById(id);
    }

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/search")
    public List<Product> search() {
        return productService.search();
    }

    @GetMapping("/delete")
    public void delete(@RequestParam Integer id) {
        productService.delete(id);
    }
}
