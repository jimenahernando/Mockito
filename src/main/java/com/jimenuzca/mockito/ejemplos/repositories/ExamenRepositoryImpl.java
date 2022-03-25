package com.jimenuzca.mockito.ejemplos.repositories;

import com.jimenuzca.mockito.ejemplos.models.Examen;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ExamenRepositoryImpl implements IExamenRepository{

    @Override
    public List<Examen> findAll() {
//        return Arrays.asList(new Examen (5L, "Matematicas"), new Examen(6L, "Lenguaje"),
//                new Examen(7L, "Historia"));

        //si quiero probar con una lista vacia (hacer un test) deberiamos modificarlo desde aca
        // y esto NO ES LO CORRECTO
        return Collections.emptyList();
    }
}
