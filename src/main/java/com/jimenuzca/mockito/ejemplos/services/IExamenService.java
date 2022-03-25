package com.jimenuzca.mockito.ejemplos.services;

import com.jimenuzca.mockito.ejemplos.models.Examen;

import java.util.List;
import java.util.Optional;

public interface IExamenService {

    Optional<Examen> findExamenPorNombre(String nombre);

    Examen findExamenPorNombreConPreguntas(String nombre);
}
