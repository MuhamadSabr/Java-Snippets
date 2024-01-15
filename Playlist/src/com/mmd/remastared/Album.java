package com.mmd.remastared;

import java.util.*;

public class Album {

    private String name;
    private String artist;
    private SongList songs;



    public Album(String name, String artist){
        this.name = name;
        this.artist = artist;
        songs = new SongList();
    }

    public boolean addSong(String title, double duration){
        Song song = new Song(title, duration);
        return songs.add(song);
    }

    public boolean addToPlayList(String title, LinkedList<Song> songList){
        if(songs.findSong(title)!=null ){
            return true;
        }
        return false;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        // Note: trackNumbers start at 1.
        if (trackNumber <= 0 || trackNumber > songs.songs.size()) {
            // trackNumber was out of range
            return false;
        }

        // get the song from the album
        Song songToAdd = songs.songs.get(trackNumber-1);
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





    public static class SongList{
        private ArrayList<Song> songs;
        private SongList(){
            songs = new ArrayList<>();
        }

        private boolean add(Song song){
            if( findSong(song.getTitle())==null ){
                songs.add(song);
                return true;
            }
            return false;
        }

        private Song findSong(String title){

            for(var element : songs){
                if(element.getTitle().equalsIgnoreCase(title) ){
                    return element;
                }
            }
            return null;
        }

        private Song findSong(int trackNumber){

            if (trackNumber <= 0 || trackNumber > songs.size()) {
                // trackNumber was out of range
                return null;
            }

            return songs.get(trackNumber-1);
        }

    }


}