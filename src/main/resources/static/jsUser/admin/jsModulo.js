var tblModulo = document.getElementById('tblModulo');

$(document).ready(function() {
	listaModulos();
});

function recargarModulos() {
	$(tblModulo).DataTable().ajax.reload();
}

function listaModulos() {
	//$ significa jquery
	if ($.fn.DataTable.isDataTable(tblModulo)) {
		$(tblModulo).DataTable().destroy();
	}

	var path = $("#path").val();

	$(tblModulo)
			.dataTable(
					{
						"language" : {
							"sProcessing" : "Procesando...",
							"sLengthMenu" : "Mostrar _MENU_ registros",
							"sZeroRecords" : "No se encontraron resultados",
							"sEmptyTable" : "Ningún dato disponible en esta tabla",
							"sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
							"sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
							"sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
							"sInfoPostFix" : "",
							"sSearch" : "Buscar:",
							"sUrl" : "",
							"sInfoThousands" : ",",
							"sLoadingRecords" : "Cargando...",
							"oPaginate" : {
								"sFirst" : "Primero",
								"sLast" : "Último",
								"sNext" : "Siguiente",
								"sPrevious" : "Anterior"
							},
							"oAria" : {
								"sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
								"sSortDescending" : ": Activar para ordenar la columna de manera descendente"
							}
						},
						"sAjaxSource" : path + "/admin/modulosJSON",
						"bServerSide" : true,
						"bProcessing" : true,
						"sDom" : "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-5'><'col-sm-7'p>>",
						"sPaginationType" : "simple_numbers",
						"bAutoWidth" : false,
						"aoColumns" : [
								{
									mData : "idmodulo",
									sWidth : "10%",
									//bsortable permite ordenar por coluumna por defecto true
									"bSortable" : false
								},
								{
									mData : "nombre",
									sWidth : "50%"
								},
								{
									mData : "estado",
									sWidth : "10%",
									"mRender" : function(data, type, row) {
										var resultStatus = $.trim(data);
										var StatusText = '';
										if (resultStatus === "1") {
											StatusText = "Activo";
										} else {
											StatusText = "Inactivo";
										}
										return StatusText;
									}
								},
								{
									mData : "idmodulo",
									sWidth : "10%",
									"bSortable" : false,
									"mRender" : function(data, type, row) {

										var editHTML = '';
										var stateHTML = '';
										var deleteHTML = '';

										editHTML += '<a href="#" class="btn btn-primary btn-xs" onclick="obtenerModulo('
												+ data
												+ ', event, this)" title="Editar"><i class="fa fa-pencil"></i></a>';
										stateHTML += '<a href="#" class="btn btn-info btn-xs" onclick="cambiarEstadoModulo('
												+ data
												+ ', event, this)" title="Cambiar estado"><i class="fa fa-refresh"></i></a>';
										deleteHTML += '<a href="#" class="btn btn-danger btn-xs" onclick="eliminarModulo('
												+ data
												+ ', event, this)" title="Eliminar"><i class="fa fa-trash-o"></i></a>';

										return editHTML + stateHTML
												+ deleteHTML;
									}
								} ]
					});
}

$('#btnAgregar').click(function(e) {
    e.preventDefault();
    
    var path = $("#path").val();

    var modalModulo = $('#modalModulo');
    modalModulo.find('form').attr('action', path + "/admin/modulo/registrar");
    modalModulo.find('.modal-header .modal-title').html($(this).attr('title'));
    
    $('#idmodulo').val('0');
    $('#estado').val('0');

    modalModulo.modal('show');
});

$('input:radio').change(function() {
	if ($("#activo").prop("checked")) {
		$('#estado').val('1');
		$('#activo').parent().addClass('btn-primary');
		$('#inactivo').parent().removeClass('btn-primary');
	} else {
		$('#estado').val('0');
		$('#inactivo').parent().addClass('btn-primary');
		$('#activo').parent().removeClass('btn-primary');
	}
});