package com.bank.microservice.services;

import com.bank.microservice.dtos.ReporteDTO;
import com.bank.microservice.entities.Movimiento;
import com.bank.microservice.repositories.MovimientoRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteService {
    private final MovimientoRepository movimientoRepository;

    public List<ReporteDTO> generarReporteJson(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        List<Movimiento> movimientos = movimientoRepository.findByCuenta_Cliente_IdAndFechaBetween(clienteId,fechaInicio,fechaFin);

        return movimientos.stream().map(movimiento -> {
            ReporteDTO reporte = new ReporteDTO();
            reporte.setFechaMovimiento(movimiento.getFecha());
            reporte.setNombreCliente(movimiento.getCuenta().getCliente().getNombre());
            reporte.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
            reporte.setTipoCuenta(movimiento.getCuenta().getTipoCuenta());
            reporte.setTipoMovimiento(movimiento.getTipoMovimiento());
            reporte.setSaldoInicial(movimiento.getSaldoInicial());
            reporte.setEstado(movimiento.getCuenta().isEstado());
            reporte.setValorMovimiento(movimiento.getValor());
            reporte.setSaldoDisponible(movimiento.getSaldoDisponible());
            return reporte;
        }).collect(Collectors.toList());
    }

    public ResponseEntity<byte[]> generarReportePdf(Long clienteId, LocalDateTime inicio, LocalDateTime fin) {
        List<Movimiento> movimientos = movimientoRepository.findByCuenta_Cliente_IdAndFechaBetween(clienteId, inicio, fin);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // ✅ Título del Reporte
        Paragraph titulo = new Paragraph("Reporte de Movimientos Bancarios")
                .setTextAlignment(TextAlignment.CENTER)
                .setBold()
                .setFontSize(18);
        document.add(titulo);
        document.add(new Paragraph(" ")); // Espacio en blanco


        // ✅ Tabla de Datos
        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 3, 3, 2, 2, 2, 2, 2}))
                .useAllAvailableWidth();


        // ✅ Encabezados de la Tabla
        String[] headers = {"Fecha", "Cliente", "Número de Cuenta", "Tipo de Cuenta", "Saldo Inicial", "Estado", "Valor Movimiento", "Saldo Disponible"};
        for (String header : headers) {
            table.addHeaderCell(new Cell().add(new Paragraph(header).setBold()));
        }


        // ✅ Relleno de la tabla con los movimientos
        for (Movimiento mov : movimientos) {
            BigDecimal valorMovimiento = mov.getValor();
            table.addCell(mov.getFecha().toString());
            table.addCell(mov.getCuenta().getCliente().getNombre());
            table.addCell(mov.getCuenta().getNumeroCuenta());
            table.addCell(String.valueOf(mov.getCuenta().getTipoCuenta()));
            table.addCell(String.format("%.2f", mov.getSaldoInicial()));
            table.addCell(mov.getCuenta().isEstado() ? "Activo" : "Inactivo");
            table.addCell(valorMovimiento.toString());
            table.addCell(String.format("%.2f", mov.getSaldoDisponible()));
        }

        document.add(table);
        document.close();


        // ✅ Configuración de la respuesta HTTP con PDF
        HttpHeaders headersHttp = new HttpHeaders();
        headersHttp.add("Content-Disposition", "inline; filename=movimientos_reporte.pdf");


        return ResponseEntity.ok()
                .headers(headersHttp)
                .contentType(MediaType.APPLICATION_PDF)
                .body(baos.toByteArray());
    }
}

