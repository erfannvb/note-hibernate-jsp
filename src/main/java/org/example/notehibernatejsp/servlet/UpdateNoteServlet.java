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
import java.util.Date;
import java.util.Optional;

@WebServlet(name = "UpdateNoteServlet", urlPatterns = "/updateNote")
public class UpdateNoteServlet extends HttpServlet {

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

            long id = Long.parseLong(req.getParameter("id"));
            String title = req.getParameter("title");
            String content = req.getParameter("content");

            Optional<Note> optionalNote = noteService.findById(id);

            if (optionalNote.isPresent()) {
                Note currentNote = optionalNote.get();

                currentNote.setTitle(title);
                currentNote.setContent(content);
                currentNote.setCreatedDate(new Date());

                noteService.update(currentNote);

                resp.sendRedirect("/allNotes.jsp");
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
