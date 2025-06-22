package NSP_TECH.REPORTE.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import NSP_TECH.REPORTE.controller.reporteController;
import NSP_TECH.REPORTE.model.reporte;


@Component
public class reporteModelAssembler implements RepresentationModelAssembler<reporte, EntityModel<reporte>>{

    @Override
    public EntityModel<reporte> toModel(reporte r) {
        return EntityModel.of(
            r,
            linkTo(methodOn(reporteController.class).BuscarResena(r.getId_reporte())).withRel("LINKS"),
            linkTo(methodOn(reporteController.class).ListarTodo()).withRel("todos-los-reportes")
        );
    }

}
