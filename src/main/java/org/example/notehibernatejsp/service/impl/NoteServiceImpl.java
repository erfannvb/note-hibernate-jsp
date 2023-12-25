package org.example.notehibernatejsp.service.impl;

import org.example.notehibernatejsp.base.service.impl.BaseServiceImpl;
import org.example.notehibernatejsp.entity.Note;
import org.example.notehibernatejsp.repository.NoteRepository;
import org.example.notehibernatejsp.service.NoteService;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class NoteServiceImpl extends BaseServiceImpl<Long, Note, NoteRepository> implements NoteService {

    protected final Session session;

    public NoteServiceImpl(Session session, NoteRepository repository) {
        super(session, repository);
        this.session = session;
    }

    @Override
    public void addNote(Note note) {
        session.getTransaction().begin();
        repository.save(note);
        session.getTransaction().commit();
    }

    @Override
    public Note getNoteById(Long id) {
        String hql = "from Note where id =: id";
        Query<Note> noteQuery = session.createQuery(hql, Note.class);
        noteQuery.setParameter("id", id);
        return noteQuery.getSingleResult();
    }

    @Override
    public void deleteNoteById(Long id) {
        session.getTransaction().begin();
        Optional<Note> currentNote = repository.findById(id);
        currentNote.ifPresent(repository::remove);
        session.getTransaction().commit();
    }
}
