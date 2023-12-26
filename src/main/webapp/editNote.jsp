<%@ page import="org.hibernate.Session" %>
<%@ page import="org.example.notehibernatejsp.base.repository.util.HibernateUtil" %>
<%@ page import="org.example.notehibernatejsp.entity.Note" %>
<%@ page import="org.example.notehibernatejsp.repository.NoteRepository" %>
<%@ page import="org.example.notehibernatejsp.repository.impl.NoteRepositoryImpl" %>
<%@ page import="org.example.notehibernatejsp.service.NoteService" %>
<%@ page import="org.example.notehibernatejsp.service.impl.NoteServiceImpl" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit Note</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <jsp:include page="navbar.jsp"/>
    <br>
    <h1>Edit your note.</h1>

    <%
        try {

            long id = Long.parseLong(request.getParameter("id"));

            Session s = HibernateUtil.getSessionFactory().openSession();

            NoteRepository noteRepository = new NoteRepositoryImpl(s);
            NoteService noteService = new NoteServiceImpl(s, noteRepository);

            Optional<Note> optionalNote = noteService.findById(id);
            if (optionalNote.isPresent()) {
                Note currentNote = optionalNote.get();
                request.setAttribute("currentNote", currentNote);
            } else {
                System.out.println("Not Found!");
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    %>

    <form action="updateNote?id=${currentNote.id}" method="post">
        <div class="mb-3">
            <label for="title" class="form-label">Note Title</label>
            <input type="text" class="form-control" id="title" name="title"
                   placeholder="Enter your title..."
                   value="${currentNote.title}" required>
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">Note Content</label>
            <textarea id="content" class="form-control"
                      name="content" style="height: 200px;"
                      placeholder="Enter your content..." required>${currentNote.content}</textarea>
        </div>
        <div class="container text-center">
            <button type="submit" class="btn btn-success w-50">Update</button>
        </div>
    </form>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
