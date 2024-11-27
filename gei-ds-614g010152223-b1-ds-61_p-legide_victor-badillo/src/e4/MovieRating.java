package e4;
/**The following values with their corresponding numerical values: NOT RATED,
AWFUL (0), BAD (2), MEDIOCRE (4), GOOD (6), EXCELLENT (8), MASTERPIECE (10).*/
public enum MovieRating {
    NOT_RATED(-1),
    AWFUL(0),
    BAD(2),
    MEDIOCRE(4),
    GOOD(6),
    EXCELLENT(8),
    MASTERPIECE(10);


    private final int rating;

/**The correct constructor for said elements*/
    MovieRating(int rating) {
        this.rating=rating;
    }

/**A method called int getNumericRating() that returns the numerical value
of the rating.*/
    public int getNumericRating(){
        return rating;
    }

/**A public method called boolean isBetterThan(MovieRating) that returns
true if and only if the rating is better than the one that is passed as an
argument. Assume that any rating is better than NOT RATED.*/
    public boolean isBetterThan(int MovieRating){
        return this.getNumericRating() > MovieRating;
    }
}
