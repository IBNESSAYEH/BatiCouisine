package com.BatiCouisine.controller;

import com.BatiCouisine.entities.Materiau;
import com.BatiCouisine.service.MateriauService;

public class MateriauController {
    private MateriauService materiauService;

    public MateriauController(MateriauService materiauService) {
        this.materiauService = materiauService;
    }

    public void store(Materiau materiau, int idProjt) {
        materiauService.store(materiau, idProjt);
    }

    public void findById(int id) {
        materiauService.findById(id);
    }

    public void update(int id, Materiau materiau) {
        materiauService.update(id, materiau);
    }

    public void destroy(int id) {
        materiauService.destroy(id);
    }

    
}
