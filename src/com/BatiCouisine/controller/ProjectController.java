package com.BatiCouisine.controller;

import com.BatiCouisine.entities.Projet;
import com.BatiCouisine.service.ProjectService;

public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void store(Projet projet, int idClient) {
        projectService.store(projet, idClient);
    }

    public void findById(int id) {
        projectService.findById(id);
    }

    public void update(int id, Projet projet) {
        projectService.update(id, projet);
    }

    public void destroy(int id) {
        projectService.destroy(id);
    }

//    public void findAll() {
//        projectService.findAll();
//    }

}
