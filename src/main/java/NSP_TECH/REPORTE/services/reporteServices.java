package NSP_TECH.REPORTE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NSP_TECH.REPORTE.model.reporte;
import NSP_TECH.REPORTE.repository.reporteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional

public class reporteServices {

    @Autowired
    private reporteRepository reporterepository;

    public List<reporte> BuscarTodoReporte(){
        return reporterepository.findAll();
    }
        public reporte BuscarUnReporte(Long id_reporte){
        return reporterepository.findById(id_reporte).get();
    }

    public reporte GuardarReporte(reporte resenas){
        return reporterepository.save(resenas);

    }



}
