package org.example.notehibernatejsp.repository.impl;

import org.example.notehibernatejsp.base.repository.impl.BaseRepositoryImpl;
import org.example.notehibernatejsp.entity.Note;
import org.example.notehibernatejsp.repository.NoteRepository;
import org.hibernate.Session;

public class NoteRepositoryImpl extends BaseRepositoryImpl<Long, Note> implements NoteRepository {

    protected final Session session;

    public NoteRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Class<Note> getEntityClass() {
        return Note.class;
    }
}
