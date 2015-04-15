package com.stanley.app1;

/**
 * Description:
 * MovieRater is used to track the ratings given on IMDB of a given 'Movies' directory.
 * The result is a printed list of the movie name and the rating. The practical use of this
 * program is to view what movie has great ratings so that you may randomly pick a movie
 * to watch for your interest, effortlessly.
 * <p>
 * Author:
 * Stanley Chin
 * <p>
 * Date:
 * April 12, 2015
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Main {
    private MovieData md; //the object that carries all the information about the movie
    private TreeMap<String, Double> h;  //hash map to store the ratings based on the movie title as the key
    private List<String> movieName; //stores all the movie names used for url detection
    private HashMap<String, Integer> movie_name_year;   //stores the year of each movie for url detection
    private Set<String> movies;    //stores all the user-friendly movie names
    private List<String> bullshit;
    private List<Node> sortedList;
    private List <String> notYifMovies;
    private int index_to_parse;
    private int year_counter;
    /**
     * Constructor for Main, instantiates the declared instance variables
     */
    public Main() {
        System.out.println("Hang tight, your list is being processed ...");
        movies = new HashSet<String>();
        h = new TreeMap<String, Double>();
        movie_name_year = new HashMap<String, Integer>();
        movieName = new ArrayList<String>();
        bullshit = new ArrayList<String>();
        sortedList = new ArrayList<Node>();
        notYifMovies = new ArrayList<String>();
        init();
    }

    private void init() {
        loadData();
        modifyMovieTitle();
        parseMovieData();
        sortData();
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
        int i = 0;
//        while (i < h.size()) {
//            System.out.println(h.get(movies.get(i)) + "\t\t" + movies.get(i));
//            i++;
//        }
        while(i<sortedList.size()) {
            System.out.println(sortedList.get(i).toString());
            i++;
        }
        int j = 0;
        System.out.println("\n----------------Bullshit that didn't make it------------------");
        while (j < bullshit.size()) {
            System.out.println(bullshit.get(j++));
        }

        int k = 0;
        while(k < notYifMovies.size()) {
            System.out.println(notYifMovies.get(k++));
        }
    }

    /**
     * loads the data from the directory chosen
     */
    public void loadData() {
        final File folder = new File("movietitles.txt");
        listFilesDirectly(folder);
    }

    /**
     * helper method used to list the folders and stores the raw name into the movieNmae list
     * @param folder the directory used to display the contents of
     */
    private void listFilesDirectly(File folder) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(folder));
            String line;
            while ((line = br.readLine()) != null) {
                if (haveYear(line) && !line.startsWith("."))
                    movieName.add(line);
            }
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * modifies the movie title name into something readable by the URL
     */
    private void modifyMovieTitle() {

        int index = 0;

        //Loops through the list of each movieName movie name
        while (index < movieName.size()) {
            index_to_parse = 0;

            String movieToModify = movieName.get(index); //the movie to modify
            Boolean movie_year_present = haveYear(movieToModify);
            //get rid of periods
            String parsedMovieName = replaceString(movieToModify.substring(0, index_to_parse - 1));

            movieName.set(index, parsedMovieName.substring(0, index_to_parse - 1));
            movie_name_year.put(parsedMovieName, year_counter);

            index++;
        }
    }
    private Boolean haveYear(String movieToModify) {
        year_counter = 1980;    //start at year 1980
        int year = Calendar.getInstance().get(Calendar.YEAR);

        //get rid of the extra stuff after the year
        while (year_counter <= year) {
            String year1 = Integer.toString(year_counter);
            if (movieToModify.contains(year1)) {
                index_to_parse = movieToModify.indexOf(year1);
                return true;
            }
            year_counter++;
        }
        return false;
    }
    private String replaceString(String parsedMovieName) {
        if (parsedMovieName.contains("&")) {
            parsedMovieName = parsedMovieName.replaceAll("&", "%26");   //replace all & with %26 (ASCII)
            index_to_parse += 2;
            parsedMovieName = parsedMovieName.replaceAll("\\.", " ");   //replace all periods with space

        } else if (parsedMovieName.contains(",")) {
            parsedMovieName = parsedMovieName.replace(",", "%2C");      //replace all commas with %2c (ASCII)
            index_to_parse += 2;
            parsedMovieName = parsedMovieName.replaceAll("\\.", "");   //replace all periods with space
        } else if (parsedMovieName.contains("'")) {
            parsedMovieName = parsedMovieName.replace("'", "%27");      //replace all apostrophe with %27 (ASCII)
            index_to_parse += 2;
            parsedMovieName = parsedMovieName.replaceAll("\\.", " ");   //replace all periods with space
        } else if (parsedMovieName.contains(".UNRATED")) {
            parsedMovieName = parsedMovieName.replace(".UNRATED", "");      //replace all apostrophe with %27 (ASCII)
            index_to_parse -= 8;
            parsedMovieName = parsedMovieName.replaceAll("\\.", " ");   //replace all periods with space

        } else if (parsedMovieName.contains(".EXTENDED")) {
            parsedMovieName = parsedMovieName.replace(".EXTENDED", "");
            index_to_parse -= 9;
            parsedMovieName = parsedMovieName.replaceAll("\\.", " ");   //replace all periods with space

        } else if (parsedMovieName.contains("Hes")) {
            parsedMovieName = parsedMovieName.replace("Hes", "He%27s");
            index_to_parse += 3;
            parsedMovieName = parsedMovieName.replaceAll("\\.", " ");   //replace all periods with space
        } else if (parsedMovieName.contains("Shes")) {
            parsedMovieName = parsedMovieName.replace("Shes", "She%27s");
            index_to_parse += 3;
            parsedMovieName = parsedMovieName.replaceAll("\\.", " ");   //replace all periods with space

        } else if (parsedMovieName.contains("Lets")) {
            parsedMovieName = parsedMovieName.replace("Lets", "Let%27s");
            index_to_parse += 3;
            parsedMovieName = parsedMovieName.replaceAll("\\.", " ");   //replace all periods with space
        } else if (parsedMovieName.contains(".and.")) {
            parsedMovieName = parsedMovieName.replace(".and.", "%26");
            index_to_parse -=2;
        } else if (parsedMovieName.contains(".And.")) {
            parsedMovieName = parsedMovieName.replace(".And.", "%26");
            index_to_parse -=2;
        } else
            parsedMovieName = parsedMovieName.replaceAll("\\.", " ");   //replace all periods with space

        if (parsedMovieName.contains(" vs ")) {
            parsedMovieName = parsedMovieName.replace(" vs ", " vs. ");
            index_to_parse++;
        }
        return parsedMovieName;
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
                ObjectMapper objectMapper = new ObjectMapper();

                //convert json string to object
                md = objectMapper.readValue(jsonData, MovieData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String rep = md.getResponse();
            if (rep.equals("True")) {
                if (!md.getImdbRating().equals("N/A"))
                    storeRating(md.getTitle(), Double.parseDouble(md.getImdbRating()));
            }
            else
                bullshit.add(movie_name);

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
    private void sortData() {
        List<Node> keys = new ArrayList<Node>();
        int i=0;
        //Node n = new Node(movies.get(i), h.get(movies.get(i)));
        Iterator<String> it = movies.iterator();
        while (it.hasNext()) {
            String x = (String) it.next();
            Node n = new Node(x, h.get(x));
            keys.add(n);
        }

        sortedList =  sort(keys);
    }

    private List<Node> sort(List<Node> nodeList) {
        List<Node> sortedList = new ArrayList<Node>();

        int i = 0;
        while (i < nodeList.size()) {
            double highestNum=nodeList.get(i).getValue();
            int j=0;
            int index=0;
            while (j < nodeList.size()) {
                if (nodeList.get(j).getValue() > highestNum) {
                    highestNum = nodeList.get(j).getValue();
                    index=j;
                }
                j++;
            }

            sortedList.add(nodeList.get(index));
            nodeList.remove(index);
            i++;
        }
        return sortedList;
    }
    public class Node {
        private Double value;
        private String key;

        public Node(String k, Double v) {
            this.key = k;
            this.value = v;
        }

        public Double getValue() {
            return this.value;
        }
        public String getKey() {
            return this.key;
        }
        public void setValue(Double v) {
            this.value = v;
        }
        public void setKey(String k) {
            this.key = k;
        }
        public String toString() {
            return value + "\t\t" + key;
        }
    }
}

