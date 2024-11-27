package e4;


import java.util.*;

import static java.util.Collections.max;


public class Movie {

    private final String title;

    List<MovieRating> filmList;

    /**
     * Creates a new movie with the list of ratings empty .
     *
     * @param title Movie title .
     */
    public Movie(String title) {
        this.title = title;
        filmList = new ArrayList<>();
    }

    /**
     * Returns the movie title
     *
     * @return the movie title .
     */
    public String getTitle() {
        return title;
    }

    /**
     * Inserts a new movieRating .
     * It is allowed to insert NOT_RATED .
     *
     * @param movieRating MovieRating to be inserted .
     */
    public void insertRating(MovieRating movieRating) {
        filmList.add(movieRating);
    }

    /**
     * Check if this movie has any rating .
     *
     * @return true if and only if there is a valuation other than NOT_RATED .
     */
    private boolean isRated() {
        if(filmList.size() == 0) return false; //Check if its empty
        else {
            int i = 0;
            while (i < filmList.size()) { //iterate through the list to see if it has ratings other than NOT_RATED
                    if (filmList.get(i).isBetterThan(-1)) return true;
                    else i++;
                }
        }
    return false;}

    /**
     * Gets the highest rating for this movie .
     *
     * @return maximum rating ; or NOT_RATED if there are no ratings .
     */
    public MovieRating maximumRating() {
        if (isRated()) { //Check if there are ratings
            return max(filmList);

        } else return MovieRating.NOT_RATED;
    }

    /**
     * Calculate the numerical average rating of this movie .
     * NOT_RATED values are not considered .
     *
     * @return Numerical average rating of this movie .
     * @throws java . util . NoSuchElementException if there are no valid ratings .
     */
    public double averageRating() {
        if (isRated()) { //Check if there are ratings
            double index=0, sum=0;
            int i=0;
            while(i<filmList.size()) {
                if(filmList.get(i).isBetterThan(-1)) { //iterate through the list to see if it has ratings other than NOT_RATED
                    sum = sum + (double)filmList.get(i).getNumericRating();
                    index++;
                }
                i++;
            }
            return sum / index; //Compute the average
        } else {
            throw new NoSuchElementException();
        }
    }
}


