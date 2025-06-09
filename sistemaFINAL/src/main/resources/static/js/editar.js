document.addEventListener("DOMContentLoaded", async () => {
    const parametro = new URLSearchParams(window.location.search);
    const id = parametro.get('id');

    if (!id) {
        alert("No se ingresó un ID de estudiante.");
        return;
    }

    try {
        const response = await fetch('/api/estudiantes/' + id, {
            method: 'GET',
            headers: { 'Accept': 'application/json' }
        });

        if (!response.ok) {
            alert("El estudiante no existe.");
            return;
        }

        const estudiante = await response.json();

        // Llenar los campos con los datos actuales
        document.getElementById('txtNombre').value = estudiante.nombreCompleto || '';
        document.getElementById('txtCorreo').value = estudiante.correo || '';
        document.getElementById('txtTelefono').value = estudiante.telefono || '';

        switch (estudiante.idIdioma) {
            case 1: document.getElementById('cboIdioma').value = "español"; break;
            case 2: document.getElementById('cboIdioma').value = "inglés"; break;
            case 3: document.getElementById('cboIdioma').value = "francés"; break;
        }

    } catch (error) {
        console.error("Error al obtener los datos:", error);
        alert("Ocurrió un error al cargar los datos del estudiante.");
    }
});

async function editarEstudiante() {
    const parametro = new URLSearchParams(window.location.search);
    const id = parametro.get('id');

    const nombre = document.getElementById('txtNombre').value.trim();
    const correo = document.getElementById('txtCorreo').value.trim();
    const telefono = document.getElementById('txtTelefono').value.trim();
    const idiomaTexto = document.getElementById('cboIdioma').value;

    if (!nombre || !correo || !telefono || !idiomaTexto) {
        alert("Todos los campos son obligatorios.");
        return;
    }

    if (!/^\d{10}$/.test(telefono)) {
        alert("El número de teléfono debe tener exactamente 10 dígitos numéricos.");
        return;
    }

    let idiomaId;
    switch (idiomaTexto) {
        case "español": idiomaId = 1; break;
        case "inglés": idiomaId = 2; break;
        case "francés": idiomaId = 3; break;
        default:
            alert("Idioma no válido.");
            return;
    }

    const datos = {
        nombreCompleto: nombre,
        correo: correo,
        telefono: telefono,
        idIdioma: idiomaId
    };

    try {
        const response = await fetch('/api/estudiantes/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        if (response.ok) {
            alert("Estudiante actualizado correctamente.");
            window.location.href = "estudiantes.html";
        } else {
            const errorText = await response.text();
            alert("Error al actualizar el estudiante: " + errorText);
        }
    } catch (error) {
        console.error("Error al actualizar:", error);
        alert("Ocurrió un error en la actualización.");
    }
}
