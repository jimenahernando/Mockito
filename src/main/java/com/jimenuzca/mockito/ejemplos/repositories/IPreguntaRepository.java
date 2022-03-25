package com.jimenuzca.mockito.ejemplos.repositories;

import java.util.List;

public interface IPreguntaRepository {

    List<String> findpreguntasPorExamenId(Long id);
}
