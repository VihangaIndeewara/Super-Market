public class Demo {
    public static void main(String[] args) {
        String i= "OR001";

        String a =i.substring(0,2);
        String b= i.substring(2);
        int q=Integer.valueOf(b);
        q++;

        System.out.println(a);
        System.out.println(q);

    }
}
