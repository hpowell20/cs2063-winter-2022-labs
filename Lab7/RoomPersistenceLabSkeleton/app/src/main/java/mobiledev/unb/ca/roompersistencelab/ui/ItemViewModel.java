package mobiledev.unb.ca.roompersistencelab.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import mobiledev.unb.ca.roompersistencelab.repository.ItemRepository;

public class ItemViewModel extends AndroidViewModel {
    private final ItemRepository itemRepository;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
    }

    // TODO
    //  Add mapping calls between the UI and Database
}
