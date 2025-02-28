document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch("/user/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    })
        .then(response => response.json())
        .then(data => {
            if (data.httpStatus === "OK") {
                localStorage.setItem("jwtToken", data.message);
                window.location.href = "/profile";
            } else {
                document.getElementById("errorMessage").innerHTML = data.message;
                document.getElementById("errorMessage").style.display = "block";
            }
        })
        .catch(error => {
            console.error("Error during login:", error);
            document.getElementById("errorMessage").innerHTML = error;
            document.getElementById("errorMessage").style.display = "block";
        });
});
