package com.task.note.service;

import com.task.note.domain.Note;
import org.springframework.ui.Model;

import java.util.List;

public interface NoteService {
    Note addNote(String headline, String noteText);
    void delete(long id);
    List<Note> findByHeadlineOrNoteText(String filterHeadline, String filterNoteText);
    List<Note> getAll();
    void view(List<Note> notes, Model model);
}

