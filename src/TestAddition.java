import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

/**
 * Program intended to find the asymptotic complexity of BigInteger.add(BigInteger n)
 * Times several trials and takes the average time for a range of sizes of BigInteger numbers
 * Outputs the numbers sizes in bits, and the running times in ms to separate txt files
 */

public class TestAddition {
    public static void main (String[] args) throws IOException {
        Random rand = new Random();
        StopWatch clock = new StopWatch();
        int increment = 1000000;//also the first data point
        int dataPoints = 100;
        int trialsPerSize = 30;
        BigInteger n1;
        BigInteger n2;
        FileWriter sizeWriter = new FileWriter("AdditionSizes.txt");
        FileWriter timeWriter = new FileWriter("AdditionTimes.txt");
        System.out.println("Size, Time(ms)");
        for (int i = increment; i <= dataPoints*increment; i += increment) {//each desired data point
            clock.reset();
            for (int j = 0; j < trialsPerSize; j++) {//running tests for each data point
                do {
                    /*
                    https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html
                    The constructor used is one of 2 constructors that gives a random big integer as desired for testing the addition of big integers.
                    The other constructor randomly produces an integer that is likely prime, which prime numbers are not helpful for measuring the addition of big integers,
                    and reduces the randomness.
                     */
                    n1 = new BigInteger(i, rand);
                }while (n1.bitLength() != i);//first bit can be generated as 0, this ensures that does not happen
                do {
                    n2 = new BigInteger(i, rand);
                }while (n2.bitLength() != i);
                clock.start();
                n1.add(n2);
                clock.stop();
            }
            System.out.println(i + ", " + clock.elapsed()*1000/trialsPerSize);
            sizeWriter.write(i + "\n");
            timeWriter.write(clock.elapsed()*1000/trialsPerSize + "\n");
        }
        sizeWriter.close();
        timeWriter.close();
    }
}
