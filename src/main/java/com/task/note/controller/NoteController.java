package com.task.note.controller;

import com.task.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/note")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filterHeadline,
            @RequestParam(required = false, defaultValue = "") String filterNoteText,
            Model model
    ) {
        noteService.view(noteService.findByHeadlineOrNoteText(filterHeadline, filterNoteText), model);
        return "note";
    }

    @PostMapping("/note")
    public String add(
            @RequestParam String headline,
            @RequestParam String noteText,
            Model model
    ) {
        noteService.addNote(headline, noteText);
        noteService.view(noteService.getAll(), model);
        return "note";
    }


    @GetMapping("/del/{id}")
    public String delete(
            @PathVariable("id") Long id
    ) {
        noteService.delete(id);
        return "redirect:/note";
    }
}
