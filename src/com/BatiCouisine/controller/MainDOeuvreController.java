package com.BatiCouisine.controller;

import com.BatiCouisine.entities.MainDoeuvre;
import com.BatiCouisine.service.MainDoeuvreService;

public class MainDOeuvreController {
    private MainDoeuvreService mainDoeuvreService;

    public MainDOeuvreController(MainDoeuvreService mainDoeuvreService) {
        this.mainDoeuvreService = mainDoeuvreService;
    }

    public void store(MainDoeuvre mainDoeuvre, int idProjt) {
        mainDoeuvreService. store(mainDoeuvre, idProjt);
    }

    public void findById(int id) {
        mainDoeuvreService.findById(id);
    }

    public void update(int id, MainDoeuvre mainDoeuvre) {
        mainDoeuvreService.update(id, mainDoeuvre);
    }

    public void destroy(int id) {
        mainDoeuvreService.destroy(id);
    }

    
}
