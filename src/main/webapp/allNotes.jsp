<%@ page import="org.hibernate.Session" %>
<%@ page import="org.example.notehibernatejsp.base.repository.util.HibernateUtil" %>
<%@ page import="org.hibernate.query.Query" %>
<%@ page import="org.example.notehibernatejsp.entity.Note" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.notehibernatejsp.repository.NoteRepository" %>
<%@ page import="org.example.notehibernatejsp.repository.impl.NoteRepositoryImpl" %>
<%@ page import="org.example.notehibernatejsp.service.NoteService" %>
<%@ page import="org.example.notehibernatejsp.service.impl.NoteServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>All Notes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <jsp:include page="navbar.jsp"/>
    <br>
    <h1>All Notes</h1>

    <%
        try {

            Session s = HibernateUtil.getSessionFactory().openSession();

            NoteRepository noteRepository = new NoteRepositoryImpl(s);
            NoteService noteService = new NoteServiceImpl(s, noteRepository);

            List<Note> noteList = new ArrayList<>(noteService.findAll());
            request.setAttribute("noteList", noteList);

        } catch (Exception e) {
            e.getStackTrace();
        }
    %>

    <c:forEach items="${noteList}" var="note">
        <div class="card mt-3 mb-3">
            <img src="img/notes.png" style="max-width: 75px" class="card-img-top mx-auto mt-2" alt="Notes">
            <div class="card-body px-5">
                <h5 class="card-title">${note.title}</h5>
                <p class="card-text">${note.content}</p>
                <p style="color: blue"><b>${note.createdDate}</b></p>
                <div class="text-center">
                    <a href="editNote.jsp?id=${note.id}" class="btn btn-primary">Edit</a>
                    <a href="delete?id=${note.id}" class="btn btn-danger">Delete</a>
                </div>
            </div>
        </div>
    </c:forEach>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
