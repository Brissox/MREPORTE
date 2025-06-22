package NSP_TECH.REPORTE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import NSP_TECH.REPORTE.Assembler.reporteModelAssembler;
import NSP_TECH.REPORTE.model.reporte;
import NSP_TECH.REPORTE.services.reporteServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/Reportes")

public class reporteController {

    @Autowired
    private reporteServices reporteservices;

    @Autowired
    private reporteModelAssembler assembler;
    
       // ENDPOINT PARA BUSCAR TODAS LAS RESENAS
    @GetMapping

    @Operation(summary = "REPORTES", description = "Operacion que lista todos los reportes")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente los reportes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = reporte.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun reportes", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))


    })

    public ResponseEntity<?> ListarTodo(){
        List<reporte> reporte = reporteservices.BuscarTodoReporte();
        if (reporte.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran dato");
        } else {
            return ResponseEntity.ok(assembler.toCollectionModel(reporte));
        }
    }

    // ENDPOINT PARA BUSCAR UN REPORTE
    @GetMapping("/{ID_REPORTE}")

    @Operation(summary = "REPORTE", description = "Operacion que lista los reportes")
    @Parameters (value = {
        @Parameter (name="ID_REPORTE", description= "ID del reporte que se buscara", in = ParameterIn.PATH, required= true)

    })

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el reporte ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = reporte.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun reporte", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })

    public ResponseEntity<?> BuscarResena(@PathVariable Long ID_REPORTE){

        try {
            reporte reporteBuscado = reporteservices.BuscarUnReporte(ID_REPORTE);
            return ResponseEntity.ok(assembler.toModel(reporteBuscado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el reporte");
        }
    }

// ENDPOINT PARA REGISTRAR UN REPORTE
    @PostMapping
    @Operation(summary = "ENDPOINT QUE REGISTRA UN REPORTE", description = "ENDPOINT QUE REGISTRA UN REPORTE",requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(description="REPORTE QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = reporte.class))))
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se registro correctamente el reporte", content = @Content(mediaType = "application/json", schema = @Schema(implementation = reporte.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el reporte", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el reporte ")))
    })

    public ResponseEntity<?> GuardarReporte(@RequestBody reporte reporteGuardar){
    try {
            reporte reporteRegistrar = reporteservices.GuardarReporte(reporteGuardar);
            return ResponseEntity.ok(assembler.toModel(reporteGuardar));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar la reserva y/o comentario");
    }
    }

    

}
