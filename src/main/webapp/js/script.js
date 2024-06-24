function mostrarDiv(id) {
    var div1 = document.getElementById('div1');
    var div2 = document.getElementById('div2');
    if (id === 'div1') {
        div1.style.display = 'block';
        div2.style.display = 'none';
        
    } else {
            div1.style.display = 'none';
            div2.style.display = 'block';
    }
}

function mostrarDiv2(id) {
    var divi1 = document.getElementById('divi1');
    var divi2 = document.getElementById('divi2');
    var divi3 = document.getElementById('divi3');

    if (id === 'divi1') {
        divi1.style.display = 'block';
        divi2.style.display = 'none';
        divi3.style.display = 'none';
        
    } else {
        if (id === 'divi2'){
            divi1.style.display = 'none';
            divi2.style.display = 'block';
            divi3.style.display = 'none';
        }else{
            divi1.style.display = 'none';
            divi2.style.display = 'none';
            divi3.style.display = 'block';

        }
           
    }
}

document.getElementById('file').addEventListener('change', function() {
    console.log('Evento de cambio de archivo detectado');
    var reader = new FileReader();

    reader.onload = function(e) {
        document.getElementById('image-preview').src = e.target.result;
    };

    reader.readAsDataURL(this.files[0]);
});

function toggleDropdown() {
    var dropdownMenu = document.getElementById("dropdownMenu");
    dropdownMenu.classList.toggle("show");
   
  }
  function toggleDropdown2() {
    var dropdownMenu2 = document.getElementById("dropdownMenu2");
    dropdownMenu2.classList.toggle("show");
  }

window.addEventListener('click', function(event) {
    var dropdownMenu = document.getElementById("dropdownMenu");
    var userPhoto = document.getElementById("userPhoto");
    
    // Verificar si el clic no ocurrió dentro del menú desplegable ni en la foto del usuario
    if (!dropdownMenu.contains(event.target) && event.target !== userPhoto) {
        // Si es así, cerrar el menú desplegable si está abierto
        if (dropdownMenu.classList.contains("show")) {
            dropdownMenu.classList.remove("show");
        }
    }

    var dropdownMenu2 = document.getElementById("dropdownMenu2");
    var btnPubli = document.getElementById("btnPubli");

    if (!dropdownMenu2.contains(event.target) && event.target !== btnPubli ) {
        // Si es así, cerrar el menú desplegable si está abierto
        if (dropdownMenu2.classList.contains("show")) {
            dropdownMenu2.classList.remove("show");
        }
    }

});


document.addEventListener("DOMContentLoaded", function() {
    const paginationContainer = document.getElementById('pagination');
    const prevButton = document.getElementById('prev');
    const nextButton = document.getElementById('next');

    let currentPage = 1;

    function showPage(page) {
        const itemsPerPage = 10; // Número de elementos por página
        const publicaciones = document.querySelectorAll('.Publicacion');
        const start = (page - 1) * itemsPerPage;
        const end = start + itemsPerPage;

        publicaciones.forEach((publicacion, index) => {
            if (index >= start && index < end) {
                publicacion.style.display = 'block';
            } else {
                publicacion.style.display = 'none';
            }
        });
    }

    function setupPagination() {
        const itemsPerPage = 10; // Número de elementos por página
        const publicaciones = document.querySelectorAll('.Publicacion');
        const pageCount = Math.ceil(publicaciones.length / itemsPerPage);

        // Eliminar números de página existentes antes de generar nuevos
        const existingPageItems = document.querySelectorAll('.page-item.page-number');
        existingPageItems.forEach(item => item.remove());

        for (let i = 1; i <= pageCount; i++) {
            const pageItem = document.createElement('li');
            pageItem.classList.add('page-item', 'page-number');
            const pageLink = document.createElement('a');
            pageLink.classList.add('page-link');
            pageLink.textContent = i;
            pageLink.href = '#';
            pageLink.addEventListener('click', function(event) {
                event.preventDefault(); // Evitar que el enlace redireccione
                currentPage = i;
                showPage(currentPage);
            });
            pageItem.appendChild(pageLink);
            paginationContainer.querySelector('.pagination').insertBefore(pageItem, nextButton);
        }
    }

    prevButton.addEventListener('click', function() {
        if (currentPage > 1) {
            currentPage--;
            showPage(currentPage);
        }
    });

    nextButton.addEventListener('click', function() {
        const itemsPerPage = 10; // Número de elementos por página
        const publicaciones = document.querySelectorAll('.Publicacion');
        const pageCount = Math.ceil(publicaciones.length / itemsPerPage);
        if (currentPage < pageCount) {
            currentPage++;
            showPage(currentPage);
        }
    });

    // Mostrar la primera página al cargar
    showPage(currentPage);
    // Configurar la paginación
    setupPagination();
});

 function obtenerIdsPublicaciones() {
            $('.Publicacion').each(function() {
                var publicacionId = $(this).data('id');
                console.log(publicacionId); // Haz algo con el ID de la publicación
            });
        }

        $(document).ready(function() {
            $('#myButton').click(obtenerIdsPublicaciones);
        });