package com.mmd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class Album {

    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist){
        this.name = name;
        this.artist = artist;
        songs = new ArrayList<>();
    }

    public boolean addSong(String title, double duration){
        Song song = new Song(title, duration);
        if(findSong(title) !=null ){
            return false;
        }
        return songs.add( song );
    }

    private Song findSong(String title){

        for(var element : songs){
            if(element.getTitle().equalsIgnoreCase(title) ){
                return element;
            }
        }
        return null;
    }

    public boolean addToPlayList(String title, LinkedList<Song> songList){
        if(findSong(title)!=null ){
            return true;
        }
        return songs.addAll(songList);
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        // Note: trackNumbers start at 1.
        if (trackNumber <= 0 || trackNumber > songs.size()) {
            // trackNumber was out of range
            return false;
        }

        // get the song from the album
        Song songToAdd = songs.get(trackNumber-1);
        String songToAddTitle = songToAdd.getTitle();

        // See if the song has already been added to the playList
        ListIterator<Song> playListIterator = playList.listIterator();
        while (playListIterator.hasNext()) {
            if  (playListIterator.next().getTitle().compareTo(songToAddTitle) == 0) {
                // The song is already in the playlist
                return false;
            }
        }

        // Add the song to the end of the playList.
        playList.add(songToAdd);
        return true;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", songs=" + songs +
                '}';
    }
}
