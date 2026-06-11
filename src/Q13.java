class Q13 {
    public static void main(String[] args) {
        System.out.println(convertToRoman(40));

    }
    static String convertToRoman(int n) {
        // Arrays to store Roman symbols and their corresponding values
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();
        int i = 0;

        // Iterate through the values and symbols
        while (n > 0) {
            // While the current value is less than or equal to n
            while (values[i] <= n) {
                // Append the corresponding Roman symbol
                roman.append(symbols[i]);
                // Subtract the value from n
                n -= values[i];
            }
            // Move to the next value and symbol
            i++;
        }

        return roman.toString();
    }
}