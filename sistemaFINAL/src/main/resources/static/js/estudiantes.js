// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarEstudiantes();
    $('#estudiantes').DataTable({
        ordering: false,
        lengthChange: false,
        searching: false
    });});
async function cargarEstudiantes() {
    const request = await fetch('api/estudiantes',{
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const estudiantes = await request.json();


let listadoHtml = '';
for (let estudiante of estudiantes) {

    let botonEliminar = '<a href="#" onclick="eliminarEstudiante(' + estudiante.idEstudiante + ')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
    let botonEditar = '<a href="informacion.html?id=' + estudiante.idEstudiante + '" class="btn btn-info btn-circle" style="margin-right: 15px;"><i class="fas fa-info-circle"></i></a>';

        let idiomaTexto = '';
        switch (estudiante.idIdioma) {
            case 1:
                idiomaTexto = 'Español';
                break;
            case 2:
                idiomaTexto = 'Inglés';
                break;
            case 3:
                idiomaTexto = 'Francés';
                break;
        }

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

async function eliminarEstudiante(id) {
    if (!confirm('¿Seguro que desea eliminar este estudiante? - acción ireversible')) {
        return;
    }

const request = await fetch('/api/estudiantes/' + id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });

//  location.reload()


    if (request.ok) {
        const fila = document.getElementById('fila-' + id);
        if (fila) {
            fila.remove();
        }
        alert('Estudiante eliminado correctamente.');
    } else {
        alert('Error al eliminar el estudiante.');
        return;
    }
}




