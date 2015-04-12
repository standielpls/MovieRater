package com.stanley.app1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.*;


public class Main {
    private MovieData md;
    private HashMap<String, Double>  h;
    private List<String> movieName;

    public Main() {
        h = new HashMap<String, Double>();
        movieName = new ArrayList<String>();
        loadData();
        storeRatings();
    }
    public static void main(String[] args) {
        Main m = new Main();
        m.printData();
    }
    public void printData() {
        int num = h.size();

        System.out.println(h.values());
    }

    public void loadData() {

        final File folder = new File("/Users/stanleychin/Desktop/Movies");
        listFilesForFolder(folder);
        System.out.println(movieName.toString());
        /*String skeleton = "http://www.omdbapi.com/?t=" + movieName1+fivmovieName2 + "&y=&plot=short&r=json";
        String url="http://www.omdbapi.com/?t=fast+five&y=&plot=short&r=json";

        try {
            String jsonData = readUrl(url);

            Gson gsonCurrent = new Gson();
            md = gsonCurrent.fromJson(jsonData, MovieData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void listFilesForFolder(File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String fileName = fileEntry.getName();
                if (fileName.substring(fileName.length() - 3, fileName.length()).equals("mp4")) {
                    movieName.add(fileName);
                }
            }
        }
    }

    private void modifyMovieTitle() {

        int index = 0;
        while(index < movieName.size()) {

            index++;
        }
    }

    private void storeRatings() {
        h.put(md.getTitle(), Double.parseDouble(md.getImdbRating()));
    }
    /**
     * Helper method to read the URL as a String and make a request to the server to read the contents of the page
     *
     * @param urlString is the String that links to the json file
     * @return String
     */
    private static String readUrl(String urlString) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

}

