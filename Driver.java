import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;



public class Driver {
    public static void main(String [] args) throws Exception {
        //Polynomial p = new Polynomial();
       // System.out.println(p.evaluate(3));
        double [] c1 = {6,0,0,5};
        int [] p1 = {2, 1, 3, 4};
        Polynomial poly1 = new Polynomial(c1, p1);
        double [] c2 = {0,-2,0,0,-9};
        int [] p2 = {7, 4, 2, 2, 0};
        Polynomial poly2 = new Polynomial(c2, p2);
        Polynomial s = poly1.add(poly2);
        Polynomial q = poly1.multiply(poly2);


        File in_file = new File("text.txt");
        Polynomial p_file = new Polynomial(in_file);
        
        File in_file2 = new File("text2.txt");
        Polynomial p_file_2 = new Polynomial(in_file2);

        Polynomial result = p_file_2.multiply(p_file);
        result.saveToFile("text3.txt");

        //System.out.println("s(0.1) = " + q.evaluate(0.1));

        for(int i = 0; i < p_file.powers.length; i++) {
            System.out.print(p_file.coefficients[i]);
            System.out.print(" ");
            System.out.print( p_file.powers[i]);
            System.out.println(" ");
        }
        // System.out.println();        
        // // if(s.hasRoot(1))
        //     System.out.println("1 is a root of s");
        // else
        //     System.out.println("1 is not a root of s");



    }
}