package com.nixsolutions.xxe_demo;

import com.nixsolutions.xxe_demo.model.Note;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserNoteService
{
  private static final Map<String, List<Note>> NOTES =
      new ConcurrentHashMap<String, List<Note>>();


  public void addNote(Note note)
  {
    Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
    List<Note> currentUserNotes = getUserNotes();

    note.setDate(new Date());
    currentUserNotes.add(note);

    NOTES.put(currentUser.getName(), currentUserNotes);
  }

  public List<Note> getAllUserNotes()
  {
    return getUserNotes();
  }

  private List<Note> getUserNotes()
  {
    Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
    List<Note> currentUserNotes = NOTES.get(currentUser.getName());

    if (currentUserNotes == null)
    {
      currentUserNotes = new ArrayList<Note>();
    }

    return currentUserNotes;
  }
}
