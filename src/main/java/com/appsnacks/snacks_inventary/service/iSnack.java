package com.appsnacks.snacks_inventary.service;

import com.appsnacks.snacks_inventary.models.Snack;
import java.util.List;

public interface iSnack {
    public List<Snack> getAllSnack();
    
    public Snack chargeSnack(Snack snack);
    
    public Snack getSnackById(int id);
    
    public Snack updateSnack(Snack snack);
    
    public void deleteSnack(int id);
}
