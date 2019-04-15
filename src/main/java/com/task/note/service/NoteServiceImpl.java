package com.task.note.service;

import com.task.note.domain.Note;
import com.task.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;

    @Autowired
    NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note addNote(String headline, String noteText) {
        Note note = new Note(headline, noteText);
        return noteRepository.save(note);
    }

    @Override
    public void delete(long id) {
       noteRepository.deleteById(id);
    }

    @Override
    public List<Note> findByHeadlineOrNoteText(
            String filterHeadline, String filterNoteText) {
        List<Note> notes = getAll();
        if (filterHeadline != null && !filterHeadline.isEmpty()) {
            Stream<Note> stream = notes.stream().filter(note -> note.getHeadline().contains(filterHeadline));
            notes = stream.collect(Collectors.toList());
        } else if (filterNoteText != null && !filterNoteText.isEmpty()) {
            Stream<Note> stream = notes.stream().filter(note -> note.getNoteText().contains(filterNoteText));
            notes = stream.collect(Collectors.toList());
        }else {
            notes = getAll();
        }
        return notes;
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Override
    public void view(List<Note> notes, Model model) {
        model.addAttribute("notes", notes);
    }
}
