package com.micael.spring.app.controllers.moduloMateria;

import com.micael.spring.app.entities.moduloMateria.Area;
import com.micael.spring.app.entities.moduloMateria.CarreraMateria;
import com.micael.spring.app.services.moduloMateria.carreraMateriaServicios.CarreraMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/carreraMaterias")
public class CarreraMateriaController  {
    @Autowired
    CarreraMateriaService service;

    @GetMapping
    public List<CarreraMateria> list(){
        return service.findAll();
    }
}
