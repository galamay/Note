package com.task.note.service;

import com.task.note.domain.Note;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceImplTest {


    private static NoteServiceImpl mockNoteServiceImpl;
    private static Note note1;
    private static Note note2;

    @BeforeClass
    public static void init() {

        mockNoteServiceImpl = mock(NoteServiceImpl.class);
        note1 = new Note("здесь должен быть заголовок", "здесь должен быть текст");
        note2 = new Note("здесь снова заголовок", "здесь по-прежнему текст");
        List<Note> notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);

        when(mockNoteServiceImpl.getAll()).thenReturn(Arrays.asList(note1, note2));
        when(mockNoteServiceImpl.addNote("здесь снова заголовок", "здесь по-прежнему текст")).thenReturn(note2);
        when(mockNoteServiceImpl.findByHeadlineOrNoteText("должен", "")).
                thenReturn(notes);
        when(mockNoteServiceImpl.findByHeadlineOrNoteText("", "по-прежнему")).
                thenReturn(notes);

        /*when(mockNoteServiceImpl.delete(1l)).thenReturn("DELETED");
        when(mockNoteServiceImpl.delete(2)).thenReturn("REMOVED");*/

    }

    @Test
    public void addNote() {
        Note note = mockNoteServiceImpl.addNote("здесь снова заголовок", "здесь по-прежнему текст");

        assertNotNull(note);
        assertEquals("здесь снова заголовок", note.getHeadline());
    }

    /* @Test
     public void delete() {
         String status = mockNoteServiceImpl.delete(2L);

         assertEquals("REMOVED", status);
     }
 */
    @Test
    public void findByHeadlineOrNoteText() {
        List<Note> find = mockNoteServiceImpl.findByHeadlineOrNoteText("должен", "");
        List<Note> notes = new ArrayList<>();
        notes.add(note1);
        Assert.assertArrayEquals(notes.toArray(), find.toArray());
    }

    @Test
    public void getAll() {
        List allNotes = mockNoteServiceImpl.getAll();

        assertNotNull(allNotes);
        assertEquals(2, allNotes.size());
    }
}