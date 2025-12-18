DROP DATABASE IF EXISTS mydailydatabase;

CREATE DATABASE mydailydatabase;

USE mydailydatabase;

/**--------------------------------------------------------**/

CREATE TABLE bancos(
  id INT NOT NULL AUTO_INCREMENT,
  nombrecorto VARCHAR(50),
  nombrelargo VARCHAR(100),
  PRIMARY KEY(id)
);


CREATE TABLE centrocosto(
  id INT NOT NULL AUTO_INCREMENT,
  nombrecorto VARCHAR(100),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE clientes(
  id INT NOT NULL AUTO_INCREMENT,
  razonsocial_id INT NOT NULL,
  tipoidentidad_id INT NOT NULL,
  nombrecliente VARCHAR(50),
  identidadcliente VARCHAR(100),
  direccioncliente VARCHAR(200),
  distritocliente VARCHAR(100),
  ciudadcliente VARCHAR(50),
  telefonocliente VARCHAR(100),
  correocliente VARCHAR(100),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE contratos(
  id INT NOT NULL AUTO_INCREMENT,
  proyectos_id INT NOT NULL,
  clientes_id INT NOT NULL,
  `tipoContrato_id` INT NOT NULL,
  `tipoMoneda_id` INT NOT NULL,
  `descripcionContrato` VARCHAR(200),
  `plazoContrato` INT,
  `tipopagoContrato` VARCHAR(20),
  `detallepagoContrato` JSON,
  exonerado DECIMAL(20,2),
  imponible DECIMAL(20,2),
  impuesto DECIMAL(20,2),
  `valorTotal` DECIMAL(20,2),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE contratosxrecursos(
  contratos_id INT NOT NULL,
  recursos_id INT NOT NULL,
  `fechaCreacion` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` TIMESTAMP
    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  estado INT
);


CREATE TABLE cuentas(
  id INT NOT NULL AUTO_INCREMENT,
  razonsocial_id INT NOT NULL,
  bancos_id INT NOT NULL,
  `tipoCuenta_id` INT NOT NULL,
  `tipoMoneda_id` INT NOT NULL,
  `numeroCuenta` VARCHAR(50),
  `numeroInterbancario` VARCHAR(50),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE descripcionegresos(
  id INT NOT NULL AUTO_INCREMENT,
  ordenes_id INT,
  proyectos_id INT NOT NULL,
  `partidaGeneral` VARCHAR(50),
  `subPartida` VARCHAR(50),
  `subDetalle` VARCHAR(50),
  `descripcionEgreso` VARCHAR(200),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE descripcioningresos(
  id INT NOT NULL AUTO_INCREMENT,
  contratos_id INT NOT NULL,
  proyectos_id INT NOT NULL,
  `partidaGeneral` VARCHAR(50),
  `subPartida` VARCHAR(50),
  `subDetalle` VARCHAR(50),
  `descripcionIngreso` VARCHAR(200),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE `descripcionRendicion`(
  id INT NOT NULL AUTO_INCREMENT,
  rendiciones_id INT NOT NULL,
  proyectos_id INT NOT NULL,
  `partidaGeneral` VARCHAR(50),
  `subPartida` VARCHAR(50),
  `subDetalle` VARCHAR(50),
  `descripcionEgreso` VARCHAR(200),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE egresos(
  id INT NOT NULL AUTO_INCREMENT,
  operaciones_id INT NOT NULL,
  tipoidentidad_id INT NOT NULL,
  `tipoDocumento_id` INT NOT NULL,
  descripcionegresos_id INT NOT NULL,
  `fechaEmision` DATE,
  `periodoEmision` VARCHAR(10),
  `numIdentidad` VARCHAR(100),
  `numDocumento` VARCHAR(100),
  `nombreRazon` VARCHAR(200),
  `subTotal1` DECIMAL(20,2),
  `tipoCambio` DECIMAL(20,2),
  `subTotal2` DECIMAL(20,2),
  exonerado DECIMAL(20,2),
  imponible DECIMAL(20,2),
  impuesto DECIMAL(20,2),
  `valorTotal` DECIMAL(20,2),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE `egresosxRecursos`(
  egresos_id INT NOT NULL,
  recursos_id INT NOT NULL,
  `fechaCreacion` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` TIMESTAMP
    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  estado INT
);


CREATE TABLE ingresos(
  id INT NOT NULL AUTO_INCREMENT,
  operaciones_id INT NOT NULL,
  tipoidentidad_id INT NOT NULL,
  `tipoDocumento_id` INT NOT NULL,
  descripcioningresos_id INT NOT NULL,
  `fechaEmision` DATE,
  `periodoEmision` VARCHAR(10),
  `numIdentidad` VARCHAR(100),
  `numDocumento` VARCHAR(100),
  `nombreRazon` VARCHAR(200),
  `subTotal1` DECIMAL(20,2),
  `tipoCambio` DECIMAL(20,2),
  `subTotal2` DECIMAL(20,2),
  imponible DECIMAL(20,2),
  exonerado DECIMAL(20,2),
  impuesto DECIMAL(20,2),
  `valorTotal` DECIMAL(20,2),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE `ingresosxRecursos`(
  ingresos_id INT NOT NULL,
  recursos_id INT NOT NULL,
  `fechaCreacion` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` TIMESTAMP
    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  estado INT
);


CREATE TABLE movimientos(
  id INT NOT NULL AUTO_INCREMENT,
  cuentas_id INT NOT NULL,
  `tipoOperacion_id` INT NOT NULL,
  `fechaEmision` DATE,
  `fechaOperacion` DATE,
  `periodoOperacion` VARCHAR(50),
  `numeroOperacion` VARCHAR(50),
  `descripcionOperacion` VARCHAR(300),
  `beneficiarioOperacion` VARCHAR(300),
  `glosaOperacion` VARCHAR(300),
  `montoOperacion1` DECIMAL(20,2),
  `tipoCambio` DECIMAL(20,2),
  `montoOperacion2` DECIMAL(20,2),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE `movimientosxEgresos`(
  movimientos_id INT NOT NULL,
  egresos_id INT NOT NULL,
  `fechaCreacion` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` TIMESTAMP
    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  estado INT
);


CREATE TABLE `movimientosxIngresos`(
  movimientos_id INT NOT NULL,
  ingresos_id INT NOT NULL,
  `fechaCreacion` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` TIMESTAMP
    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  estado INT
);


CREATE TABLE `movimientosxRecursos`(
  movimientos_id INT NOT NULL,
  recursos_id INT NOT NULL,
  `fechaCreacion` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` TIMESTAMP
    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  estado INT
);


CREATE TABLE ordenes(
  id INT NOT NULL AUTO_INCREMENT,
  proyectos_id INT NOT NULL,
  proveedores_id INT NOT NULL,
  `tipoOrden_id` INT NOT NULL,
  `tipoMoneda_id` INT NOT NULL,
  `fechaOrden` DATE,
  `descripcionOrden` VARCHAR(200),
  `plazoOrden` INT,
  `formapagoOrden` VARCHAR(50),
  `detallePartidas` JSON,
  `detallepagoOrden` JSON,
  `detallegeneralOrden` JSON,
  `detalleespecifOrden` JSON,
  `subTotal1` DECIMAL(20,2),
  `tipoCambio` DECIMAL(20,2),
  `subTotal2` DECIMAL(20,2),
  exonerado DECIMAL(20,2),
  imponible DECIMAL(20,2),
  impuesto DECIMAL(20,2),
  `valorTotal` DECIMAL(20,2),
  `valorLetras` VARCHAR(500),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE `ordenesxRecursos`(
  ordenes_id INT NOT NULL,
  recursos_id INT NOT NULL,
  `fechaCreacion` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` TIMESTAMP
    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  estado TIMESTAMP
);


CREATE TABLE `partidaGeneral`(
  id INT NOT NULL AUTO_INCREMENT,
  centrocosto_id INT NOT NULL,
  `nombreCorto` VARCHAR(100),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE proveedores(
  id INT NOT NULL AUTO_INCREMENT,
  tipoidentidad_id INT NOT NULL,
  `nombreProveedor` VARCHAR(50),
  `identidadProveedor` VARCHAR(100),
  `direccionProveedor` VARCHAR(200),
  `distritoProveedor` VARCHAR(100),
  `ciudadProveedor` VARCHAR(50),
  `bancoProveedor` VARCHAR(50),
  `cuentaProveedor` VARCHAR(100),
  `cciProveedor` VARCHAR(100),
  `telefonoProveedor` VARCHAR(100),
  `correoProveedor` VARCHAR(100),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE proyectos(
  id INT NOT NULL AUTO_INCREMENT,
  razonsocial_id INT NOT NULL,
  `nombreCorto` VARCHAR(50),
  `nombreLargo` VARCHAR(100),
  `plazoProyecto` INT,
  `montoProyecto` DECIMAL(20,2),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE razonsocial(
  id INT NOT NULL AUTO_INCREMENT,
  tipoidentidad_id INT NOT NULL,
  nombrecorto VARCHAR(50),
  nombrelargo VARCHAR(100),
  numidentidad VARCHAR(50),
  estado INT,
  PRIMARY KEY(id)
);


INSERT INTO razonsocial
    (nombrecorto, nombrelargo, numidentidad, tipoidentidad_id, estado)
VALUES
    ('HADASA', 'CREACIONES HADASA S.A.C.', '20510862440', 4, 1),
    ('CESIA','INVERSIONES CESIA S.A.C.', '20522872602', 4,1),
    ('EYR', 'E&R EDIFICACIONES S.A.C.','20603430591', 4, 1),
    ('AZUR', 'DESARROLLO INMOBILIARIO AZUR S.A.C.', '20603430612', 4, 1);

CREATE TABLE recursos(
  id INT NOT NULL AUTO_INCREMENT,
  razonsocial_id INT NOT NULL,
  `tablaRecurso` VARCHAR(100) NOT NULL,
  `periodoRecurso` VARCHAR(50),
  `nombreRecurso` VARCHAR(300),
  `direccionRecurso` VARCHAR(500),
  `fechaCreacion` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` TIMESTAMP
    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE rendiciones(
  id INT NOT NULL AUTO_INCREMENT,
  `fechaRendicion` DATE,
  `periodoRendicion` DATE,
  `beneficiarioRendicion` VARCHAR(100),
  `glosaRendicion` VARCHAR(100),
  `montoRendicion` DECIMAL(20,2),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE `rendicionesDetalle`(
  `descripcionRendicion_id` INT NOT NULL,
  `fechaDetalle` DATE,
  `periodoPeriodo` VARCHAR(10),
  `numIdentidad` VARCHAR(100),
  `numDocumento` VARCHAR(100),
  `nombreRazon` VARCHAR(100),
  `subTotal1` DECIMAL(20,2),
  `tipoCambio` DECIMAL(20,2),
  `subTotal2` DECIMAL(20,2),
  impuesto DECIMAL(20,2),
  exonerado DECIMAL(20,2),
  `subTotal3` DECIMAL(20,2),
  estado INT
);


CREATE TABLE rolesxusuarios(
  id INT NOT NULL AUTO_INCREMENT,
  usuarios_id INT NOT NULL,
  tiporol_id INT NOT NULL,
  fechacreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fechamodificacion TIMESTAMP
    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  estado INT,
  PRIMARY KEY(id)
);


INSERT INTO rolesxusuarios
    (usuarios_id, tiporol_id, estado)
VALUES
    (1, 1, 1),
    (1, 2, 1);

CREATE TABLE razonxusuarios(
  id INT NOT NULL AUTO_INCREMENT,
  usuarios_id INT NOT NULL,
  razonsocial_id INT NOT NULL,
  fechacreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fechamodificacion TIMESTAMP
    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  estado INT,
  PRIMARY KEY(id)
);


INSERT INTO razonxusuarios
    (usuarios_id, razonsocial_id, estado)
VALUES
    (1, 1, 1),
    (1, 2, 1),
    (1, 3, 1),
    (1, 4, 1);

CREATE TABLE `subDetalle`(
  id INT NOT NULL AUTO_INCREMENT,
  `subPartida_id` INT NOT NULL,
  `nombreCorto` VARCHAR(100),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE `subPartida`(
  id INT NOT NULL AUTO_INCREMENT,
  `partidaGeneral_id` INT NOT NULL,
  `nombreCorto` VARCHAR(100),
  estado INT,
  PRIMARY KEY(id)
);


CREATE TABLE `tipoContrato`(
  id INT NOT NULL AUTO_INCREMENT,
  `nombreCorto` VARCHAR(50),
  `nombreLargo` VARCHAR(100),
  PRIMARY KEY(id)
);


CREATE TABLE `tipoCuenta`(
  id INT NOT NULL AUTO_INCREMENT,
  `nombreCorto` VARCHAR(50),
  `nombreLargo` VARCHAR(100),
  PRIMARY KEY(id)
);


CREATE TABLE `tipoDocumento`(
  id INT NOT NULL AUTO_INCREMENT,
  `nombreCodigo` VARCHAR(10),
  `nombreCorto` VARCHAR(50),
  `nombreLargo` VARCHAR(100),
  PRIMARY KEY(id)
);


CREATE TABLE tipoidentidad(
  id INT NOT NULL AUTO_INCREMENT,
  nombrecodigo VARCHAR(10),
  nombrecorto VARCHAR(50),
  nombrelargo VARCHAR(100),
  PRIMARY KEY(id)
);


INSERT INTO tipoidentidad
    (nombreCodigo, nombreCorto, nombreLargo)
VALUES
    ('00','N/A', 'DOC. TRIB. NO DOM. SIN RUC'),
    ('01','DNI','DOC. NACIONAL DE IDENTIDAD'),
    ('04','CEX','CARNET EXTRANEJERIA'),
    ('06','RUC','REGISTRO UNICO DE CONTRIBUYENTE'),
    ('A','CDEP','CEDULA DIPLOMATICA'),
    ('B','PASS','PASAPORTE'),
    ('C','TAX','DOC. TRIB. PP. NN.'),
    ('D','TRI','DOC. TRIB. PP. JJ.'),
    ('E','TAM','TARJETA ANDINA DE MIGRACION'),
    ('F','PTP','PERMISO TEMPORAL DE PERMANENCIA');

CREATE TABLE `tipoMoneda`(
  id INT NOT NULL AUTO_INCREMENT,
  `nombreCorto` VARCHAR(50),
  `nombreLargo` VARCHAR(100),
  PRIMARY KEY(id)
);


CREATE TABLE `tipoOperacion`(
  id INT NOT NULL AUTO_INCREMENT,
  `nombreCorto` VARCHAR(50),
  `nombreLargo` VARCHAR(100),
  PRIMARY KEY(id)
);


CREATE TABLE `tipoOrden`(
  id INT NOT NULL AUTO_INCREMENT,
  `nombreCorto` VARCHAR(50),
  `nombreLargo` VARCHAR(100),
  PRIMARY KEY(id)
);


CREATE TABLE tiporol(
  id INT NOT NULL AUTO_INCREMENT,
  nombrecorto VARCHAR(50),
  nombrelargo VARCHAR(100),
  PRIMARY KEY(id)
);


INSERT INTO tiporol
    (nombrecorto, nombrelargo)
VALUES
    ('ADMIN.','ADMINISTRADOR DE SISTEMA'),
    ('G.GEN.', 'GERENCIA GENERAL'),
    ('JEFE A.', 'COORDINAR ADMINISTRATIVO'),
    ('JEFE P.', 'JEFE DE PROYECTOS'),
    ('ASIST. A.', 'ASISTENTE ADMINISTRATIVO'),
    ('ASIST. P.','ASISTENTE PROYECTO.'),
    ('ADMIN. OBRA', 'ADMINISTRADOR DE OBRA'),
    ('ALMAC', 'ALMACENERO'),
    ('REGIST.', 'REGISTRADOR'),
    ('DIGIT.','DIGITADOR');


CREATE TABLE usuarios(
  id INT NOT NULL AUTO_INCREMENT,
  tipoidentidad_id INT NOT NULL,
  nombreusuario VARCHAR(100),
  apellidousuario VARCHAR(100),
  identidadusuario VARCHAR(100),
  passwordusuario VARCHAR(200),
  celularusuario VARCHAR(12),
  estado INT,
  PRIMARY KEY(id)
);


CREATE UNIQUE INDEX usuarios_ix_1 ON usuarios(identidadusuario);


INSERT INTO usuarios
    (tipoidentidad_id, nombreusuario, apellidousuario, identidadusuario, passwordusuario, celularusuario, estado)
VALUES
    (2,'OBED', 'LANDA VENTURA', '46410175','$2a$10$aiqM9VnvyofYe2c6TevYGe/0CTlttcaOQnf5vFbK8YFBld8RG8pt.','971545272',1),
    (2,'CESIA', 'LANDA VENTURA', '46410176','$2a$10$aiqM9VnvyofYe2c6TevYGe/0CTlttcaOQnf5vFbK8YFBld8RG8pt.','971545272',1),
    (2,'RUTH', 'LANDA VENTURA', '46410177','$2a$10$aiqM9VnvyofYe2c6TevYGe/0CTlttcaOQnf5vFbK8YFBld8RG8pt.','971545272',1),
    (2,'KEYLA', 'LANDA VENTURA', '46410178','$2a$10$aiqM9VnvyofYe2c6TevYGe/0CTlttcaOQnf5vFbK8YFBld8RG8pt.','971545272',1);


ALTER TABLE cuentas
  ADD CONSTRAINT bancos_cuentas FOREIGN KEY (bancos_id) REFERENCES bancos (id)
;


ALTER TABLE `partidaGeneral`
  ADD CONSTRAINT `centroCosto_partidaGeneral`
    FOREIGN KEY (centrocosto_id) REFERENCES centrocosto (id)
;


ALTER TABLE contratos
  ADD CONSTRAINT clientes_contratos
    FOREIGN KEY (clientes_id) REFERENCES clientes (id)
;


ALTER TABLE contratosxrecursos
  ADD CONSTRAINT `contratos_contratosxRecursos`
    FOREIGN KEY (contratos_id) REFERENCES contratos (id)
;


ALTER TABLE descripcioningresos
  ADD CONSTRAINT `contratos_descripcionIngresos`
    FOREIGN KEY (contratos_id) REFERENCES contratos (id)
;


ALTER TABLE movimientos
  ADD CONSTRAINT cuentas_operaciones
    FOREIGN KEY (cuentas_id) REFERENCES cuentas (id)
;


ALTER TABLE egresos
  ADD CONSTRAINT `descripcionEgresos_egresos`
    FOREIGN KEY (descripcionegresos_id) REFERENCES descripcionegresos (id)
;


ALTER TABLE ingresos
  ADD CONSTRAINT `descripcionIngresos_ingresos`
    FOREIGN KEY (descripcioningresos_id) REFERENCES descripcioningresos (id)
;


ALTER TABLE `rendicionesDetalle`
  ADD CONSTRAINT `descripcionRendicion_rendicionesDetalle`
    FOREIGN KEY (`descripcionRendicion_id`) REFERENCES `descripcionRendicion` (id)
      ON DELETE Restrict
;


ALTER TABLE `egresosxRecursos`
  ADD CONSTRAINT `egresos_egresosxRecursos`
    FOREIGN KEY (egresos_id) REFERENCES egresos (id)
;


ALTER TABLE `movimientosxEgresos`
  ADD CONSTRAINT `egresos_movimientosxEgresos`
    FOREIGN KEY (egresos_id) REFERENCES egresos (id)
;


ALTER TABLE `ingresosxRecursos`
  ADD CONSTRAINT `ingresos_ingresosxRecursos`
    FOREIGN KEY (ingresos_id) REFERENCES ingresos (id)
;


ALTER TABLE `movimientosxIngresos`
  ADD CONSTRAINT `ingresos_movimientosxIngresos`
    FOREIGN KEY (ingresos_id) REFERENCES ingresos (id)
;


ALTER TABLE egresos
  ADD CONSTRAINT movimientos_egresos
    FOREIGN KEY (operaciones_id) REFERENCES movimientos (id)
;


ALTER TABLE ingresos
  ADD CONSTRAINT movimientos_ingresos
    FOREIGN KEY (operaciones_id) REFERENCES movimientos (id)
;


ALTER TABLE `movimientosxEgresos`
  ADD CONSTRAINT `movimientos_movimientosxEgresos`
    FOREIGN KEY (movimientos_id) REFERENCES movimientos (id)
;


ALTER TABLE `movimientosxIngresos`
  ADD CONSTRAINT `movimientos_movimientosxIngresos`
    FOREIGN KEY (movimientos_id) REFERENCES movimientos (id)
;


ALTER TABLE `movimientosxRecursos`
  ADD CONSTRAINT `movimientos_movimientosxRecursos`
    FOREIGN KEY (movimientos_id) REFERENCES movimientos (id)
;


ALTER TABLE descripcionegresos
  ADD CONSTRAINT `ordenes_descripcionEgresos`
    FOREIGN KEY (ordenes_id) REFERENCES ordenes (id)
;


ALTER TABLE `ordenesxRecursos`
  ADD CONSTRAINT `ordenes_ordenesxRecursos`
    FOREIGN KEY (ordenes_id) REFERENCES ordenes (id)
;


ALTER TABLE `subPartida`
  ADD CONSTRAINT `partidaGeneral_subPartida`
    FOREIGN KEY (`partidaGeneral_id`) REFERENCES `partidaGeneral` (id)
;


ALTER TABLE ordenes
  ADD CONSTRAINT proveedores_ordenes
    FOREIGN KEY (proveedores_id) REFERENCES proveedores (id)
;


ALTER TABLE contratos
  ADD CONSTRAINT proyectos_contratos
    FOREIGN KEY (proyectos_id) REFERENCES proyectos (id)
;


ALTER TABLE descripcionegresos
  ADD CONSTRAINT `proyectos_descripcionEgresos`
    FOREIGN KEY (proyectos_id) REFERENCES proyectos (id)
;


ALTER TABLE descripcioningresos
  ADD CONSTRAINT `proyectos_descripcionIngresos`
    FOREIGN KEY (proyectos_id) REFERENCES proyectos (id)
;


ALTER TABLE ordenes
  ADD CONSTRAINT proyectos_ordenes
    FOREIGN KEY (proyectos_id) REFERENCES proyectos (id)
;


ALTER TABLE clientes
  ADD CONSTRAINT `razonSocial_clientes`
    FOREIGN KEY (razonsocial_id) REFERENCES razonsocial (id)
;


ALTER TABLE cuentas
  ADD CONSTRAINT `razonSocial_cuentas`
    FOREIGN KEY (razonsocial_id) REFERENCES razonsocial (id)
;


ALTER TABLE proyectos
  ADD CONSTRAINT `razonSocial_proyectos`
    FOREIGN KEY (razonsocial_id) REFERENCES razonsocial (id)
;


ALTER TABLE recursos
  ADD CONSTRAINT `razonSocial_recursos`
    FOREIGN KEY (razonsocial_id) REFERENCES razonsocial (id)
;


ALTER TABLE contratosxrecursos
  ADD CONSTRAINT `recursos_contratosxRecursos`
    FOREIGN KEY (recursos_id) REFERENCES recursos (id)
;


ALTER TABLE `egresosxRecursos`
  ADD CONSTRAINT `recursos_egresosxRecursos`
    FOREIGN KEY (recursos_id) REFERENCES recursos (id)
;


ALTER TABLE `ingresosxRecursos`
  ADD CONSTRAINT `recursos_ingresosxRecursos`
    FOREIGN KEY (recursos_id) REFERENCES recursos (id)
;


ALTER TABLE `movimientosxRecursos`
  ADD CONSTRAINT `recursos_movimientosxRecursos`
    FOREIGN KEY (recursos_id) REFERENCES recursos (id)
;


ALTER TABLE `ordenesxRecursos`
  ADD CONSTRAINT `recursos_ordenesxRecursos`
    FOREIGN KEY (recursos_id) REFERENCES recursos (id)
;


ALTER TABLE `descripcionRendicion`
  ADD CONSTRAINT `rendiciones_descripcionRendicion`
    FOREIGN KEY (rendiciones_id) REFERENCES rendiciones (id) ON DELETE Restrict
;


ALTER TABLE `subDetalle`
  ADD CONSTRAINT `subPartida_subPartida1`
    FOREIGN KEY (`subPartida_id`) REFERENCES `subPartida` (id)
;


ALTER TABLE contratos
  ADD CONSTRAINT `tipoContrato_contratos`
    FOREIGN KEY (`tipoContrato_id`) REFERENCES `tipoContrato` (id)
;


ALTER TABLE cuentas
  ADD CONSTRAINT `tipoCuenta_cuentas`
    FOREIGN KEY (`tipoCuenta_id`) REFERENCES `tipoCuenta` (id)
;


ALTER TABLE egresos
  ADD CONSTRAINT `tipoDocumento_egresos`
    FOREIGN KEY (`tipoDocumento_id`) REFERENCES `tipoDocumento` (id)
;


ALTER TABLE ingresos
  ADD CONSTRAINT `tipoDocumento_ingresos`
    FOREIGN KEY (`tipoDocumento_id`) REFERENCES `tipoDocumento` (id)
;


ALTER TABLE egresos
  ADD CONSTRAINT `tipoIdentidad_Egresos`
    FOREIGN KEY (tipoidentidad_id) REFERENCES tipoidentidad (id)
;


ALTER TABLE clientes
  ADD CONSTRAINT `tipoIdentidad_clientes`
    FOREIGN KEY (tipoidentidad_id) REFERENCES tipoidentidad (id)
;


ALTER TABLE ingresos
  ADD CONSTRAINT `tipoIdentidad_ingresos`
    FOREIGN KEY (tipoidentidad_id) REFERENCES tipoidentidad (id)
;


ALTER TABLE proveedores
  ADD CONSTRAINT `tipoIdentidad_proveedores`
    FOREIGN KEY (tipoidentidad_id) REFERENCES tipoidentidad (id)
;


ALTER TABLE usuarios
  ADD CONSTRAINT `tipoIdentidad_usuarios`
    FOREIGN KEY (tipoidentidad_id) REFERENCES tipoidentidad (id)
;


ALTER TABLE contratos
  ADD CONSTRAINT `tipoMoneda_contratos`
    FOREIGN KEY (`tipoMoneda_id`) REFERENCES `tipoMoneda` (id)
;


ALTER TABLE cuentas
  ADD CONSTRAINT `tipoMoneda_cuentas`
    FOREIGN KEY (`tipoMoneda_id`) REFERENCES `tipoMoneda` (id)
;


ALTER TABLE ordenes
  ADD CONSTRAINT `tipoMoneda_ordenes`
    FOREIGN KEY (`tipoMoneda_id`) REFERENCES `tipoMoneda` (id)
;


ALTER TABLE movimientos
  ADD CONSTRAINT `tipoOperacion_operaciones`
    FOREIGN KEY (`tipoOperacion_id`) REFERENCES `tipoOperacion` (id)
;


ALTER TABLE ordenes
  ADD CONSTRAINT `tipoOrden_ordenes`
    FOREIGN KEY (`tipoOrden_id`) REFERENCES `tipoOrden` (id)
;


ALTER TABLE rolesxusuarios
  ADD CONSTRAINT `tipoRol_rolesxUsuarios`
    FOREIGN KEY (tiporol_id) REFERENCES tiporol (id)
;


ALTER TABLE rolesxusuarios
  ADD CONSTRAINT `usuarios_rolesxUsuarios`
    FOREIGN KEY (usuarios_id) REFERENCES usuarios (id)
;


ALTER TABLE razonxusuarios
  ADD CONSTRAINT `usuarios_razonxUsuarios`
    FOREIGN KEY (usuarios_id) REFERENCES usuarios (id)
;


ALTER TABLE razonxusuarios
  ADD CONSTRAINT `razonSocial_razonxUsuarios`
    FOREIGN KEY (razonsocial_id) REFERENCES razonsocial (id)
;


ALTER TABLE razonsocial
  ADD CONSTRAINT `tipoIdentidad_razonsocial`
    FOREIGN KEY (tipoidentidad_id) REFERENCES tipoidentidad (id)
;



/**--------------------------------------------------------**/

INSERT INTO bancos
    (nombreCorto, nombreLargo)
VALUES
    ('EFECT','CAJA CHICA EFECTIVO'),
    ('BCP', 'BANCO DE CREDITO DEL PERU'),
    ('INTK','INTERBANK'),
    ('BBVA', 'BANCO BBVA'),
    ('BN', 'BANCO DE LA NACION'),
    ('BBIF','BANCO INTERAMERICANO DE FINANZAS'),
    ('BP','BANCO PICHINCHA');

INSERT INTO tipoDocumento
    (nombreCodigo, nombreCorto, nombreLargo)
VALUES
    ('00','OTRO','OTRO'),
    ('01','FACT.','FACTURA'),
    ('02','RXH', 'RECIBO POR HONORARIOS'),
    ('03','BOL','BOLETA DE VENTA'),
    ('04','LIQ','LIQUIDACION DE COMPRA'),
    ('05','PAS','BOLETO DE AVION'),
    ('06','POR','CARTA PORTE AEREO'),
    ('07','NC','NOTA DE CREDITO'),
    ('08','ND','NOTA DE DEBITO'),
    ('09','GRE','GUIA DE REMISION'),
    ('10','ARR','RECIBO ARRENDAMIENTO'),
    ('11','POL','POLIZA BOLSA VALORES'),
    ('12','TIC','TICKET MAQUINA REGISTRADORA'),
    ('13','DOC', 'DOCUMENTO BANCOS'),
    ('14', 'RE','RECIBO SERV. PUBLICO');

INSERT INTO tipoidentidad
    (nombreCodigo, nombreCorto, nombreLargo)
VALUES
    ('00','N/A', 'DOC. TRIB. NO DOM. SIN RUC'),
    ('01','DNI','DOC. NACIONAL DE IDENTIDAD'),
    ('04','CEX','CARNET EXTRANEJERIA'),
    ('06','RUC','REGISTRO UNICO DE CONTRIBUYENTE'),
    ('A','CDEP','CEDULA DIPLOMATICA'),
    ('B','PASS','PASAPORTE'),
    ('C','TAX','DOC. TRIB. PP. NN.'),
    ('D','TRI','DOC. TRIB. PP. JJ.'),
    ('E','TAM','TARJETA ANDINA DE MIGRACION'),
    ('F','PTP','PERMISO TEMPORAL DE PERMANENCIA');

INSERT INTO tipoOperacion
    (nombreCorto, nombreLargo)
VALUES
    ('ING','INGRESO'),
    ('REE', 'REINTEGRO'),
    ('ABO','ABONO DE CTAS. PROPIAS'),
    ('EGR','EGRESO'),
    ('DCO','DETRACCION COMPRAS'),
    ('DVE','DETRACCION VENTAS'),
    ('COM','COMISION BANCARIA'),
    ('ITF','COMISION ITF'),
    ('CCI','COMISION TRANSF. INTERB.'),
    ('RET','RETENCION'),
    ('DEV','DEVOLUCION'),
    ('TRA','TRANSF. A CTAS. PROPIAS');

INSERT INTO tipoCuenta
    (nombreCorto, nombreLargo)
VALUES
    ('CRR','CORRIENTE'),
    ('AHO','AHORROS'),
    ('DET','DETRACCION'),
    ('EFC','EFECTIVO'),
    ('GAR','GARANTIA'),
    ('DPZ','DEPOSITO A PLAZO');

INSERT INTO tipoMoneda
    (nombreCorto, nombreLargo)
VALUES
    ('PEN','SOLES'),
    ('USD','DOLARES');

INSERT INTO tipoContrato
    (nombreCorto, nombreLargo)
VALUES
    ('OBRA','CONTRATO DE EJECUCION DE OBRA'),
    ('ADICIONAL', 'ADENDA POR ADICIONAL DE OBRA'),
    ('DEDUCTIVO', 'ADENDA POR DEDUCTIVO DE OBRA'),
    ('SERVICIO','CONTRATO DE PRESTACION SERVICIOS'),
    ('CONSULTORIA', 'CONSULTORIA DISEÑO EN INGENIERIA'),
    ('FASTTRACK', 'CONTRATO FASTTRACK DISEÑO Y EJECUCION DE OBRA'),
    ('COMPRAVENTA', 'CONTRATO COMPRAVENTA BIEN FUTURO O TERMINADO'),
    ('ADENDACV','ADENDA A LA COMPRAVENTA BIEN FUTURO O TERMINADO');

INSERT INTO tipoOrden
    (nombreCorto, nombreLargo)
VALUES
    ('O/C','ORDEN DE COMPRA '),
    ('O/S', 'ORDEN DE SERVICIO'),
    ('S/MO', 'SUBCONTRATO M.O.'),
    ('S/TC', 'SUBCONTRATO TODO COSTO'),
    ('S/SA', 'SUBCONTRATO SUMA ALZADA'),
    ('0/T','ORDEN DE TRABAJO'),
    ('O/A', 'ORDEN DE ARRENDAMIENTO'),
    ('PPTO.','PRESUPUESTO DE CONTROL');



INSERT INTO cuentas
    (razonSocial_id, bancos_id, tipoCuenta_id, tipoMoneda_id, numeroCuenta, numeroInterbancario, estado)
VALUES
    (1,1,4,1,'E0000000','',1),
    (1,2,1,1,'1912129217041','00219100212921704158',1),
    (1,2,1,2,'1912126705177','00219100212670517755',1),
    (1,5,3,1,'00019048659','',1),
    (1,6,1,1,'7000684968','',1),
    (1,6,1,2,'7000705299','',1),
    (2,1,4,1,'E0000000','',1),
    (2,2,1,1,'1912424151073','00219100242415107354',1),
    (2,2,1,2,'1912342079172','00219100234207917257',1),
    (2,5,3,1,'00019087050','',1),
    (2,6,1,1,'7000703393','',1),
    (2,6,5,1,'7900065715','',1),
    (3,1,4,1,'E0000000','',1),
    (3,1,2,1,'19191107626003','00219119110762600350',1),
    (3,2,1,1,'1939845021055','00219300984502105518',1),
    (3,2,1,2,'19107411884150','00219100074118841553',1),
    (3,5,3,1,'00019108554','',1);

INSERT INTO clientes
    (razonSocial_id, tipoIdentidad_id, nombreCliente, identidadCliente, direccionCliente, distritoCliente, ciudadCliente, telefonoCliente, correoCliente, estado)
VALUES
    (1,2, 'OBED ISAI LANDA VENTURA', '46410175','JR. MARIANO PACHECO N 986','LA VICTORIA','LIMA','998877445','obed@landa.ve',1),
    (1,2, 'CESIA SOFIA LANDA VENTURA', '46410175','JR. MARIANO PACHECO N 986','LA VICTORIA','LIMA','998877445','obed@landa.ve',1),
    (1,2, 'RUTH EVELYN LANDA VENTURA', '46410175','JR. MARIANO PACHECO N 986','LA VICTORIA','LIMA','998877445','obed@landa.ve',1),
    (1,2, 'KEYLA LANDA VENTURA', '46410175','JR. MARIANO PACHECO N 986','LA VICTORIA','LIMA','998877445','obed@landa.ve',1);

INSERT INTO proveedores
    (tipoIdentidad_id, nombreProveedor, identidadProveedor, direccionProveedor,
    distritoProveedor, ciudadProveedor, bancoProveedor, cuentaProveedor, cciProveedor,
    telefonoProveedor, correoProveedor, estado)
VALUES
    (2,'OBED ISAI LANDA VENTURA', '46410175','JR. MARIANO PACHECO N 933', 'LA VICTORIA','LIMA', 'BANCO DE CREDITO DEL PERU','19132625736087', '01840500440561571713', '971545988', 'obed.landa.v@gmail.com',1);


INSERT INTO proyectos
    (razonSocial_id, nombreCorto, nombreLargo, plazoProyecto, montoProyecto, estado)
VALUES
    (1,'CONTRALORIA LA LIBERTAD','EJECUCION FASTTRACK DEL PROYECTO DE LA CGR',100, 815000.20,1),
    (1,'ITP','IMPLEMENTACION DE RED DE AGUA CONTRAINCENDIO EN CITE MADERA',150,750330.21,1);

INSERT INTO contratos
    (proyectos_id, clientes_id, tipocontrato_id, tipomoneda_id, descripcioncontrato, plazocontrato, tipopagocontrato, detallepagocontrato, exonerado, imponible, impuesto, valortotal, estado)
VALUES
    (1,1,2,1,'POR LA EJECUCION DEL PROYECTO MULTIFAMILIAR LAS BRISAS DE CALIFORNIA',21,'VALORIZACIONES','[{"amount": "21", "header": "Adelanto", "numitem": "1", "description": "CUOTA INICIAL 10%"}, {"amount": "21", "header": "Val. semanal", "numitem": "2", "description": "CUOTA INICIAL 10%"}]',12.00,12.00,21.00,21.00,1);

INSERT INTO movimientos
    (id, cuentas_id, tipoOperacion_id, fechaEmision, fechaOperacion, periodoOperacion,
    numeroOperacion, descripcionOperacion, beneficiarioOperacion, glosaOperacion, montoOperacion1,
    tipoCambio, montoOperacion2, estado)
VALUES
    (1,2,1,'2025-07-11','2025-07-11',7,'00112233','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',12.00,1.00,12.00,1),
    (2,2,4,'2025-07-12','2025-07-12',7,'00112233','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',12.00,1.00,12.00,1),
    (3,2,8,'2025-07-22','2025-07-22',7,'00223344','IMPUESTO ITF','','NUEVO BANNER PARA CABALLETE RECUAY',12.00,1.00,12.00,1),
    (4,2,1,'2025-07-23','2025-07-23',7,'00112233','IMPUESTO ITF','','NUEVO BANNER PARA CABALLETE RECUAY',122.00,1.00,122.00,1),
    (5,2,4,'2025-07-17','2025-07-17',7,'11223344','IMPUESTO ITF','','NUEVO BANNER PARA CABALLETE RECUAY',45.00,1.00,45.00,1),
    (6,2,1,'2025-07-24','2025-07-24',7,'22334455','IMPUESTO ITF','','NUEVO BANNER PARA CABALLETE RECUAY',65.00,1.00,65.00,1),
    (7,2,4,'2025-07-30','2025-07-30',7,'11223344','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',12.00,1.00,12.00,1),
    (8,2,4,'2025-07-01','2025-07-01',7,'00258335','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',57.00,1.00,57.00,1),
    (9,2,4,'2025-07-01','2025-07-01',7,'00888597','PACIF SOAT CF CENTRO','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (10,2,1,'2025-07-01','2025-07-01',7,'00329032','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',1000.00,1.00,1000.00,1),
    (11,2,4,'2025-07-01','2025-07-01',7,'00719030','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (12,2,4,'2025-07-01','2025-07-01',7,'00771372','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',125.90,1.00,125.90,1),
    (13,2,4,'2025-07-01','2025-07-01',7,'00581486','TDPC000738713433','','NUEVO BANNER PARA CABALLETE RECUAY',700.00,1.00,700.00,1),
    (14,2,4,'2025-07-01','2025-07-01',7,'00747220','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',57.00,1.00,57.00,1),
    (15,2,4,'2025-07-01','2025-07-01',7,'00162832','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (16,2,4,'2025-07-01','2025-07-01',7,'00900513','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',1000.00,1.00,1000.00,1),
    (17,2,4,'2025-07-01','2025-07-01',7,'00531188','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (18,2,4,'2025-07-01','2025-07-01',7,'00347804','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',125.90,1.00,125.90,1),
    (19,2,4,'2025-07-05','2025-07-05',7,'00702302','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',700.00,1.00,700.00,1),
    (20,2,4,'2025-07-05','2025-07-05',7,'00910932','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',140.00,1.00,140.00,1),
    (21,2,4,'2025-07-05','2025-07-05',7,'00484629','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',825.00,1.00,825.00,1),
    (22,2,4,'2025-07-05','2025-07-05',7,'00898851','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',1100.00,1.00,1100.00,1),
    (23,2,1,'2025-07-05','2025-07-05',7,'00561970','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',4000.00,1.00,4000.00,1),
    (24,2,4,'2025-08-01','2025-08-01',8,'00258335','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',57.00,1.00,57.00,1),
    (25,2,4,'2025-08-01','2025-08-01',8,'00888597','PACIF SOAT CF CENTRO','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (26,2,1,'2025-08-01','2025-08-01',8,'00329032','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',1000.00,1.00,1000.00,1),
    (27,2,4,'2025-08-01','2025-08-01',8,'00719030','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (28,2,4,'2025-08-01','2025-08-01',8,'00771372','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',125.90,1.00,125.90,1),
    (29,2,4,'2025-08-01','2025-08-01',8,'00581486','TDPC000738713433','','NUEVO BANNER PARA CABALLETE RECUAY',700.00,1.00,700.00,1),
    (30,2,4,'2025-08-01','2025-08-01',8,'00747220','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',2.00,1.00,2.00,1),
    (31,2,4,'2025-08-01','2025-08-01',8,'00162832','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (32,2,4,'2025-08-01','2025-08-01',8,'00900513','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',1000.00,1.00,1000.00,1),
    (33,2,4,'2025-08-01','2025-08-01',8,'00531188','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (34,2,4,'2025-08-01','2025-08-01',8,'00347804','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',125.90,1.00,125.90,1),
    (35,2,4,'2025-08-05','2025-08-05',8,'00702302','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',700.00,1.00,700.00,1),
    (36,2,4,'2025-08-05','2025-08-05',8,'00910932','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',140.00,1.00,140.00,1),
    (37,2,4,'2025-08-05','2025-08-05',8,'00484629','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',825.00,1.00,825.00,1),
    (38,2,4,'2025-08-05','2025-08-05',8,'00898851','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',1100.00,1.00,1100.00,1),
    (39,2,1,'2025-08-05','2025-08-05',8,'00561970','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',4000.00,1.00,4000.00,1),
    (40,2,4,'2025-08-06','2025-08-06',8,'00258335','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',57.00,1.00,57.00,1),
    (41,2,4,'2025-08-06','2025-08-06',8,'00888597','PACIF SOAT CF CENTRO','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (42,2,1,'2025-08-06','2025-08-06',8,'00329032','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',1000.00,1.00,1000.00,1),
    (43,2,4,'2025-08-06','2025-08-06',8,'00719030','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (44,2,4,'2025-08-06','2025-08-06',8,'00771372','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',125.90,1.00,125.90,1),
    (45,2,4,'2025-08-06','2025-08-06',8,'00581486','TDPC000738713433','','NUEVO BANNER PARA CABALLETE RECUAY',700.00,1.00,700.00,1),
    (46,2,4,'2025-08-06','2025-08-06',8,'00747220','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',2.00,1.00,2.00,1),
    (47,2,4,'2025-08-06','2025-08-06',8,'00162832','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (48,2,4,'2025-08-06','2025-08-06',8,'00900513','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',22.00,1.00,22.00,1),
    (49,2,4,'2025-08-06','2025-08-06',8,'00531188','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',50.00,1.00,50.00,1),
    (50,2,4,'2025-08-06','2025-08-06',8,'00347804','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',125.90,1.00,125.90,1),
    (51,2,4,'2025-08-09','2025-08-09',8,'00702302','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',700.00,1.00,700.00,1),
    (52,2,4,'2025-08-09','2025-08-09',8,'00910932','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',140.00,1.00,140.00,1),
    (53,2,4,'2025-08-09','2025-08-09',8,'00484629','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',825.00,1.00,825.00,1),
    (54,2,4,'2025-08-09','2025-08-09',8,'00898851','TRAN.CEL.HK.','','NUEVO BANNER PARA CABALLETE RECUAY',1100.00,1.00,1100.00,1),
    (55,2,1,'2025-08-09','2025-08-09',8,'00561970','TRAN.CTAS.TERC.HK','','NUEVO BANNER PARA CABALLETE RECUAY',4000.00,1.00,4000.00,1);

INSERT INTO recursos
    (razonSocial_id, tablaRecurso, periodoRecurso, nombreRecurso, direccionRecurso,estado)
VALUE
    (1,'MOVIMIENTOS','JULIO','20250700001','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','JULIO','20250700002','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','JULIO','20250700003','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','JULIO','20250700004','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','JULIO','20250700005','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','JULIO','20250700006','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','JULIO','20250700007','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','JULIO','20250700008','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','JULIO','20250700009','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (2,'MOVIMIENTOS','JULIO','20250700010','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (2,'MOVIMIENTOS','JULIO','20250700011','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (2,'MOVIMIENTOS','JULIO','20250700012','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','AGOSTO','20250800001','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','AGOSTO','20250800002','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','AGOSTO','20250800003','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','AGOSTO','20250800004','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','AGOSTO','20250800005','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','AGOSTO','20250800006','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','AGOSTO','20250800007','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','AGOSTO','20250800008','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (1,'MOVIMIENTOS','AGOSTO','20250800009','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (2,'MOVIMIENTOS','AGOSTO','20250800010','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (2,'MOVIMIENTOS','AGOSTO','20250800011','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (2,'MOVIMIENTOS','AGOSTO','20250800012','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (2,'MOVIMIENTOS','AGOSTO','20250800013','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1),
    (2,'MOVIMIENTOS','AGOSTO','20250800014','C:/ASSETS/HADASA/MOVEMENTS/20250500001.pdf',1);

/**--------------------------------------------------------**/
