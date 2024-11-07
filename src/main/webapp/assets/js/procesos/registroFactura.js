function CargarModalCatalogo() {
    $("#modalProductos").modal("show");
    fnDestruirTabla('#resultadoCatalogo');
    $('#resultadoCatalogo tbody').empty();
    fnCrearDataTable('#resultadoCatalogo');
}

function CargarModalCliente() {
    $("#modalClientes").modal("show");
    fnDestruirTabla('#resultadoCliente');
    $('#resultadoCliente tbody').empty();
    fnCrearDataTable('#resultadoCliente');
}

function GuardarFactura() {
    var idCliente = $("#idCliente").val();
    var idVendedor = $("#idVendedor").val();
    var idSerie = $("#idSerie").val();
    var idMoneda = $("#idMoneda").val();

    var fechaVenc = $("#fechaVenc").val();
    var tipoCambioContable = $("#tipoCambioContable").val();
    var fecha = $("#fecha").val();

    var glucosa = $("#glucosa").val();

    if (idCliente === "") {
        fnToast("info", "Debe seleccionar un cliente");
        return;
    }

    if (idSerie === "") {
        fnToast("info", "Debe seleccionar una serie");
        return;
    }
    if (idMoneda === "") {
        fnToast("info", "Debe seleccionar una moneda");
        return;
    }

    if (fechaVenc === "") {
        fnToast("info", "Debe ingresar una fecha de vencimiento");
        return;
    }

    if (tipoCambioContable === "") {
        fnToast("info", "Debe ingresar un tipo de cambio contable");
        return;
    }

    if (glucosa === "") {
        fnToast("info", "Debe ingresar una glucosa");
        return;
    }

    if (fecha === "") {
        fnToast("info", "Debe ingresar una fecha de factura");
        return;
    }

    if (idVendedor === "") {
        fnToast("info", "Debe seleccionar un vendedor");
        return;
    }

    var params = {
        accion: "guardarFactura",
        idCliente: idCliente,
        idSerie: idSerie,
        idMoneda: idMoneda,
        idVendedor: idVendedor,
        glucosa: glucosa,
        fechaVenc: fechaVenc,
        tipoCambioContable: tipoCambioContable,
        fecha: fecha
    };

    console.log(params);


    $.ajax({
        url: 'factura',
        type: 'GET',
        data: params,
        dataType: 'json',
        success: function (response) {
            if (response.msg === "OK") {
                fnToast("success", response.resultado);
            } else {
                fnToast("error", response.msg);
            }
        },
        error: function () {
            fnToast('error', 'Error al generar');
        }
    });
}

function CargarDetalle() {
    $('#btnGuardar').prop('disabled', true);

    $.ajax({
        url: 'factura',
        type: 'GET',
        data: {
            accion: 'listarDetalle'
        },
        dataType: 'json',
        success: function (response) {
            $('#resultadoDetalle').empty();
            var result = "";
            if (response.data.length > 0) {
                $.each(response.data, function (index, item) {
                    result += "<tr> ";
                    result += "<td>" + (index + 1) + "</td>";
                    result += "<td>" + item.cantidad + "</td>";
                    result += "<td>" + item.descripcion + "</td>";
                    result += "<td>" + item.descuento + "</td>";
                    result += "<td>" + (item.valorUnitario).toFixed(2) + "</td>";
                    result += "<td>" + (item.igvValor).toFixed(2) + "</td>";
                    result += "<td>" + (item.subTotal).toFixed(2) + "</td>";
                    result += "<td class='text-center'><button type='button'  onclick='eliminarItem(" + index + ")' class='btn btn-danger' title='Quitar item'><i class='fa fa-trash'></i></button></td>";
                    result += "</tr>";
                });

                result += "<tr> ";
                result += "<td colspan='6' class='text-rigth' style='font-weight: bold;'>Sub Total:</td>";
                result += "<td colspan='2' class='text-left' style='font-weight: bold;'>" + (response.subTotal).toFixed(2) + "</td>";
                result += "</tr>";

                result += "<tr> ";
                result += "<td colspan='6' class='text-rigth' style='font-weight: bold;'>IGV:</td>";
                result += "<td colspan='2' class='text-left' style='font-weight: bold;'>" + (response.igv).toFixed(2) + "</td>";
                result += "</tr>";

                result += "<tr> ";
                result += "<td colspan='6' class='text-rigth' style='font-weight: bold;'>Total:</td>";
                result += "<td colspan='2' class='text-left' style='font-weight: bold;'>" + (response.total).toFixed(2) + "</td>";
                result += "</tr>";

                $('#btnGuardar').prop('disabled', false);

            } else {
                result += "<tr> ";
                result += "<td colspan='8' class='text-center'>Sin resultados</td>";
                result += "</tr>";
            }

            $('#resultadoDetalle').append(result);
        },
        error: function () {
            fnToast('error', 'Error al cargar detalle');
        }
    });
}

