package store.services.interfaces;

import store.models.Role;
import store.models.Store;

import java.util.List;

public interface StoreServiceInterface {
    void addStore(Store store);
    List<Store> getAllStores();
    Store getStoreById(Integer store_id);
    void deleteStore(Integer store_id);
}
