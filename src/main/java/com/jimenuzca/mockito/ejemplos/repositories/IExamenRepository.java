package com.jimenuzca.mockito.ejemplos.repositories;

import com.jimenuzca.mockito.ejemplos.models.Examen;

import java.util.List;

public interface IExamenRepository {
    List<Examen> findAll();
}
