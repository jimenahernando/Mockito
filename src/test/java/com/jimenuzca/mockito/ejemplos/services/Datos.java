package com.jimenuzca.mockito.ejemplos.services;

import com.jimenuzca.mockito.ejemplos.models.Examen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datos {

    public final static List<Examen> EXAMENES = Arrays.asList(new Examen (5L, "Matematicas"),
            new Examen(6L, "Lenguaje"), new Examen(7L, "Historia"));

    public final static List<String> PREGUNTAS = Arrays.asList("aritmetica", "integrales", "derivadas",
            "trigonometria", "geometria");
}
