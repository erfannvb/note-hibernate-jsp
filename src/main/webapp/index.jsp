<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Main Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <jsp:include page="navbar.jsp"/>
    <br>

    <div class="card">
        <img src="img/notes.png" alt="Notes" class="img-fluid mx-auto py-5" style="max-width: 200px">
        <h1 class="text-primary text-center mt-4">Start Taking your Notes</h1>
        <div class="container text-center">
            <button id="startBtn" class="btn btn-outline-primary mt-4 mb-4 w-50">Start Here</button>
        </div>
    </div>

</div>

<script>
    document.getElementById("startBtn").addEventListener("click", () => {
        window.location.href = "/addNote.jsp";
    })
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
