package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import store.models.Store;
import store.repos.StoreRepo;
import store.services.interfaces.StoreService;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreRepo storeRepo;

    @Override
    public void addStore(Store store) {
        storeRepo.save(store);
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepo.findAll();
    }

    @Override
    public Store getStoreById(Integer store_id) {
        return storeRepo.findById(store_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store Doesn't Exist!"));
    }

    @Override
    public void deleteStore(Integer store_id) {
        storeRepo.deleteById(store_id);
    }
}
