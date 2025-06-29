package NSP_TECH.REPORTE.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import NSP_TECH.REPORTE.model.reporte;
import NSP_TECH.REPORTE.repository.reporteRepository;
import NSP_TECH.REPORTE.services.reporteServices;

public class reporteServiceTest {
    
    @Mock
    private reporteRepository reporterepository;
    
    @InjectMocks
    private reporteServices reporteservices;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }



    
    @Test
    public void testBuscarTodo(){
    java.util.List<reporte> lista = new  ArrayList<>();

    reporte rpt1 = new reporte();
    reporte rpt2 = new reporte();


    rpt1.setId_reporte(11L);
    rpt1.setTitulo("Ganancias");
    rpt1.setDescripcion("ganancias de la sucursal");
    rpt1.setFecha_generacion(LocalDateTime.now());
    rpt1.setGenerado_por("Bastian");
    rpt1.setTipo_reporte("%Ganancias%");

    rpt2.setId_reporte(12L);
    rpt2.setTitulo("Perdidas");
    rpt2.setDescripcion("perdidas de la sucursal");
    rpt2.setFecha_generacion(LocalDateTime.now());
    rpt2.setGenerado_por("Bastian");
    rpt2.setTipo_reporte("%Perdidas%");
    

    lista.add(rpt1);
    lista.add(rpt2);

    when(reporterepository.findAll()).thenReturn(lista);

    java.util.List<reporte> resultadoBusqueda = reporteservices.BuscarTodoReporte();

    assertEquals(2,resultadoBusqueda.size());
    verify(reporterepository, times(1)).findAll();

}

    @Test
    public void testBuscarUnReporte(){
    reporte rpt = new reporte();

    rpt.setId_reporte(11L);
    rpt.setTitulo("Ganancias");
    rpt.setDescripcion("ganancias de la sucursal");
    rpt.setFecha_generacion(LocalDateTime.now());
    rpt.setGenerado_por("Bastian");
    rpt.setTipo_reporte("%Ganancias%");

    when(reporterepository.findById(11L)).thenReturn(Optional.of(rpt));

    reporte reporteBuscado = reporteservices.BuscarUnReporte(11L);
    assertEquals(11L,reporteBuscado.getId_reporte());
    verify(reporterepository, times(1)).findById(11L);

    }



    @Test
    public void testGuardarReporte(){
        reporte r = new reporte();

        r.setId_reporte(11L);
        r.setTitulo("Ganancias");
        r.setDescripcion("ganancias de la sucursal");
        r.setFecha_generacion(LocalDateTime.now());
        r.setGenerado_por("Bastian");
        r.setTipo_reporte("%Ganancias%");

        when(reporterepository.save(any())).thenReturn(r);

        reporte reporteGuardados = reporteservices.GuardarReporte(r);
        assertEquals(11L, reporteGuardados.getId_reporte());
        verify(reporterepository, times(1)).save(r);

    }

    @Test
    public void testEditarReporte(){

        reporte repO = new reporte();
        repO.setId_reporte(11L);
        repO.setTipo_reporte("solicitud");
        repO.setDescripcion("Rancagua");

        reporte repoE = new reporte();
        repoE.setId_reporte(11L);
        repoE.setTipo_reporte("Error");
        repoE.setDescripcion("Santiago");

        when(reporterepository.save(any(reporte.class))).thenReturn(repoE);
        when(reporterepository.existsById(11L)).thenReturn(true);
        reporte resultado = reporteservices.GuardarReporte(repoE);

        assertNotNull(resultado);
        assertEquals(11L, resultado.getId_reporte());
        assertEquals("Error", resultado.getTipo_reporte());
        assertEquals("Santiago", resultado.getDescripcion());

        verify(reporterepository, times(1)).save(repoE);
    }

    @Test
    public void testEliminarReporte(){
        Long id = 11L;
        doNothing().when(reporterepository).deleteById(id);

        reporteservices.EliminarReporte(11L);

        verify(reporterepository, times(1)).deleteById(id);

    }

}

