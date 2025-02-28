document.getElementById("registerForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const fullName = document.getElementById("fullName").value;
    const passwordHash = document.getElementById("password").value;
    const phoneNumber = document.getElementById("phoneNumber").value;

    fetch("/user/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, passwordHash, fullName, phoneNumber})
    })
        .then(response => response.json())
        .then(data => {
            if (data.httpStatus === "OK") {
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
