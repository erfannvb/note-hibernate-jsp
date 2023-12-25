package org.example.notehibernatejsp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.notehibernatejsp.base.repository.util.HibernateUtil;
import org.example.notehibernatejsp.entity.Note;
import org.example.notehibernatejsp.repository.NoteRepository;
import org.example.notehibernatejsp.repository.impl.NoteRepositoryImpl;
import org.example.notehibernatejsp.service.NoteService;
import org.example.notehibernatejsp.service.impl.NoteServiceImpl;
import org.hibernate.Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "AddNoteServlet", urlPatterns = "/addNote")
public class AddNoteServlet extends HttpServlet {

    private Session session;
    private NoteRepository noteRepository;
    private NoteService noteService;

    @Override
    public void init() throws ServletException {
        session = HibernateUtil.getSessionFactory().openSession();
        noteRepository = new NoteRepositoryImpl(session);
        noteService = new NoteServiceImpl(session, noteRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String title = req.getParameter("title");
            String content = req.getParameter("content");

            Note note = new Note(title, content, new Date());
            noteService.addNote(note);

            resp.setContentType("text/html");

            PrintWriter writer = resp.getWriter();
            writer.println("<h2>Note Saved Successfully!!!</h2>");
            writer.println("<h2><a href='allNotes.jsp'>View All Notes</a></h2>");

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
