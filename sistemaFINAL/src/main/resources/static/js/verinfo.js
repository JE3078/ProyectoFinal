// Call the dataTables jQuery plugin
$(document).ready(function() {
    verInfoEstudiante();    
});

async function verInfoEstudiante() {
    const parametro = new URLSearchParams(window.location.search);
    const id = parametro.get('id');

    if (!id) {
        alert("No se proporcionó un ID de estudiante.");
        return;
    }

    try {
        const response = await fetch('/api/estudiantes/' + id, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        });

        if (!response.ok) {
            alert("El estudiante no existe.");
            return;
        }

        const estudiante = await response.json();

        document.getElementById('btnEditarCompleto').href = 'editar.html?id=' + estudiante.idEstudiante;
        document.getElementById('btnEditarParcial').href = 'editarparcial.html?id=' + estudiante.idEstudiante;


        document.getElementById('txtNombre').value = estudiante.nombreCompleto;
        document.getElementById('txtCorreo').value = estudiante.correo;
        document.getElementById('txtTelefono').value = estudiante.telefono;

        let idioma = '';
        switch (estudiante.idIdioma) {
            case 1: idioma = 'Español'; break;
            case 2: idioma = 'Inglés'; break;
            case 3: idioma = 'Francés'; break;
        }
        document.getElementById('txtIdioma').value = idioma;

    } catch (error) {
        console.error("Error al obtener los datos del estudiante:", error);
        alert("Ocurrió un error al consultar el estudiante.");
    }
}

