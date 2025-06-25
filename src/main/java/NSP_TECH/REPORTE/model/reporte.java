package NSP_TECH.REPORTE.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name="reportes")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="Todas las reportes registradas")

public class reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_REPORTE")
    @Schema(description="identificador del reporte", example="1")
    private Long id_reporte;

    @Column(name="TITULO",nullable = false,length = 100)
    @Schema(description="titulo del reporte", example="reporte #1")
    private String titulo;
    
    @Column(name="DESCRIPCION",nullable = true,length = 500)
    @Schema(description="referencia de las caracteristicas del reporte", example="Descripción del reporte #1")
    private String descripcion;

    @Column(name="FECHA_GENERACION",nullable = true)
    @Schema(description="Fecha en la que se genera el reporte", example="")
    private LocalDateTime fecha_generacion;

    @Column(name="GENERADO_POR",nullable = true,length = 100)
    @Schema(description="Persona que realiza el reporte", example="Bastian Brisso")
    private String generado_por;

    @Column(name="TIPO_REPORTE",nullable = true,length = 50)
    @Schema(description="clasificacion del reporte", example="ventas, soporte, inventario")
    private String tipo_reporte;
}
