# MovieRater

###Synopsis     
MovieRater takes your collection of movie titles and retrieves the ratings for those movies.    
     
MovieRater uses [OMDBAPI](https://www.omdbapi.com/) to get the ratings for the movies. 
It uses the Jackson library to parse data from OMDBAPI and data binds JSON to the Java objects.    
    
    
###Build and Install    
Before you start, you need to compile your list of movies:     
```
$cd /path/to/movies-folder/     
$ls -R > moviestitle.txt     
```
Now for the program installation
```
$git clone git@github.com:standielpls/MovieRater.git    
$cd MovieRater
```
At this time, move moviestitle.txt text file into this directory
```
$mvn package
$java -jar target/Movie_Rater-1.0-SNAPSHOT-jar-with-dependencies.jar
```

###Usage Example
`==========================================`     
`RATING  TITLE`     
`8.1		12 Years a Slave`    
`7.0		28 Weeks Later`          
`6.2		30 Minutes or Less`          
`6.4		A Long Way Down`     
         
         
###Documentation
