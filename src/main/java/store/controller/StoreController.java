package store.controller;
//

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.models.Store;
import store.security.JWT.JwtTokenUtil;
import store.services.Implementation.StoreServiceImpl;
import store.services.interfaces.StoreService;

//
//
@RestController
@CrossOrigin
@RequestMapping("/store")
public class StoreController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    StoreService storeService;

    @PostMapping("/addStore")
    public Store addStore(@RequestBody Store store) {
        storeService.addStore(store);
        return  store;
    }

//
//    /*store json format
//   {
//   "token": token
//   "name": "name",
//   "location":"location"
//   }
//    */
//    @PostMapping("/add")
//    public ResponseEntity<String> addStore(@RequestBody JSONObject store) {
//        try {// validate Employee or Manager
//            Validation.validate_addstore(store);
//            storeDataBase.add(store.getAsString("name"), store.getAsString("location"));
//            return ResponseEntity.status(HttpStatus.OK).body(null);
//        } catch (SQLException e) {
//            return Controller.SqlEx(e);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/get_list")
//    public ResponseEntity<?> getStoreList() {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(storeDataBase.getList());
//        } catch (SQLException e) {
//            return Controller.SqlEx(e);
//        } catch (ParseException e) {
//            return Controller.ParserEx(e);
//        }
//    }
//
//    @PostMapping("/get")
//    public ResponseEntity<?> getStore(@RequestBody String store_id) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(storeDataBase.get(store_id));
//        } catch (SQLException e) {
//            return Controller.SqlEx(e);
//        } catch (ParseException e) {
//            return Controller.ParserEx(e);
//        }
//    }
//
//    @PostMapping("/delete")
//    public ResponseEntity<?> deleteStore(@RequestBody String store_id) {
//        try {
//            storeDataBase.delete(store_id);
//            return ResponseEntity.status(HttpStatus.OK).body(null);
//        } catch (SQLException e) {
//            return Controller.SqlEx(e);
//        }
//    }
//
//
}
