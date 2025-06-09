// Call the dataTables jQuery plugin
$(document).ready(function() {

   });



let listadoHtml = '';
for (let estudiante of estudiantes) {

    let botonEditar = '<a href="informacion.html?id=' + estudiante.idEstudiante + '" class="btn btn-info btn-circle" style="margin-right: 15px;"><i class="fas fa-info-circle"></i></a>';


    let estudianteHtml = '<tr id="fila-' + estudiante.idEstudiante + '"><td>' + estudiante.idEstudiante + '</td><td>' +
        estudiante.nombreCompleto + '</td><td>' +
        estudiante.correo + '</td><td>' +
        estudiante.telefono + '</td><td>' +
        idiomaTexto + '</td><td class="text-center">' +
        botonEditar +
        botonEliminar +
        '</td></tr>';

    listadoHtml += estudianteHtml;
}

document.querySelector('#estudiantes tbody').outerHTML = listadoHtml;


}

}




