package com.BatiCouisine.controller;

import com.BatiCouisine.entities.Devis;
import com.BatiCouisine.service.DevisService;

public class DevisController {
    private DevisService devisService;

    public DevisController(DevisService devisService) {
        this.devisService = devisService;
    }

    public void store(Devis devis, int idProject) {
        devisService.store(devis, idProject);
    }

    public void findById(int id) {
        devisService.findById(id);
    }

    public void update(int id, Devis devis) {
        devisService.update(id, devis);
    }

    public void destroy(int id) {
        devisService.destroy(id);
    }

    
}
