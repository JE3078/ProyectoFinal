async function registrarEstudiante() {
    let datos = {};
    datos.nombreCompleto = document.getElementById('txtNombre').value.trim();
    datos.correo = document.getElementById('txtCorreo').value.trim();
    datos.telefono = document.getElementById('txtTelefono').value.trim();
    let idiomaSeleccionado = document.getElementById('cboIdioma').value;

    if (!datos.nombreCompleto || !datos.correo || !datos.telefono || idiomaSeleccionado === "") {
        alert("Todos los campos son obligatorios.");
        return;
    }

    if (!/^[^@\s]+@[^@\s]+\.[^@\s]+$/.test(datos.correo)) {
        alert("El correo ingresado no tiene un formato válido.");
        return;
    }

    // Validar teléfono (10 dígitos)
    if (!/^\d{10}$/.test(datos.telefono)) {
        alert("El teléfono debe tener exactamente 10 dígitos numéricos.");
        return;
    }

    // Convertir idioma a ID
    if (idiomaSeleccionado === "español") {
        datos.idIdioma = 1;
    } else if (idiomaSeleccionado === "inglés") {
        datos.idIdioma = 2;
    } else if (idiomaSeleccionado === "francés") {
        datos.idIdioma = 3;
    } else {
        alert("Idioma no válido.");
        return;
    }

    // Enviar al backend
    try {
        const request = await fetch('/api/estudiantes/store', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        if (request.ok) {
            alert("Estudiante creado exitosamente.");
            window.location.href = 'estudiantes.html';
        } else {
            const errorText = await request.text();
            if (request.status === 409) {
                alert("Error: El correo ya está registrado.");
            } else if (request.status === 400) {
                alert("Error: " + errorText); // ejemplo: "Formato de correo inválido."
            } else {
                alert("Error inesperado: " + errorText);
            }
        }
    } catch (error) {
        console.error("Error al registrar:", error);
        alert("Error de conexión con el servidor.");
    }
}
