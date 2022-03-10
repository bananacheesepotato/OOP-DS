
public class A0 {

    public static void main(String[] args) {
        System.out.println("Executing method main.");
        int x= 5;
        System.out.println("x is now " + x);
        // assert x == 6;
        System.out.println("The assert statement was not executed");
        int a= 3;

        while (a > 1) {
            a= a + 1;
            a= a - 1;
        }

        System.out.println(a);
    }

}
