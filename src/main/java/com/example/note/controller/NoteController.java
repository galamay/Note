package com.example.note.controller;


import com.example.note.domain.Note;
import com.example.note.repository.NoteRepository;
import com.example.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteService noteService;

    @GetMapping("/note")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filterHeadline,
            @RequestParam(required = false, defaultValue = "") String filterNoteText,
            Model model
    ) {
        Iterable<Note> notes = noteRepository.findAll();

        if ((filterHeadline != null && !filterHeadline.isEmpty()) ||
                (filterNoteText != null && !filterNoteText.isEmpty())) {
            notes = noteService.search(filterHeadline, filterNoteText, notes);
        } else {
            notes = noteRepository.findAll();
        }

        model.addAttribute("notes", notes);
        return "note";
    }

    @PostMapping("/note")
    public String add(
            @RequestParam String headline,
            @RequestParam String noteText, Model model
    ) {
        Note note = new Note(headline, noteText);
        noteRepository.save(note);
        Iterable<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "note";
    }

    @GetMapping("/del/{id}")
    public String delete(
            @PathVariable("id") Long id
    ) {
        noteRepository.deleteById(id);
        return "redirect:/note";
    }
}
