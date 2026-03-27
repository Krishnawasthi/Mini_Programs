public class PalindromeNumber {

    public static void main(String[] args) {

        int n = 121;
        int original = n;
        int reverse = 0;

        while (n > 0) {

            int digit = n % 10;
            reverse = reverse * 10 + digit;

            n = n / 10;
        }

        if (reverse == original) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not a Palindrome");
        }
    }
}