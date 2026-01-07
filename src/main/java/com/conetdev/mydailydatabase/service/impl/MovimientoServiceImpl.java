package com.conetdev.mydailydatabase.service.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.conetdev.mydailydatabase.mapper.MovimientoMapper;
import com.conetdev.mydailydatabase.model.Movimiento;
import com.conetdev.mydailydatabase.repository.MovimientoRepository;
import com.conetdev.mydailydatabase.request.MovimientoRequest;
import com.conetdev.mydailydatabase.response.MovimientoResponse;
import com.conetdev.mydailydatabase.service.MovimientoService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private MovimientoMapper movimientoMapper;

    private String className = MovimientoServiceImpl.class.getName();

    // Columnas esperadas en el Excel
    private static final String[] COLUMNAS_PLANTILLA = {
            "Fecha Emisión", "Fecha Operación", "Período", "Número Operación",
            "Descripción", "Beneficiario", "Glosa", "Monto 1", "Tipo Cambio", "Monto 2",
            "ID Cuenta", "ID Tipo Operación", "Estado"
    };

    @Override
    public ResponseEntity<List<MovimientoResponse>> cargar() {
        try {
            List<Movimiento> movimientos = movimientoRepository.findAll();
            List<MovimientoResponse> responses = new ArrayList<>();
            for (Movimiento m : movimientos) {
                responses.add(movimientoMapper.toMovimientoResponse(m));
            }
            Logger.getLogger(className).log(Level.INFO, "Movimientos cargados exitosamente");
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(className).log(Level.SEVERE, "Error al cargar movimientos", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> validarExcel(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                Map<String, Object> error = new HashMap<>();
                error.put("status", "ERROR");
                error.put("mensaje", "Archivo vacío");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            if (!file.getOriginalFilename().endsWith(".xlsx")) {
                Map<String, Object> error = new HashMap<>();
                error.put("status", "ERROR");
                error.put("mensaje", "Solo se aceptan archivos .xlsx");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            List<Map<String, Object>> datos = new ArrayList<>();
            List<String> errores = new ArrayList<>();

            // Parsear Excel
            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            // Validar header
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                Map<String, Object> error = new HashMap<>();
                error.put("status", "ERROR");
                error.put("mensaje", "El archivo no tiene encabezados");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            // Leer datos
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;

                try {
                    Map<String, Object> movimiento = new HashMap<>();

                    movimiento.put("fechaEmision", getCellAsDate(row.getCell(0)));
                    movimiento.put("fechaOperacion", getCellAsDate(row.getCell(1)));
                    movimiento.put("periodoOperacion", getCellAsString(row.getCell(2)));
                    movimiento.put("numeroOperacion", getCellAsString(row.getCell(3)));
                    movimiento.put("descripcionOperacion", getCellAsString(row.getCell(4)));
                    movimiento.put("beneficiarioOperacion", getCellAsString(row.getCell(5)));
                    movimiento.put("glosaOperacion", getCellAsString(row.getCell(6)));
                    movimiento.put("montoOperacion1", getCellAsDecimal(row.getCell(7)));
                    movimiento.put("tipoCambio", getCellAsDecimal(row.getCell(8)));
                    movimiento.put("montoOperacion2", getCellAsDecimal(row.getCell(9)));
                    movimiento.put("cuentasId", getCellAsLong(row.getCell(10)));
                    movimiento.put("tipoOperacionId", getCellAsLong(row.getCell(11)));
                    movimiento.put("estado", getCellAsInteger(row.getCell(12), 1));

                    // Validación básica
                    if (movimiento.get("fechaEmision") == null || movimiento.get("cuentasId") == null) {
                        errores.add("Fila " + (rowNum + 1) + ": Falta fecha emisión o ID cuenta");
                        continue;
                    }

                    datos.add(movimiento);
                } catch (Exception e) {
                    errores.add("Fila " + (rowNum + 1) + ": " + e.getMessage());
                }
            }

            workbook.close();

            Map<String, Object> response = new HashMap<>();
            if (errores.isEmpty() && datos.isEmpty()) {
                response.put("status", "ERROR");
                response.put("mensaje", "No hay datos válidos en el archivo");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            response.put("status", "OK");
            response.put("datos", datos);
            response.put("errores", errores);
            response.put("total", datos.size());

            Logger.getLogger(className).log(Level.INFO, "Excel validado: " + datos.size() + " registros");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(className).log(Level.SEVERE, "Error validando Excel", e);
            Map<String, Object> error = new HashMap<>();
            error.put("status", "ERROR");
            error.put("mensaje", "Error procesando archivo");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> guardarLote(List<Map<String, Object>> movimientos) {
        try {
            int exitosos = 0;
            List<String> errores = new ArrayList<>();

            for (int i = 0; i < movimientos.size(); i++) {
                try {
                    Map<String, Object> data = movimientos.get(i);

                    Movimiento m = new Movimiento();
                    m.setFechaEmision((LocalDate) data.get("fechaEmision"));
                    m.setFechaOperacion((LocalDate) data.get("fechaOperacion"));
                    m.setPeriodoOperacion((String) data.get("periodoOperacion"));
                    m.setNumeroOperacion((String) data.get("numeroOperacion"));
                    m.setDescripcionOperacion((String) data.get("descripcionOperacion"));
                    m.setBeneficiarioOperacion((String) data.get("beneficiarioOperacion"));
                    m.setGlosaOperacion((String) data.get("glosaOperacion"));
                    m.setMontoOperacion1((BigDecimal) data.get("montoOperacion1"));
                    m.setTipoCambio((BigDecimal) data.get("tipoCambio"));
                    m.setMontoOperacion2((BigDecimal) data.get("montoOperacion2"));
                    m.setCuentasId((Long) data.get("cuentasId"));
                    m.setTipoOperacionId((Long) data.get("tipoOperacionId"));
                    m.setEstado((Integer) data.get("estado"));

                    movimientoRepository.save(m);
                    exitosos++;
                } catch (Exception e) {
                    errores.add("Registro " + (i + 1) + ": " + e.getMessage());
                    Logger.getLogger(className).log(Level.WARNING, "Error guardando movimiento", e);
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("status", "OK");
            response.put("exitosos", exitosos);
            response.put("total", movimientos.size());
            response.put("errores", errores);

            Logger.getLogger(className).log(Level.INFO, "Guardados " + exitosos + " movimientos");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(className).log(Level.SEVERE, "Error guardando lote", e);
            Map<String, Object> error = new HashMap<>();
            error.put("status", "ERROR");
            error.put("mensaje", "Error al guardar datos");
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> descargarPlantilla() {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Movimientos");

            // Crear header
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < COLUMNAS_PLANTILLA.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(COLUMNAS_PLANTILLA[i]);
                cell.setCellStyle(getHeaderStyle(workbook));
            }

            // Ajustar ancho de columnas
            for (int i = 0; i < COLUMNAS_PLANTILLA.length; i++) {
                sheet.setColumnWidth(i, 20 * 256);
            }

            // Convertir a bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            workbook.close();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=plantilla_movimientos.xlsx");
            headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            Logger.getLogger(className).log(Level.INFO, "Plantilla descargada");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(baos.toByteArray());

        } catch (IOException e) {
            Logger.getLogger(className).log(Level.SEVERE, "Error generando plantilla", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para lectura de celdas
    private String getCellAsString(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((long) cell.getNumericCellValue());
        }
        return "";
    }

    private LocalDate getCellAsDate(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return LocalDate.parse(cell.getStringCellValue(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private BigDecimal getCellAsDecimal(Cell cell) {
        if (cell == null) return BigDecimal.ZERO;
        if (cell.getCellType() == CellType.NUMERIC) {
            return BigDecimal.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return new BigDecimal(cell.getStringCellValue());
            } catch (Exception e) {
                return BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;
    }

    private Long getCellAsLong(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return (long) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Long.parseLong(cell.getStringCellValue());
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private Integer getCellAsInteger(Cell cell, Integer defaultValue) {
        if (cell == null) return defaultValue;
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Integer.parseInt(cell.getStringCellValue());
            } catch (Exception e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    private CellStyle getHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}
