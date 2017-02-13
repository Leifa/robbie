package de.leifaktor.robbie.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.files.FileHandle;

import de.leifaktor.robbie.data.Episode;
import de.leifaktor.robbie.io.IO;

public class SaveDialog implements TextInputListener {
    
    Episode episode;
  
    public SaveDialog(Episode e) {
        this.episode = e;
    }
    
    @Override
    public void input(String text) {
        FileHandle file = Gdx.files.local("episodes/" + text);
        IO.save(episode, file);
    }

    @Override
    public void canceled () {
        
    }

 }
