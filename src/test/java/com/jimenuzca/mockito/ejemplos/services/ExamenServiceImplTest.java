package com.jimenuzca.mockito.ejemplos.services;

import com.jimenuzca.mockito.ejemplos.models.Examen;
import com.jimenuzca.mockito.ejemplos.repositories.ExamenRepositoryImpl;
import com.jimenuzca.mockito.ejemplos.repositories.ExamenRepositoryOtro;
import com.jimenuzca.mockito.ejemplos.repositories.IExamenRepository;
import com.jimenuzca.mockito.ejemplos.repositories.IPreguntaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ExamenServiceImplTest {

//    MOCK: no se puede hacer un mock de cualqueir método solo los publicos o default siempre que estamos dentro del mismo
//    package. No podemos hacer un mocke de un metodo privado, ni final ni estatico.
//    El metodo estatico when indica que cuando se ejecute x metodo va a devolver x datos. Detiene la invocacion real
//    del metodo que indicamos y lo simulamos con el comportamiento que nosotros queremos
//    El metodo verify nos permite verificar si efectivamente se invoco un metodo de nuestro mock.
//    Valida y confirma si se llamaron esos metodos

    IExamenRepository examenRepository;
    IExamenService service;
    IPreguntaRepository preguntaRepository;

    @BeforeEach
    void setUp() {
//        SE PREPARA EL ESCENARIO
        //USAMO LA IMPLEMENTACION EN ESTE CASO o BIEN LA INTERFAZ QUE IMPLEMENTAN
//        repository = mock(ExamenRepositoryOtro.class);
        examenRepository = mock(IExamenRepository.class);
        preguntaRepository = mock(IPreguntaRepository.class);
        service = new ExamenServiceImpl(examenRepository, preguntaRepository);
    }

    @Test
    void FindExamenPorNombre_usoImplementacion_listaConDatos(){
        //NO SE ESTÁ LLAMANDO AL METODO REAL findAll es un mock
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        Optional<Examen> examen = service.findExamenPorNombre("Matematicas");


        assertTrue(examen.isPresent());
        assertEquals(5L, examen.get().getId());
        assertEquals("Matematicas", examen.get().getNombre());
    }

    @Test
    void FindExamenPorNombre_usoImplementacion_listaVacia(){
        // Nuevo contexto de pruebas, lista vacia
        List<Examen> datos = Collections.emptyList();

        //NO SE ESTÁ LLAMANDO AL METODO REAL findAll es un mock
        when(examenRepository.findAll()).thenReturn(datos);
        Optional<Examen> examen = service.findExamenPorNombre("Matematicas");

        assertFalse(examen.isPresent());
    }

    @Test
    void testPreguntasExamen() {
//        Para cada metodo simulamos que devuelve algo
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.findpreguntasPorExamenId(7L)).thenReturn(Datos.PREGUNTAS);
        //generico para que se aplique a cualquier valor numerico de tipo long
        when(preguntaRepository.findpreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Historia");

        assertEquals(5L, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("aritmetica"));
        // no existe es false
        assertFalse(examen.getPreguntas().contains("integrales2"));
    }

    @Test
    void testPreguntasExamen_verify() {
//        Para cada metodo simulamos que devuelve algo
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.findpreguntasPorExamenId(7L)).thenReturn(Datos.PREGUNTAS);
        //generico para que se aplique a cualquier valor numerico de tipo long
        when(preguntaRepository.findpreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matematicas");

        assertEquals(5L, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("integrales"));

        //verificamos que se invoque el metodo findAll
        verify(examenRepository).findAll();
        verify(preguntaRepository).findpreguntasPorExamenId(5L);
    }

    @Test
    void testNoexisteExamenVerify() {
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.findpreguntasPorExamenId(7L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Arte");

        assertNull(examen);

        //verificamos que se invoquen los metodos
        verify(examenRepository).findAll();

//        Falla porque no existe el examen de Arte entonces este ya no lo ejecuta
//        verify(preguntaRepository).findpreguntasPorExamenId(5L);
    }
    @Test
    void testListaVaciaExamenVerify() {
        when(examenRepository.findAll()).thenReturn(Collections.emptyList());
        when(preguntaRepository.findpreguntasPorExamenId(5L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Arte");

        assertNull(examen);

        //verificamos que se invoquen los metodos
        verify(examenRepository).findAll();

//        Falla porque la lista esta vacia
//        verify(preguntaRepository).findpreguntasPorExamenId(5L);
    }

    @Test
    void testAnyLongExamenVerify() {
        when(examenRepository.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaRepository.findpreguntasPorExamenId(5L)).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matematicas");

        assertNotNull(examen);

        //verificamos que se invoquen los metodos
        verify(examenRepository).findAll();
        verify(preguntaRepository).findpreguntasPorExamenId(anyLong());
    }

}