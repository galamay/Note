package com.task.note;

import com.task.note.controller.NoteController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@TestPropertySource("/application-test.properties")
@Sql(value = {"/note-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/note-list-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class NoteControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private NoteController noteController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void noteListTest() throws Exception {
        this.mockMvc.perform(get("/note"))
                .andDo(print())
                .andExpect(xpath("//div[@id='note-list']/div").nodeCount(4));
    }

    @Test
    public void filterNoteTest() throws Exception {
        this.mockMvc.perform(get("/note").param("filterHeadline", "опять"))
                .andDo(print())
                .andExpect(xpath("//div[@id='note-list']/div").nodeCount(2))
                .andExpect(xpath("//div[@id='note-list']/div[@id=3]").exists())
                .andExpect(xpath("//div[@id='note-list']/div[@id=4]").exists());

    }


    @Test
    public void addNoteTest() throws Exception {
        this.mockMvc.perform(post("/note")
                .param("headline", "заголовок")
                .param("noteText", "новая заметочка"))
                .andDo(print())
                .andExpect(view().name("note"))
                .andExpect(xpath("//div[@id='note-list']/div").nodeCount(5))
                .andExpect(xpath("//div[@id='note-list']/div[@id=10]").exists());
    }
}
