package com.example.note.service;

import com.example.note.domain.Note;
import com.example.note.repository.NoteRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    public Iterable<Note> search(String filter1, String filter2, Iterable<Note> iter) {

        List<Note> list = Lists.newArrayList(iter);

        if (filter1 != null && !filter1.isEmpty()) {
            Stream<Note> stream = list.stream().filter(note -> note.getHeadline().contains(filter1));
            list = stream.collect(Collectors.toList());
        } else if (filter2 != null && !filter2.isEmpty()) {
            Stream<Note> stream = list.stream().filter(note -> note.getNoteText().contains(filter2));
            list = stream.collect(Collectors.toList());
        }
        return list;
    }
}
