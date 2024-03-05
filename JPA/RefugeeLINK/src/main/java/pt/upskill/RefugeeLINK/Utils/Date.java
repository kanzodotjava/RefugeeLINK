//package pt.upskill.RefugeeLINK.Utils;
//import java.util.Calendar;
//
///**
// * Represents a date through the day, month and year.
// */
//public class Date implements Comparable<Date>{
//
//        /**
//         * The year of the date.
//         */
//        private int year;
//
//        /**
//         * The month of the date.
//         */
//        private Month month;
//
//        /**
//         * The day of the date.
//         */
//        private int day;
//
//        /**
//         * The default year.
//         */
//        private static final int YEAR_BY_OMISSION = 1;
//
//        /**
//         * The default month.
//         */
//        private static final Month MONTH_BY_OMISSION = Month.JANUARY;
//
//        /**
//         * The default day.
//         */
//        private static final int DIA_POR_OMISSAO = 1;
//
//        /**
//         * Constructs an instance of Date receiving the year, the month and the day.
//         */
//        private static enum DayOfTheWeek {
//
//            /**
//             * The days of the week.
//             */
//            SUNDAY { @Override public String toString() { return "Sunday"; } },
//            MONDAY { @Override public String toString() { return "Monday"; } },
//            TUESDAY {   @Override public String toString() { return "Tuesday"; } },
//            WEDNESDAY {  @Override public String toString() { return "Wednesday"; } },
//            THURSDAY {  @Override public String toString() { return "Thursday"; } },
//            FRIDAY {   @Override public String toString() { return "Friday"; } },
//            SATURDAY {  @Override public String toString() { return "Saturday"; } };
//
//            /**
//             * Returns the designation of the day of the week.
//             * @param orderDayOfTheWeek the order of the day of the week.
//             */
//            public static String dayOfTheWeeksDesignation(int orderDayOfTheWeek) {
//                return DayOfTheWeek.values()[orderDayOfTheWeek].toString();
//            }
//        }
//
//        /**
//         * Constructs an instance of Date receiving the year, the month and the day.
//         */
//        private static enum Month {
//
//            /**
//             * Os meses do ano.
//             */
//            JANUARY(31) {   @Override public String toString() { return "January"; } },
//            FEBRUARY(28) { @Override public String toString() { return "February"; } },
//            MARCH(31) {     @Override public String toString() { return "March"; } },
//            APRIL(30) {     @Override public String toString() { return "April"; } },
//            MAY(31) {      @Override public String toString() { return "May"; } },
//            JUNE(30) {     @Override public String toString() { return "June"; } },
//            JULY(31) {     @Override public String toString() { return "July"; } },
//            AUGUST(31) {    @Override public String toString() { return "August"; } },
//            SEPTEMBER(30) {  @Override public String toString() { return "September"; } },
//            OCTOBER(31) {   @Override public String toString() { return "October"; } },
//            NOVEMBER(30) {  @Override public String toString() { return "November"; } },
//            DECEMBER(31) {  @Override public String toString() { return "December"; } };
//
//            /**
//             * The number of days of the month.
//             */
//            private int numberOfDays;
//
//            /**
//             * Constructs an instance of Month receiving the number of days of the month.
//             *
//             * @param numberOfDays the number of days of the month.
//             */
//            private Month(int numberOfDays) {
//                this.numberOfDays = numberOfDays;
//            }
//
//            /**
//             * Returns the number of days of the month of the year.
//             *
//             * @param year the year.
//             * @return the number of days of the month of the year.
//             */
//            public int numberOfDays(int year) {
//                if (ordinal() == 1 && Date.isYear366(year)) {
//                    return numberOfDays + 1;
//                }
//                return numberOfDays;
//            }
//
//            /**
//             * Returns the designation of the month.
//             *
//             * @param orderMonth the order of the month.
//             * @return the designation of the month.
//             */
//            public static Month getTheMonth(int orderMonth) {
//                return Month.values()[orderMonth - 1];
//            }
//
//        }
//
//
//        /**
//         * Constructs an instance of Date receiving the year, the month and the day.
//         *
//         * @param year the year of the date.
//         * @param month the month of the date.
//         * @param day the day of the date.
//         */
//        public Date(int year, int month, int day) {
//            this.year = year;
//            this.month = Month.getTheMonth(month);
//            this.day = day;
//        }
//
//        /**
//         * Constructs an instance of Date receiving the year, the month and the day.
//         */
//        public Date() {
//            year = YEAR_BY_OMISSION;
//            month = MONTH_BY_OMISSION;
//            day = DIA_POR_OMISSAO;
//
//        }
//
//        /**
//         * Constructs an instance of Date receiving the year, the month and the day.
//         */
//        public Date(Date anotherDate) {
//            year = anotherDate.year;
//            month = anotherDate.month;
//            day = anotherDate.day;
//        }
//
//        /**
//         * Returns the year of the date.
//         *
//         * @return the year of the date.
//         */
//        public int getYear() {
//            return year;
//        }
//
//        /**
//         * Returns the month of the date.
//         *
//         * @return the month of the date.
//         */
//        public int getMonth() {
//            return month.ordinal()+1;
//        }
//
//        /**
//         * Returns the day of the date.
//         *
//         * @return the day of the date.
//         */
//        public int getDay() {
//            return day;
//        }
//
//        /**
//         * Returns the date in the format: day of the week, day of the month of month of year.
//         *
//         * @param year the year of the date.
//         * @param month the month of the date.
//         * @param day the day of the date.
//         */
//        public final void setData(int year, int month, int day) {
//            this.year = year;
//            this.month = Month.getTheMonth(month);
//            this.day = day;
//        }
//
//        /**
//         * Returns the date in the format: day of the week, day of the month of month of year.
//         *
//         *
//         * @return the date in the format: day of the week, day of the month of month of year.
//         */
//        @Override
//        public String toString() {
//            return String.format("%s, %d de %s de %d", dayOfTheWeek(), day, month, year);
//        }
//
//        /**
//         * Returns the date in the format: year/month/day.
//         *
//         */
//        public String toYearMonthDay() {
//            return String.format("%04d/%02d/%02d", year, month.ordinal()+1, day);
//        }
//
//        /**
//         * Compara a data com o objeto recebido.
//         *
//         * @param anotherObject o objeto a comparar com a data.
//         * @return true se o objeto recebido representar uma data equivalente à
//         *         data. Caso contrário, retorna false.
//         */
//        @Override
//        public boolean equals(Object anotherObject) {
//            if (this == anotherObject) {
//                return true;
//            }
//            if (anotherObject == null || getClass() != anotherObject.getClass()) {
//                return false;
//            }
//            Date anotherDate = (Date) anotherObject;
//            return year == anotherDate.year && month.equals(anotherDate.month)
//                    && day == anotherDate.day;
//        }
//
//
//    /**
//     * Compares the date with the object received.
//     * @param otherDate the object to be compared.
//     * @return
//     */
//    @Override
//    public int compareTo(Date otherDate) {
//            return (otherDate.isBigger(this)) ? -1 : (isBigger(otherDate)) ? 1 : 0;
//        }
//
//        /**
//         *
//         *
//         * @return the day of the week of the date.
//         */
//        public String dayOfTheWeek() {
//            int totalDays = countDays();
//            totalDays = totalDays % 7;
//
//            return DayOfTheWeek.dayOfTheWeeksDesignation(totalDays);
//        }
//
//
//        /**
//         * Devolve true se a data for maior do que a data recebida por parâmetro. Se
//         * a data for menor ou igual à data recebida por parâmetro, devolve false.
//         *
//         * @param anotherDate a outra data com a qual se compara a data.
//         * @return true se a data for maior do que a data recebida por parâmetro,
//         *         caso contrário devolve false.
//         */
//        public boolean isBigger(Date anotherDate) {
//            int totalDays = countDays();
//            int totalDays1 = anotherDate.countDays();
//
//            return totalDays > totalDays1;
//        }
//
//    /**
//     * Returns the difference in number of days between the date and the date received as
//     * parameter.
//     *
//     * @param anotherDate the other date with which to compare the date to calculate
//     *                    the difference in number of days.
//     * @return difference in number of days between the date and the date received as
//     *         parameter.
//     */
//
//    public int diferenca(Date anotherDate) {
//            int totalDays = countDays();
//            int totalDays1 = anotherDate.countDays();
//
//            return Math.abs(totalDays - totalDays1);
//        }
//
//    /**
//     * Returns the difference in number of days between the date and the date received as parameter.
//     *
//     * @param year the year of the date with which to compare the date to calculate the
//     *             difference in number of days.
//     * @param month the month of the date with which to compare the date to calculate the
//     *              difference in number of days.
//     * @param day the day of the date with which to compare the date to calculate the
//     *            difference in number of days.
//     * @return difference in number of days between the date and the date received as
//     *         parameter with year, month, and day.
//     */
//
//    public int dif(int year, int month, int day) {
//            int totalDays = countDays();
//            Date anotherDate = new Date(year, month, day);
//            int totalDays1 = anotherDate.countDays();
//
//            return Math.abs(totalDays - totalDays1);
//        }
//
//        /**
//         * Returns true if the year passed by parameter is a leap year, otherwise
//         *
//         *
//         * @param year the year to be checked.
//         * @return true if the year passed by parameter is a leap year, otherwise
//         *
//         */
//        public static boolean isYear366(int year) {
//            return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
//        }
//
//        /**
//         * Returns the current date of the system.
//         *
//         * @return the current date of the system.
//         */
//        public static Date actualDate() {
//            Calendar today = Calendar.getInstance();
//            int year = today.get(Calendar.YEAR);
//            int month = today.get(Calendar.MONTH) + 1;
//            int day = today.get(Calendar.DAY_OF_MONTH);
//            return new Date(year, month, day);
//        }
//
//        /**
//         * Returns the number of days of the date.
//         *
//         * @return the number of days of the date.
//         */
//        private int countDays() {
//            int totalDays = 0;
//
//            for (int i = 1; i < year; i++) {
//                totalDays += isYear366(i) ? 366 : 365;
//            }
//            for (int i = 1; i < month.ordinal()+1; i++) {
//                totalDays += Month.getTheMonth(i).numberOfDays(year);
//            }
//            totalDays += day;
//
//            return totalDays;
//        }
//    }
//
