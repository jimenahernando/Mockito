package com.jimenuzca.mockito.ejemplos.services;

import com.jimenuzca.mockito.ejemplos.models.Examen;
import com.jimenuzca.mockito.ejemplos.repositories.IExamenRepository;
import com.jimenuzca.mockito.ejemplos.repositories.IPreguntaRepository;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements IExamenService{
    private IExamenRepository examenRepository;
    private IPreguntaRepository preguntaRepository;

    public ExamenServiceImpl(IExamenRepository examenRepository, IPreguntaRepository preguntaRepository) {
        this.examenRepository = examenRepository;
        this.preguntaRepository = preguntaRepository;
    }

    @Override
    public Optional<Examen> findExamenPorNombre(String nombre) {
//        Optional : contiene uan representacion del objeto, para evitar el null
//        Puede ser equals o contains
        return examenRepository.findAll().stream()
                .filter(e -> e.getNombre().equals(nombre)).findFirst();
    }

    @Override
    public Examen findExamenPorNombreConPreguntas(String nombre) {
       Optional<Examen> examenOptional = findExamenPorNombre(nombre);
       Examen examen = null;
       if(examenOptional.isPresent()){
           examen = examenOptional.get();
           List<String> preguntas = preguntaRepository.findpreguntasPorExamenId(examen.getId());
           examen.setPreguntas(preguntas);
       }
        return examen;
    }
}
