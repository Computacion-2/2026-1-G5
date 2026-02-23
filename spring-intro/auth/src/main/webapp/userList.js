
document.addEventListener("DOMContentLoaded", function () {

    const params = new URLSearchParams(window.location.search);

    if (params.get("success") === "true") {
        document.getElementById("successBox").classList.remove("error")
        document.getElementById("successBox").classList.add("message")
        document.getElementById("successBox").style.display = "block";
        window.history.replaceState({}, document.title, window.location.pathname);
    } else if (params.get("success") === "false") {
        document.getElementById("successBox").classList.remove("message")
        document.getElementById("successBox").classList.add("error")
        document.getElementById("successBox").style.display = "block";
        document.getElementById("successBox").textContent = "Error al crear usuario"
        window.history.replaceState({}, document.title, window.location.pathname);
    }else{
        document.getElementById("successBox").style.display = "none";
    }
})