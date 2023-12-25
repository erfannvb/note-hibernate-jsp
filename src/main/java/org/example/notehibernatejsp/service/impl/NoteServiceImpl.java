package org.example.notehibernatejsp.service.impl;

import org.example.notehibernatejsp.base.service.impl.BaseServiceImpl;
import org.example.notehibernatejsp.entity.Note;
import org.example.notehibernatejsp.repository.NoteRepository;
import org.example.notehibernatejsp.service.NoteService;
import org.hibernate.Session;

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
}
