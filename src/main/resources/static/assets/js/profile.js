const token = localStorage.getItem('jwtToken');

fetch('/user/profile', {
    method: 'GET',
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
    .then(response => response.json())
    .then(data => {
        document.getElementById('fullName').value = data.data.fullName;
        document.getElementById('email').value = data.data.email;
        document.getElementById('phoneNumber').value = data.data.phoneNumber;
    })
    .catch(error => {
        console.error('Error fetching profile data:', error);
    });
