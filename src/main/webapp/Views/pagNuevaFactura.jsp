<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factura</title>
        <jsp:include page="../Includes/css.jsp" />
    </head>
    <body>
        <jsp:include page="../Includes/navegacion.jsp" />

        <div class="container-fluid mt-2">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title blockquote">
                        <span class="badge bg-success">Registro de factura</span>
                    </h5>
                    <hr>

                    <form>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="mb-3 row">
                                    <label class="col-sm-2 col-form-label">Cliente:</label>
                                    <div class="col-sm-4">
                                        <div class="input-group mb-6">
                                            <input type="text" name="lblRuc" id="lblRuc" class="form-control" disabled="">
                                            <a href="#" class="btn btn-default" title="Buscar Cliente" onclick="CargarModalCliente()">
                                                <i class="fa fa-search"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" name="lbRazonSocial" id="lbRazonSocial"  class="form-control" disabled="">
                                    </div>

                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="mb-3 row">
                                    <label class="col-sm-2 col-form-label">Serie:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="idSerie">
                                            <option value="">::: Seleccione :::</option>
                                            <c:forEach var="item" items="${series}" >
                                                <option value="${item.serNCodigo}" 
                                                        >${item.serCCodigo} </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <label class="col-sm-2 col-form-label">Moneda:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="idMoneda">
                                            <option value="">::: Seleccione :::</option>
                                            <c:forEach var="item" items="${monedas}" >
                                                <option value="${item.monCodigo}" 
                                                        >${item.descripcion} </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <div class="mb-3 row">
                                    <label class="col-sm-2 col-form-label">Direccion:</label>
                                    <div class="col-sm-10">
                                        <div class="input-group mb-6">
                                            <input type="text" name="lbDireccion" id="lbDireccion" class="form-control" disabled="">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <div class="mb-3 row">
                                    <label class="col-sm-2 col-form-label">Fecha Venc:</label>
                                    <div class="col-sm-4">
                                        <div class="input-group mb-6">
                                            <input type="date" name="fechaVenc" id="fechaVenc" class="form-control" >
                                        </div>
                                    </div>

                                    <label class="col-sm-2 col-form-label">Tipo Cambio Contable:</label>
                                    <div class="col-sm-4">
                                        <div class="input-group mb-6">
                                            <input type="number" name="tipoCambioContable" id="tipoCambioContable" class="form-control" >
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <div class="mb-3 row">
                                    <label class="col-sm-2 col-form-label">Glucosa:</label>
                                    <div class="col-sm-10">
                                        <div class="input-group mb-6">
                                            <input type="text" name="glucosa" id="glucosa" class="form-control" >
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <div class="mb-3 row">
                                    <label class="col-sm-2 col-form-label">Fecha:</label>
                                    <div class="col-sm-4">
                                        <div class="input-group mb-6">
                                            <input type="date" name="fecha" id="fecha" class="form-control" >
                                        </div>
                                    </div>

                                    <label class="col-sm-2 col-form-label">IGV:</label>
                                    <div class="col-sm-4">
                                        <div class="input-group mb-6">
                                            <input type="text" name="igv" id="igv" class="form-control" value="18%"  disabled="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="mb-3 row">
                                    <label class="col-sm-2 col-form-label">Vendedor:</label>
                                    <div class="col-sm-5">
                                        <div class="input-group mb-6">
                                            <select class="form-control" id="idVendedor">
                                                <option value="">::: Seleccione :::</option>
                                                <c:forEach var="item" items="${vendedores}" >
                                                    <option value="${item.venCodigo}" 
                                                            >${item.apellidos} ${item.nombres} </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-sm-5 mt-1">
                                        <a href="#" class="btn btn-info" title="Buscar Productos" onclick="CargarModalCatalogo()">
                                            <i class="fa fa-plus-circle"></i> Agregar Detalle
                                        </a>

                                        <button type="button"  id="btnGuardar" class="btn btn-primary" title="Guardar" onclick="GuardarFactura()">
                                            <i class="fa fa-save"></i> Guardar
                                        </button>

                                    </div>


                                </div>
                            </div>
                        </div>

                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                    <tr> 
                                        <th style="background-color: #5272b9;color: white">ITEM</th>
                                        <th style="background-color: #5272b9;color: white">CANTIDAD</th>
                                        <th style="background-color: #5272b9;color: white">DESCRIPCIÓN</th>
                                        <th style="background-color: #5272b9;color: white">DESCUENTO</th>
                                        <th style="background-color: #5272b9;color: white">VALOR UNIT.</th>
                                        <th style="background-color: #5272b9;color: white">IGV</th>
                                        <th style="background-color: #5272b9;color: white">SUB TOTAL</th>
                                        <th style="background-color: #5272b9;color: white"></th>
                                    </tr>
                                </thead>
                                <tbody id="resultadoDetalle">
                                    <tr>
                                        <td colspan='8' class='text-center'>Sin resultados</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <div class="modal fade" id="modalProductos" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header bg-primary text-white">
                        <h1 class="modal-title fs-5">::: Agregar Detalle :::</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        <div class="d-flex align-items-center mb-3">
                            <label class="me-2 fw-bold">Buscar por producto:</label>
                            <select class="form-select me-2" style="width: 150px;" aria-label="Buscar por" id="OpcionCatalogo">
                                <option value="1">Código</option>
                                <option value="2">Descripción</option>
                            </select>

                            <input type="text" class="form-control me-2" placeholder="Ingrese búsqueda..." id="txtDescripcionCatalogo">

                            <button type="button" class="btn btn-primary" onclick="BuscarCatalogo()">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>

                        <hr />

                        <div class="table-responsive mt-4">
                            <table class="table table-bordered tabla" id="resultadoCatalogo">
                                <thead>
                                    <tr>
                                        <th style="background-color: #5272b9;color: white">Código</th>
                                        <th style="background-color: #5272b9;color: white">Producto</th>
                                        <th style="background-color: #5272b9;color: white">Precio</th>
                                        <th style="background-color: #5272b9;color: white">Cantidad</th>
                                        <th style="background-color: #5272b9;color: white">Descuento</th>
                                        <th style="background-color: #5272b9;color: white">Seleccionar</th>  
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-bs-dismiss="modal">
                            <i class="fa fa-times"></i>&nbsp; Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modalClientes" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header bg-primary text-white">
                        <h1 class="modal-title fs-5">::: Buscar Cliente :::</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        <!-- Fila de búsqueda -->
                        <div class="d-flex align-items-center mb-3">
                            <label class="me-2 fw-bold">Buscar por:</label>
                            <select class="form-select me-2" style="width: 150px;" aria-label="Buscar por" id="OpcionCliente">
                                <option value="1">RUC</option>
                                <option value="2">Razón Social</option>
                            </select>
                            <input type="text" class="form-control me-2" placeholder="Ingrese búsqueda..." id="txtDescripcionCliente">
                            <button type="button" onclick="BuscarCliente()" class="btn btn-primary">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>

                        <hr />
                        <div class="table-responsive mt-4">
                            <table class="table table-striped table-bordered text-center tabla" id="resultadoCliente" >
                                <thead>
                                    <tr>
                                        <th style="background-color: #5272b9; color: white;">RUC</th>
                                        <th style="background-color: #5272b9; color: white;">Razón Social</th>
                                        <th style="background-color: #5272b9; color: white;width: 5px;" >Seleccionar</th>
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <input type="hidden" name="idCliente" id="idCliente" value="">
                        <button type="button" class="btn btn-default" data-bs-dismiss="modal">
                            <i class="fa fa-times"></i>&nbsp; Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../Includes/js.jsp" />
        <script src="assets/js/procesos/registroFactura.js" type="text/javascript"></script>
    </body>

</html>
