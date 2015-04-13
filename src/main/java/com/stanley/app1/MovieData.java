
package com.stanley.app1;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "Title",
    "Year",
    "Rated",
    "Released",
    "Runtime",
    "Genre",
    "Director",
    "Writer",
    "Actors",
    "Plot",
    "Language",
    "Country",
    "Awards",
    "Poster",
    "Metascore",
    "imdbRating",
    "imdbVotes",
    "imdbID",
    "Type",
    "Response"
})
public class MovieData {

    @JsonProperty("Title")
    private String Title;
    @JsonProperty("Year")
    private String Year;
    @JsonProperty("Rated")
    private String Rated;
    @JsonProperty("Released")
    private String Released;
    @JsonProperty("Runtime")
    private String Runtime;
    @JsonProperty("Genre")
    private String Genre;
    @JsonProperty("Director")
    private String Director;
    @JsonProperty("Writer")
    private String Writer;
    @JsonProperty("Actors")
    private String Actors;
    @JsonProperty("Plot")
    private String Plot;
    @JsonProperty("Language")
    private String Language;
    @JsonProperty("Country")
    private String Country;
    @JsonProperty("Awards")
    private String Awards;
    @JsonProperty("Poster")
    private String Poster;
    @JsonProperty("Metascore")
    private String Metascore;
    @JsonProperty("imdbRating")
    private String imdbRating;
    @JsonProperty("imdbVotes")
    private String imdbVotes;
    @JsonProperty("imdbID")
    private String imdbID;
    @JsonProperty("Type")
    private String Type;
    @JsonProperty("Response")
    private String Response;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Title
     */
    @JsonProperty("Title")
    public String getTitle() {
        return Title;
    }

    /**
     * 
     * @param Title
     *     The Title
     */
    @JsonProperty("Title")
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * 
     * @return
     *     The Year
     */
    @JsonProperty("Year")
    public String getYear() {
        return Year;
    }

    /**
     * 
     * @param Year
     *     The Year
     */
    @JsonProperty("Year")
    public void setYear(String Year) {
        this.Year = Year;
    }

    /**
     * 
     * @return
     *     The Rated
     */
    @JsonProperty("Rated")
    public String getRated() {
        return Rated;
    }

    /**
     * 
     * @param Rated
     *     The Rated
     */
    @JsonProperty("Rated")
    public void setRated(String Rated) {
        this.Rated = Rated;
    }

    /**
     * 
     * @return
     *     The Released
     */
    @JsonProperty("Released")
    public String getReleased() {
        return Released;
    }

    /**
     * 
     * @param Released
     *     The Released
     */
    @JsonProperty("Released")
    public void setReleased(String Released) {
        this.Released = Released;
    }

    /**
     * 
     * @return
     *     The Runtime
     */
    @JsonProperty("Runtime")
    public String getRuntime() {
        return Runtime;
    }

    /**
     * 
     * @param Runtime
     *     The Runtime
     */
    @JsonProperty("Runtime")
    public void setRuntime(String Runtime) {
        this.Runtime = Runtime;
    }

    /**
     * 
     * @return
     *     The Genre
     */
    @JsonProperty("Genre")
    public String getGenre() {
        return Genre;
    }

    /**
     * 
     * @param Genre
     *     The Genre
     */
    @JsonProperty("Genre")
    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    /**
     * 
     * @return
     *     The Director
     */
    @JsonProperty("Director")
    public String getDirector() {
        return Director;
    }

    /**
     * 
     * @param Director
     *     The Director
     */
    @JsonProperty("Director")
    public void setDirector(String Director) {
        this.Director = Director;
    }

    /**
     * 
     * @return
     *     The Writer
     */
    @JsonProperty("Writer")
    public String getWriter() {
        return Writer;
    }

    /**
     * 
     * @param Writer
     *     The Writer
     */
    @JsonProperty("Writer")
    public void setWriter(String Writer) {
        this.Writer = Writer;
    }

    /**
     * 
     * @return
     *     The Actors
     */
    @JsonProperty("Actors")
    public String getActors() {
        return Actors;
    }

    /**
     * 
     * @param Actors
     *     The Actors
     */
    @JsonProperty("Actors")
    public void setActors(String Actors) {
        this.Actors = Actors;
    }

    /**
     * 
     * @return
     *     The Plot
     */
    @JsonProperty("Plot")
    public String getPlot() {
        return Plot;
    }

    /**
     * 
     * @param Plot
     *     The Plot
     */
    @JsonProperty("Plot")
    public void setPlot(String Plot) {
        this.Plot = Plot;
    }

    /**
     * 
     * @return
     *     The Language
     */
    @JsonProperty("Language")
    public String getLanguage() {
        return Language;
    }

    /**
     * 
     * @param Language
     *     The Language
     */
    @JsonProperty("Language")
    public void setLanguage(String Language) {
        this.Language = Language;
    }

    /**
     * 
     * @return
     *     The Country
     */
    @JsonProperty("Country")
    public String getCountry() {
        return Country;
    }

    /**
     * 
     * @param Country
     *     The Country
     */
    @JsonProperty("Country")
    public void setCountry(String Country) {
        this.Country = Country;
    }

    /**
     * 
     * @return
     *     The Awards
     */
    @JsonProperty("Awards")
    public String getAwards() {
        return Awards;
    }

    /**
     * 
     * @param Awards
     *     The Awards
     */
    @JsonProperty("Awards")
    public void setAwards(String Awards) {
        this.Awards = Awards;
    }

    /**
     * 
     * @return
     *     The Poster
     */
    @JsonProperty("Poster")
    public String getPoster() {
        return Poster;
    }

    /**
     * 
     * @param Poster
     *     The Poster
     */
    @JsonProperty("Poster")
    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    /**
     * 
     * @return
     *     The Metascore
     */
    @JsonProperty("Metascore")
    public String getMetascore() {
        return Metascore;
    }

    /**
     * 
     * @param Metascore
     *     The Metascore
     */
    @JsonProperty("Metascore")
    public void setMetascore(String Metascore) {
        this.Metascore = Metascore;
    }

    /**
     * 
     * @return
     *     The imdbRating
     */
    @JsonProperty("imdbRating")
    public String getImdbRating() {
        return imdbRating;
    }

    /**
     * 
     * @param imdbRating
     *     The imdbRating
     */
    @JsonProperty("imdbRating")
    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    /**
     * 
     * @return
     *     The imdbVotes
     */
    @JsonProperty("imdbVotes")
    public String getImdbVotes() {
        return imdbVotes;
    }

    /**
     * 
     * @param imdbVotes
     *     The imdbVotes
     */
    @JsonProperty("imdbVotes")
    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    /**
     * 
     * @return
     *     The imdbID
     */
    @JsonProperty("imdbID")
    public String getImdbID() {
        return imdbID;
    }

    /**
     * 
     * @param imdbID
     *     The imdbID
     */
    @JsonProperty("imdbID")
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    /**
     * 
     * @return
     *     The Type
     */
    @JsonProperty("Type")
    public String getType() {
        return Type;
    }

    /**
     * 
     * @param Type
     *     The Type
     */
    @JsonProperty("Type")
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * 
     * @return
     *     The Response
     */
    @JsonProperty("Response")
    public String getResponse() {
        return Response;
    }

    /**
     * 
     * @param Response
     *     The Response
     */
    @JsonProperty("Response")
    public void setResponse(String Response) {
        this.Response = Response;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