function eliminarItem(index) {
    $.ajax({
        url: 'factura',
        type: 'POST',
        data: {
            accion: "eliminarItemDetalle",
            index: index
        },
        success: function (response) {
            if (response.msg === "OK") {
                $("#modalProductos").modal("hide");
                CargarDetalle();
            } else {
                fnToast('error', response.msg);
            }
        },
        error: function () {
            fnToast('error', 'Error al quitar item catalogo');
        }
    });
}

function BuscarCatalogo() {
    let descripcion = $('#txtDescripcionCatalogo').val();
    let opcion = $('#OpcionCatalogo').val();

    $.ajax({
        url: 'catalogo',
        type: 'GET',
        data: {
            accion: 'buscar',
            opcion: opcion,
            descripcion: descripcion
        },
        dataType: 'json',
        success: function (data) {
            fnDestruirTabla('#resultadoCatalogo');
            $('#resultadoCatalogo tbody').empty();

            if (data.length > 0) {
                var result = "";
                $.each(data, function (index, item) {
                    result += "<tr> ";
                    result += "<td>" + item.cmacCodigo + "</td>";
                    result += "<td>" + item.descripcion + "</td>";
                    result += "<td><input type='number' class='form-control' id='precio_" + item.mcanCodigo + "' name='precio' min='0'></td>";
                    result += "<td><input type='number' class='form-control' id='cantidad_" + item.mcanCodigo + "' name='cantidad' min='1'></td>";
                    result += "<td><input type='number' class='form-control' id='descuento_" + item.mcanCodigo + "' name='descuento' min='1'></td>";
                    result += "<td class='text-center'><button type='button' class='btn btn-success seleccionar-btn' data-codigo='" + item.mcanCodigo + "'><i class='fa fa-check-circle'></i></button></td>";
                    result += "</tr>";
                });

                $('#resultadoCatalogo tbody').append(result);

                $('.seleccionar-btn').on('click', function () {

                    let codigo = $(this).data('codigo');
                    let precio = $('#precio_' + codigo).val();
                    let cantidad = $('#cantidad_' + codigo).val();
                    let descuento = $('#descuento_' + codigo).val();

                    if (precio && cantidad && parseFloat(precio) > 0 && parseInt(cantidad) > 0 && parseInt(descuento) >= 0) {
                        SeleccionarItemCatalogo(codigo, precio, cantidad, descuento);
                    } else {
                        fnToast('error', 'Por favor, ingrese un precio,cantidad y descuento válidos.');
                    }
                });
            } else {
                fnToast('info', 'No se encontraron resultados para el criterio de busqueda.');
            }

            fnCrearDataTable('#resultadoCatalogo');
        },
        error: function () {
            fnToast('error', 'Error al realizar la búsqueda');
        }
    });
}


function BuscarCliente() {
    let descripcion = $('#txtDescripcionCliente').val();
    let opcion = $('#OpcionCliente').val();

    $.ajax({
        url: 'cliente',
        type: 'GET',
        data: {
            accion: 'buscar',
            opcion: opcion,
            descripcion: descripcion
        },
        dataType: 'json',
        success: function (data) {
            fnDestruirTabla('#resultadoCliente');
            $('#resultadoCliente tbody').empty();

            if (data.length > 0) {
                var result = "";
                $.each(data, function (index, item) {
                    result += "<tr>";
                    result += "<td>" + item.ruc + "</td>";
                    result += "<td>" + item.razonSocial + "</td>";
                    result += "<td class='text-center' style='width: 5px;'><button type='button' onclick=\"SeleccionarItemCliente('" + item.clinCodigo + "','" + item.ruc + "', '" + item.razonSocial + "', '" + item.direccion + "')\" class='btn btn-success'><i class='fa fa-check-circle'></i></button></td>";
                    result += "</tr>";
                });
                $('#resultadoCliente tbody').append(result);

            } else {
                fnToast('info', 'No se encontraron resultados para el criterio de busqueda.');
            }

            fnCrearDataTable('#resultadoCliente');
        },
        error: function () {
            fnToast('error', 'Error al realizar la búsqueda');
        }
    });
}

function SeleccionarItemCliente(id, ruc, razonSocial, direccion) {
    $("#lblRuc").val(ruc);
    $("#lbRazonSocial").val(razonSocial);
    $("#lbDireccion").val(direccion);
    $("#idCliente").val(id);
    $("#modalClientes").modal("hide");
}

function SeleccionarItemCatalogo(codigo, precio, cantidad, descuento) {
    $.ajax({
        url: 'factura',
        type: 'POST',
        data: {
            accion: "agregarItemDetalle",
            id: codigo,
            precio: precio,
            cantidad: cantidad,
            descuento: descuento
        },
        success: function (response) {
            if (response.msg === "OK") {
                $("#modalProductos").modal("hide");
                CargarDetalle();
            } else {
                fnToast('error', response.msg);
            }
        },
        error: function () {
            fnToast('error', 'Error al seleccionar item catalogo');
        }
    });
}

CargarDetalle();