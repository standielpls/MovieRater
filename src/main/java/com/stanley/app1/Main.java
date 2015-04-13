package com.stanley.app1;

/**
 Description:
 MovieRater is used to track the ratings given on IMDB of a given 'Movies' directory.
 The result is a printed list of the movie name and the rating. The practical use of this
 program is to view what movie has great ratings so that you may randomly pick a movie
 to watch for your interest, effortlessly.

 Author:
 Stanley Chin

 Date:
 April 12, 2015
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Main {
    private MovieData md; //the object that carries all the information about the movie
    private HashMap<String, Double> h;  //hash map to store the ratings based on the movie title as the key
    private List<String> movieName; //stores all the movie names used for url detection
    private HashMap<String, Integer> movie_name_year;   //stores the year of each movie for url detection
    private List<String> movies;    //stores all the user-friendly movie names

    /**
     * Constructor for Main, instantiates the declared instance variables
     */
    public Main() {
        movies = new ArrayList<String>();
        h = new HashMap<String, Double>();
        movie_name_year = new HashMap<String, Integer>();
        movieName = new ArrayList<String>();

        //in addition, it loads the data, modify the titles, and parse the data
        loadData();
        modifyMovieTitle();
        parseMovieData();
    }

    /**
     * Main method used to run the program
     * @param args
     */
    public static void main(String[] args) {
        Main m = new Main();
        m.printData();
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

    /**
     * Displays the data
     */
    public void printData() {
        System.out.println("==========================================");
        System.out.println("RATING\t\tTITLE");
        int i=0;
        while(i < h.size()) {
            System.out.println(h.get(movies.get(i))  + "\t\t" + movies.get(i));
            i++;
        }
    }

    /**
     * loads the data from the directory chosen
     */
    public void loadData() {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);
        final File folder = f.getCurrentDirectory();
        //final File folder = new File("/Users/stanleychin/Desktop/Movies");

        listFilesForFolder(folder);
    }

    /**
     * helper method used to list the folders and stores the raw name into the movieNmae list
     * @param folder the directory used to display the contents of
     */
    public void listFilesForFolder(File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                String fileName = fileEntry.getName();
                //picks strictly from a direct set of file names
                if (fileName.substring(fileName.length() - 3, fileName.length()).equals("mp4") && fileName.contains("x264")
                        && !fileName.contains("ASAP") && !fileName.contains("asap")) {
                    movieName.add(fileName);
                }
            }
        }
    }

    /**
     * modifies the movie title name into something readable by the URL
     */
    private void modifyMovieTitle() {

        int index = 0;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int index_to_parse = 0;

        //Loops through the list of each movieName movie name
        while (index < movieName.size()) {
            String movieToModify = movieName.get(index); //the movie to modify
            int year_counter = 2000;    //start at year 2000

            //get rid of the extra stuff after the year
            while (year_counter <= year) {
                String year1 = Integer.toString(year_counter);
                if (movieToModify.contains(year1)) {
                    index_to_parse = movieToModify.indexOf(year1);
                    break;
                }
                year_counter++;
            }

            //get rid of periods
            String parsedMovieName = movieToModify.substring(0, index_to_parse - 1);
            if (parsedMovieName.contains("&")) {
                parsedMovieName = parsedMovieName.replaceAll("&", "%26");   //replace all & with %26 (ASCII)
                index_to_parse += 2;
                parsedMovieName = parsedMovieName.replaceAll("\\.", " ");   //replace all periods with space

            }
            else if (parsedMovieName.contains(",")) {
                parsedMovieName = parsedMovieName.replace(",", "%2C");      //replace all commas with %2c (ASCII)
                index_to_parse +=2;
                parsedMovieName = parsedMovieName.replaceAll("\\.", "");   //replace all periods with space
            }
            else
                parsedMovieName = parsedMovieName.replaceAll("\\.", " ");   //replace all periods with space

            movieName.set(index, parsedMovieName.substring(0, index_to_parse - 1));
            movie_name_year.put(parsedMovieName, year_counter);
            index++;
        }
    }

    /**
     * parses the movie data by reading the url, sending a request to the network and retrieving the data
     */
    private void parseMovieData() {
        final String skeletonFront = "http://www.omdbapi.com/?t=";
        final String skeletonMid = "&y=";
        final String skeletonEnd = "&plot=short&r=json";
        int index = 0;
        while (index < movieName.size()) {
            String movie_name = movieName.get(index);
            String[] parts = movie_name.split(" ");
            int i = 0;
            String middlePiece = "";
            middlePiece += parts[i++];
            while (i < parts.length) {
                middlePiece += "+";
                middlePiece += parts[i++];
            }
            String url = skeletonFront + middlePiece + skeletonMid;

            String yearPiece = Integer.toString(movie_name_year.get(movie_name));
            url += yearPiece;
            url += skeletonEnd;
            try {

                String jsonData = readUrl(url);

                //GSON PARSING
//                Gson gsonCurrent = new Gson();
//                md = gsonCurrent.fromJson(jsonData, MovieData.class);
                //JACKSON PARSING
                //create ObjectMapper instance
                ObjectMapper objectMapper = new ObjectMapper();

                //convert json string to object
                md= objectMapper.readValue(jsonData, MovieData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (md.getImdbRating().equals("N/A"))
                storeRating(md.getTitle(), 0.0);
            else
                storeRating(md.getTitle(), Double.parseDouble(md.getImdbRating()));
            index++;
        }
    }

    /**
     * stores the data
     * @param s name of movie
     * @param d rating of movie
     */
    private void storeRating(String s, Double d) {
        h.put(s, d);
        movies.add(s);
    }
}

