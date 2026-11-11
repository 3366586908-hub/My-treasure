const api = axios.create({
    baseURL: 'http://localhost:8080/api/v1',
    timeout: 10000,
    withCredentials: true
});

api.interceptors.response.use(
    response => response,
    error => {
        if (error.response && error.response.status === 401) {
            sessionStorage.removeItem('userRole');
            window.location.href = '/login.html';
        }
        return Promise.reject(error);
    }
);

function getCurrentRole() {
    return sessionStorage.getItem('userRole');
}

function renderNavigation() {
    const $navbar = $('#mainNavbar');
    if (!$navbar.length) return;
    $navbar.empty();

    $navbar.append(`
        <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="/search.html">Discover</a></li>
    `);

    const role = getCurrentRole();
    if (role) {
        let accountDropdown = `
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">My Account</a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="/user/dashboard.html">Dashboard</a></li>
                    <li><a class="dropdown-item" href="/user/my-listings.html">My Listings</a></li>
        `;
        if (role === 'retailer') {
            accountDropdown += `<li><a class="dropdown-item" href="/retailer/listings.html">Retailer Console</a></li>`;
        }
        if (role === 'admin') {
            accountDropdown += `<li><a class="dropdown-item" href="/admin/dashboard.html">Admin Panel</a></li>`;
        }
        accountDropdown += `
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="#" id="logoutBtn">Logout</a></li>
                </ul>
            </li>
        `;
        $navbar.append(accountDropdown);
        $('#logoutBtn').off('click').on('click', async () => {
            await api.post('/auth/logout');
            sessionStorage.removeItem('userRole');
            window.location.href = '/login.html';
        });
    } else {
        $navbar.append(`
            <li class="nav-item"><a class="nav-link" href="/login.html">Login</a></li>
            <li class="nav-item"><a class="nav-link" href="/register.html">Register</a></li>
        `);
    }
}

function escapeHtml(str) {
    if (!str) return '';
    return str.replace(/[&<>]/g, function(m) {
        if (m === '&') return '&amp;';
        if (m === '<') return '&lt;';
        if (m === '>') return '&gt;';
        return m;
    });
}

$(document).ready(function() {
    renderNavigation();
});