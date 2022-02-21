package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.models.Store;
import store.repos.StoreRepo;
import store.repos.UserRepo;
import store.services.interfaces.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    StoreRepo storeRepo;

    @Override
    public void addStore(Store store) {
        storeRepo.save(store);
    }
}
