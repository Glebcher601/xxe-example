package com.nixsolutions.xxe_demo.resource;

import com.nixsolutions.xxe_demo.UnmarshallingComponent;
import com.nixsolutions.xxe_demo.UserNoteService;
import com.nixsolutions.xxe_demo.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "/notes")
@RestController
public class UserNoteResource
{
  @Autowired
  private UserNoteService userNoteService;

  @Autowired
  private UnmarshallingComponent unmarshallingComponent;

  @PutMapping
  public ResponseEntity createNote(@RequestBody String payload)
  {
    Note note = unmarshallingComponent.unmarshall(payload);
    userNoteService.addNote(note);

    return ResponseEntity.ok().build();
  }

  @GetMapping(path = "/all")
  public List<Note> getNotes()
  {
    return userNoteService.getAllUserNotes();
  }

}
