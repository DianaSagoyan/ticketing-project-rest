package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getProjects(){
        return ResponseEntity.ok(new ResponseWrapper("Projects successfully retrieved", projectService.listAllProjects(), HttpStatus.OK));
    }

    @GetMapping("{projectcode}")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("projectcode") String code){
        return ResponseEntity.ok(new ResponseWrapper("Project successfully retrieved", projectService.getByProjectCode(code), HttpStatus.OK));
    }

    @PostMapping
    @RolesAllowed({"Admin", "Manager"})
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO project){
        projectService.save(project);
        return ResponseEntity.ok(new ResponseWrapper("Project successfully created", HttpStatus.CREATED));
    }

    @PutMapping
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO project){
        projectService.update(project);
        return ResponseEntity.ok(new ResponseWrapper("Project successfully updated", HttpStatus.OK));
    }

    @DeleteMapping("{projectcode}")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectcode") String code){
        projectService.delete(code);
        return ResponseEntity.ok(new ResponseWrapper("Project successfully deleted", HttpStatus.OK));
    }

    @GetMapping("/manager/project-status")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> getProjectByManager(){
        return ResponseEntity.ok(new ResponseWrapper("Project successfully deleted", projectService.listAllProjectDetails(), HttpStatus.OK));
    }


    @PutMapping("/manager/complete/{projectcode}")
    @RolesAllowed("Manager")
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectcode") String code){
        projectService.complete(code);
        return ResponseEntity.ok(new ResponseWrapper("Project successfully deleted",  HttpStatus.OK));
    }
}
