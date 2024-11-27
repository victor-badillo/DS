package e2;

public class SocialDistance {
    /**
     * Given the layout of a class with available sites marked with an 'A' and
     * invalid sites marked with a '. ', returns the resulting layout with the
     * sites occupied by the students marked with a '#' following two rules :
     * - Students occupy an empty seat if there are no other adjacent students .
     * - A student leaves a seat empty if he/ she has 4 or more adjacent students .
     * @param layout The initial layout .
     * @return The resulting layout .
     * @throws IllegalArgumentException if the initial layout is invalid (is null ,
     * is ragged , includes characters other than '.' or 'A ')).
     */
    public static char [][] seatingPeople ( char [][] layout ) {

        //Check if matrix is null
        if (layout == null) {
            throw new IllegalArgumentException();
        } else {
            char[][] copyLayout = new char[layout.length + 2][layout[0].length + 2]; //Copy of the original layout with 2  rows and 2 columns more
            boolean check = false;
            int countA = 0, countHash = 0, countDot = 0;
            int colIndex = -1, colIndex2 = -1;
            char left, right;

            //Check if the layout is ragged.
            for(int c=1; c< layout.length; c++) {
                if(layout[c].length != layout[0].length){
                    throw new IllegalArgumentException();
                }
            }
            //Check invalid characters
            for (char[] row : layout) {
                for (char element : row) {
                    if (element != 'A' && element != '.')
                        throw new IllegalArgumentException();
                }
            }
            //Expand matrix with surrounding '.'
            //Insert '.' in the first and the last row
            for (int j = 0; j < copyLayout[0].length; j++) {
                copyLayout[0][j] = '.';
                copyLayout[copyLayout.length - 1][j] = '.';
            }
            //Insert '.' in the first and last column jumping the position of the first row and the last row
            for (int k = 1; k < copyLayout.length - 1; k++) {
                copyLayout[k][0] = '.';
                copyLayout[k][copyLayout[0].length-1] = '.';
            }
            //Repeat loop while layout and copyLayout are different
            while (!check) {

                //We copy the layout inside the copy border
                for (int row = 0; row < layout.length; row++) {
                    System.arraycopy(layout[row], 0, copyLayout[row + 1], 1, layout[0].length);
                }

                //We check the distribution in copyLayout, and we create the new distribution inserting it in layout matrix
                for (int m = 1; m < copyLayout.length - 1; m++) {
                    for (int z = 1; z < copyLayout[0].length - 1; z++) {
                        //Only check seats that can be occupied
                        if (copyLayout[m][z] != '.'){
                            for (int f = 0; f <= 2; f++) {
                                if (copyLayout[m - 1][z + colIndex] == 'A') { countA++;
                                }else if (copyLayout[m - 1][z + colIndex] == '#') { countHash++;
                                }else{ countDot++; }
                                colIndex++;

                                if (copyLayout[m + 1][z + colIndex2] == 'A') { countA++;
                                }else if (copyLayout[m + 1][z + colIndex2] == '#') { countHash++;
                                }else{ countDot++; }
                                colIndex2++;
                            }
                            colIndex = -1;
                            colIndex2 = -1;

                            left = copyLayout[m][z - 1];
                            right = copyLayout[m][z + 1];

                            if (left == 'A') { countA++;
                            }else if (left == '#'){ countHash++;
                            }else countDot++;

                            if (right == 'A') { countA++;
                            }else if (right == '#'){ countHash++;
                            }else countDot++;

                            //At this point we already know many surrounding seats are occupied or free for one index

                            if (copyLayout[m][z] == 'A' && (countA+countDot)==8) {
                                layout[m - 1][z - 1] = '#';
                            }else{
                                if (copyLayout[m][z] == '#' && countHash >= 4) {
                                    layout[m - 1][z - 1] = 'A';
                                }
                            }
                            countA=0; countHash=0; countDot=0;

                            //At this point we changed one seat if necessary
                        }
                    }
                }
                //Check if the copy and the layout have the same disposition. If they are check = true
                //We assume they are equal
                check = true;

                for (int a = 0; a < layout.length; a++) {
                    for (int b = 0; b < layout[0].length; b++) {
                        if (layout[a][b] != copyLayout[a + 1][b + 1]){
                            check = false;
                            break;
                        }
                    }
                    if(!check) break;
                }
            }
            return layout;
        }
    }
}