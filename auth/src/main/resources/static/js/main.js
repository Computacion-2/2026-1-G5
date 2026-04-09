// ==================== FUNCIONES AUXILIARES ====================

/**
 * Cierra modal para agregar roles
 */
function closeAddRoleModal() {
    const modal = document.getElementById('roleModal');
    if (modal) {
        modal.style.display = 'none';
    }
}

/**
 * Abre modal para agregar roles
 */
function openAddRoleModal(userId, username) {
    const modal = document.getElementById('roleModal');
    if (modal) {
        modal.style.display = 'block';
        const userInput = document.getElementById('userId');
        const form = document.getElementById('roleForm');
        
        if (userInput && form) {
            userInput.value = userId;
            form.action = '/users/' + userId + '/add-role';
            
            const title = modal.querySelector('h2');
            if (title) {
                title.textContent = 'Agregar Rol a ' + username;
            }
        }
    }
}

/**
 * Cierra modal cuando se hace click fuera de él
 */
window.onclick = function(event) {
    const modal = document.getElementById('roleModal');
    if (modal && event.target === modal) {
        modal.style.display = 'none';
    }
}

/**
 * Confirma eliminación de usuario
 */
function confirmDelete(itemName) {
    return confirm('¿Estás seguro de que deseas eliminar ' + itemName + '?');
}

/**
 * Muestra mensaje de éxito
 */
function showSuccessMessage(message) {
    const alertDiv = document.createElement('div');
    alertDiv.className = 'alert alert-success';
    alertDiv.innerHTML = '<span>' + message + '</span>';
    
    const container = document.querySelector('.container');
    if (container) {
        container.insertBefore(alertDiv, container.firstChild);
        
        setTimeout(() => {
            alertDiv.remove();
        }, 5000);
    }
}

/**
 * Muestra mensaje de error
 */
function showErrorMessage(message) {
    const alertDiv = document.createElement('div');
    alertDiv.className = 'alert alert-danger';
    alertDiv.innerHTML = '<span>' + message + '</span>';
    
    const container = document.querySelector('.container');
    if (container) {
        container.insertBefore(alertDiv, container.firstChild);
        
        setTimeout(() => {
            alertDiv.remove();
        }, 5000);
    }
}

/**
 * Previene envío de formulario duplicado
 */
function preventDoubleSubmit(form) {
    const submitButton = form.querySelector('button[type="submit"]');
    if (submitButton) {
        form.addEventListener('submit', function(e) {
            submitButton.disabled = true;
            submitButton.textContent = 'Procesando...';
        });
    }
}

/**
 * Inicializa todos los formularios en la página
 */
function initializeForms() {
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        preventDoubleSubmit(form);
    });
}

/**
 * Añade animaciones suave al cargar la página
 */
function initializeAnimations() {
    const elements = document.querySelectorAll('.card, .table, .permission-card');
    elements.forEach((element, index) => {
        element.style.animation = `fadeIn 0.3s ease-in ${index * 0.1}s both`;
    });
}

/**
 * Inicializa la funcionalidad de búsqueda en tablas
 */
function initializeTableSearch() {
    const searchInput = document.getElementById('tableSearch');
    if (searchInput) {
        searchInput.addEventListener('keyup', function() {
            const filter = searchInput.value.toLowerCase();
            const table = document.querySelector('table tbody');
            const rows = table.querySelectorAll('tr');
            
            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(filter) ? '' : 'none';
            });
        });
    }
}

/**
 * Inicializa todo al cargar el DOM
 */
document.addEventListener('DOMContentLoaded', function() {
    initializeForms();
    initializeAnimations();
    initializeTableSearch();
});

// ==================== VALIDACIÓN DE FORMULARIOS ====================

/**
 * Valida un formulario de usuario
 */
function validateUserForm(form) {
    const username = form.querySelector('[name="username"]');
    const email = form.querySelector('[name="email"]');
    const password = form.querySelector('[name="password"]');
    
    if (!username || !username.value.trim()) {
        showErrorMessage('El usuario es requerido');
        return false;
    }
    
    if (!email || !email.value.trim()) {
        showErrorMessage('El email es requerido');
        return false;
    }
    
    if (!isValidEmail(email.value)) {
        showErrorMessage('El email no es válido');
        return false;
    }
    
    if (password && !password.value.trim()) {
        showErrorMessage('La contraseña es requerida');
        return false;
    }
    
    return true;
}

/**
 * Valida si un email es válido
 */
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

/**
 * Valida un formulario de rol
 */
function validateRoleForm(form) {
    const name = form.querySelector('[name="name"]');
    
    if (!name || !name.value.trim()) {
        showErrorMessage('El nombre del rol es requerido');
        return false;
    }
    
    return true;
}

// ==================== UTILIDADES ====================

/**
 * Formatea una fecha
 */
function formatDate(date) {
    const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return new Date(date).toLocaleDateString('es-ES', options);
}

/**
 * Copia texto al portapapeles
 */
function copyToClipboard(text) {
    navigator.clipboard.writeText(text).then(() => {
        showSuccessMessage('Copiado al portapapeles');
    }).catch(() => {
        showErrorMessage('Error al copiar');
    });
}

/**
 * Exporta datos a CSV
 */
function exportToCSV(filename) {
    const table = document.querySelector('table');
    if (!table) return;
    
    let csv = [];
    const rows = table.querySelectorAll('tr');
    
    rows.forEach(row => {
        const cols = row.querySelectorAll('td, th');
        const csvRow = [];
        cols.forEach(col => {
            csvRow.push('"' + col.textContent.replace(/"/g, '""') + '"');
        });
        csv.push(csvRow.join(','));
    });
    
    const csvContent = csv.join('\n');
    const blob = new Blob([csvContent], { type: 'text/csv' });
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.download = filename || 'export.csv';
    link.click();
}
