//package store.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Data;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Data
//@Entity
//public class Customer{
//
//    @Id
//    private String id;
//
//    @MapsId
//    @OneToOne(cascade = CascadeType.ALL)
//    private User user;
//
//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.ALL)
//    @NotNull
//    private Cart cart;
//
//}
