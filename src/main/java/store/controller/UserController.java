package store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.models.User;
import store.security.JWT.JwtTokenUtil;
import store.services.interfaces.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserService userService;

    @GetMapping("/logOut")
    public void logOut(@RequestHeader("Authorization") String token) {
        // delete token .................
    }


    @GetMapping("/get_info")
    public User userInfo(@RequestHeader("Authorization") String token) {
        String userEmail =  jwtTokenUtil.getUserEmailFromToken(jwtTokenUtil.parseHeaderAuth(token));
        return userService.GetUserByEmail(userEmail);
    }


    @GetMapping("/delete_account")
    public void deleteMyAccount(@RequestHeader("Authorization") String token) {
        String userEmail =  jwtTokenUtil.getUserEmailFromToken(jwtTokenUtil.parseHeaderAuth(token));
        userService.DeleteUserByEmail(userEmail);
    }


    @PostMapping("/modifyInfo")
    public User modifyInfo(@RequestBody User modifyUser) {
        return userService.UpdateUser(modifyUser);
    }

    @PostMapping("/removeAccount")
    public void removeAccount(@RequestBody String email) {
        userService.DeleteUserByEmail(email);
    }


}
//@CrossOrigin
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @PostMapping("/logOut")
//    public void logOut(@RequestBody String userToken) {
//        tokenManager.removeUser(userToken);
//    }
//
//    /*
//     * modify info json format { "id": user token "data": { "password": new password
//     * "firstName": user name "lastName": user last name } }
//     */
//    @SuppressWarnings("rawtypes")
//    @PostMapping("/modifyInfo")
//    public ResponseEntity<String> modifyInfo(@RequestBody JSONObject modifyJson) {
//        try {
//
//            String userEmail = tokenManager.getUser(modifyJson.getAsString("id"));
//            if (userEmail == null)
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Not Signed In\nSign In first\n");
//            userDataBase.modifyUserinfo(userEmail, (LinkedHashMap) modifyJson.get("data"));
//            return ResponseEntity.status(HttpStatus.OK).body(null);
//        } catch (SQLException e) {
//            return Controller.SqlEx(e);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    /*
//     * return json object same as signup object
//     */
//    @PostMapping("/info")
//    public ResponseEntity<?> userInfo(@RequestBody String userToken) {
//        try {
//            String userEmail = tokenManager.getUser(userToken);
//            if (userEmail == null)
//            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Not Signed In\nSign In first\n");
//            return ResponseEntity.status(HttpStatus.OK).body(userDataBase.getUserInfo(userEmail));
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return Controller.SqlEx(e);
//        } catch (ParseException e) {
//            return Controller.ParserEx(e);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }
//
//    /*
//     * return logged out As String
//     */
//    @PostMapping("/delete")
//    public ResponseEntity<?> deleteMyAccount(@RequestBody String userToken) {
//        try {
//            String userEmail = tokenManager.getUser(userToken);
//            if (userEmail == null)
//            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Not Signed In\nSign In first\n");
//            UserType user = Authentication.tokenUserType(userToken);
//            if (user == UserType.Manager) {
//                int n = userDataBase.numberOfManagers();
//                if (n == 1) {
//                	return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Can't delete last Manager\n");
//                }
//            }
//            String temp = userDataBase.deleteAccount(userEmail,user);
//            logOut(userToken);
//            return ResponseEntity.status(HttpStatus.OK).body(temp);
//        } catch (SQLException e) {
//            return Controller.SqlEx(e);
//        }
//    }
//
//    /*
//     * {
//     * "token":userToken
//     * "email":deleted email
//     */
//    @PostMapping("/removeAccount")
//    public ResponseEntity<?> removeAccount(@RequestBody JSONObject removeJson) {
//    	try {
//	    	String userToken = removeJson.getAsString("token");
//	    	String email = tokenManager.getUser(userToken);
//	    	String deleted = removeJson.getAsString("email");
//	    	if(email.equalsIgnoreCase(deleted)) {
//	    		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Can't remove yourself.\n");
//	    	}
//	    	UserType user = Authentication.tokenUserType(userToken);
//	    	UserType d = Authentication.getUserType(deleted);
//	    	if(user == UserType.Customer) {
//	    		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("this account can't delete any account.\n");
//	    	}else if(user == UserType.Employee) {
//	    		if(d != UserType.Customer) {
//	    			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Can't delete this account\n");
//	    		}
//	    	}
//	    	userDataBase.deleteAccount(deleted, d);
//	    	return ResponseEntity.status(HttpStatus.OK).body(null);
//    	} catch (SQLException e) {
//            return Controller.SqlEx(e);
//        }
//    }
//
//    @PostMapping("/userCart_token")
//    public ResponseEntity<?> userCart_token(@RequestBody String userToken) {
//            return userCart_Email(tokenManager.getUser(userToken));
//    }
//
//    @PostMapping("/userCart_Email")
//    public ResponseEntity<?> userCart_Email(@RequestBody String userEmail) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(userDataBase.UserCart(userEmail));
//        } catch (SQLException e) {
//            return Controller.SqlEx(e);
//        } catch (ParseException e) {
//            return Controller.ParserEx(e);
//        }
//    }
//
//}
