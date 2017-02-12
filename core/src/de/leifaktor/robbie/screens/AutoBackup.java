package de.leifaktor.robbie.screens;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import de.leifaktor.robbie.io.IO;

public class AutoBackup {
    
    int maxNumberOfBackups = 5;
    int interval = 60; // in seconds
    float accu;
    DateTimeFormatter dtf;
    
    EditorData data;
    
    public AutoBackup(EditorData data) {
        this.data = data;
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    }
    
    public void setInterval(int seconds) {
        this.interval = seconds;
    }
    
    public void update(float delta) {
        accu += delta;
        if (accu > interval) {
            accu -= interval;
            save();
        }
    }
    
    private void save() {
        if (data.episode != null) {            
            LocalDateTime now = LocalDateTime.now();
            String filename = dtf.format(now);
            IO.save(data.episode, "episodes/backup/" + filename + ".epi");
        }
        LinkedList<FileHandle> files = new LinkedList<FileHandle>(Arrays.asList(Gdx.files.local("episodes/backup/").list()));
        Collections.sort(files, new Comparator<FileHandle>() {
            @Override
            public int compare(FileHandle o1, FileHandle o2) {                
                return o1.lastModified() > o2.lastModified() ? 1 : -1;
            }            
        });
        while (files.size() > maxNumberOfBackups) {
            files.get(0).delete();
            files.remove(0);
        }
    }
    
    public void loadLatestBackup() {
        LinkedList<FileHandle> files = new LinkedList<FileHandle>(Arrays.asList(Gdx.files.local("episodes/backup/").list()));
        Collections.sort(files, new Comparator<FileHandle>() {
            @Override
            public int compare(FileHandle o1, FileHandle o2) {                
                return o1.lastModified() > o2.lastModified() ? 1 : -1;
            }            
        });
        if (files.size() > 0) {
            data.episode = IO.load(files.getLast());
        }
    }

}
