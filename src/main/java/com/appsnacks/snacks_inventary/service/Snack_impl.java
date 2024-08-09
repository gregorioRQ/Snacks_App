package com.appsnacks.snacks_inventary.service;

import com.appsnacks.snacks_inventary.models.Snack;
import com.appsnacks.snacks_inventary.repository.iSnackRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Snack_impl implements iSnack {
    
    @Autowired
    private iSnackRepository repository;

    @Override
    public List<Snack> getAllSnack() {
       return repository.findAll();
    }

    @Override
    public Snack chargeSnack(Snack snack) {
        return repository.save(snack);
    }

    @Override
    public Snack getSnackById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public Snack updateSnack(Snack snack) {
        return repository.save(snack);
    }

    @Override
    public void deleteSnack(int id) {
        repository.deleteById(id);
    }

}
