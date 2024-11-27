package e1;

public class DateUtilities {
    /**
     * Indicates whether a year is a leap year . A leap year is divisible by 4,
     * unless it is divisible by 100 , in which case it must be divisible by 400
     * in order to be considered a leap year (e.g., 1900 is not a leap year ,
     * but 2000 is) = > See the JUnit seminar for an example .
     *
     * @param year the given year
     * @return True if the given year is a leap year , false otherwise .
     */
    public static boolean isLeap(int year) {

        boolean result = false;

        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    result = true;
                    return result;
                }
                else {
                    return result;
                }
            }
            else {
                result = true;
                return result;
            }
        }
        else {
            return result;
        }
    }


    /**
     * Indicates the number of days of a given month . As the number of days in
     * the month of February depends on the year , it is also necessary to pass
     * the year as an argument .
     *
     * @param month The given month
     * @param year  The given year
     * @return The number of days of that month in that year .
     * @throws IllegalArgumentException if the month is not valid .
     */
    public static int numberOfDays(int month, int year) {

        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12:
                return 31;

            case 2:
                if (isLeap(year)) return 29;
                else return 28;

            case 4, 6, 9, 11:
                return 30;

            default:
                throw new IllegalArgumentException();
        }
    }


    /**
     * The ISO date format is a standard format that displays the dates
     * starting with the year , followed by the month and the day , i.e. ,
     * "YYYY -MM -DD ". For example , in that format the text " July 28 , 2006"
     * would be represented as "2006-07-28".
     * The " convertToISO " method converts a date in the " Month DD , AAAA "
     * format to its ISO representation . For simplicity , let us assume that
     * the values are correctly formatted even if the date is invalid
     * (e.g., " February 31 , 2006" is correctly formatted but it is not a valid date )
     *
     * @param dateText Date in textual format ( USA style ).
     * @return A string with the given date in ISO format .
     */

    public static String convertToISODate(String dateText) {
        String[] parts = dateText.split(" ");
        String[] sday = parts[1].split(",");

        String month = switch (parts[0]) {
            case "January" -> "-01";
            case "February" -> "-02";
            case "March" -> "-03";
            case "April" -> "-04";
            case "May" -> "-05";
            case "June" -> "-06";
            case "July" -> "-07";
            case "August" -> "-08";
            case "September" -> "-09";
            case "October" -> "-10";
            case "November" -> "-11";
            case "December" -> "-12";
            default -> null;
        };
        return parts[2] + month + "-" + sday[0];
    }


    /**
     * Given a String representing an ISO - formatted date , the methods checks
     * its validity . This includes checking for non - valid characters , erroneous
     * string lengths , and the validity of the date itself (i.e. , checking the
     * number of days of the month ).
     *
     * @param ISODate A date in ISO format
     * @return True if the ISO - formatted date is a valid date , False otherwise .
     */

    public static boolean checkISODate(String ISODate) {

        boolean result = true;
        String[] parts = ISODate.split("-");

        String sISODate = parts[0].concat(parts[1]).concat(parts[2]);

        //Check for invalid length
        if (ISODate.length() != 10) {
            result = false;
            return result;
        }
        else{
            //Check for invalid characters
            if (!sISODate.matches("[0-9]+")) {
                result = false;
                return result;
            } else {
                //Check for invalid month
                if (Integer.parseInt(parts[1]) > 12) {
                    result = false;
                    return result;
                } else {
                    //If the year is leap and the month is February, we check if there are more than 29 days
                    if (isLeap(Integer.parseInt(parts[0])) && parts[1].equals("02")) {
                        if (Integer.parseInt(parts[2]) > 29) {
                            result = false;
                            return result;
                        }
                    } else {
                        //Check that the day number is correct
                        if (numberOfDays(Integer.parseInt(parts[1]), Integer.parseInt(parts[0])) < Integer.parseInt(parts[2])) {
                            result = false;
                            return result;
                        }
                    }
                }
            }
            return result;
        }
    }
}