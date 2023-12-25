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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeleteNoteServlet", urlPatterns = "/delete")
public class DeleteNoteServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            long id = Long.parseLong(req.getParameter("id"));

            List<Note> noteList = new ArrayList<>(noteService.findAll());
            for (Note note : noteList) {
                if (note.getId().equals(id)) {
                    noteService.remove(note);
                }
            }

            resp.sendRedirect("/allNotes.jsp");

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
