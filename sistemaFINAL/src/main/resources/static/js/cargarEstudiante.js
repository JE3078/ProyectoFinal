document.addEventListener("DOMContentLoaded", async () => {
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
            alert("El estudiante no fue encontrado.");
            return;
        }

        const estudiante = await response.json();

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
