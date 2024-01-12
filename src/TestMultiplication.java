import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

/**
 * Program intended to find the asymptotic complexity of BigInteger.multiply(BigInteger n)
 * Times several trials and takes the average time for a range of sizes of BigInteger numbers
 * Outputs the numbers sizes in bits, and the running times in nanoseconds to separate txt files
 *
 * The data produced by this program shows that the time complexity for multiplication of BigInteger's is more complex than linear, but polynomial,
 * most likely quadratic as the curvature is low
 */

public class TestMultiplication {
    public static void main (String[] args) throws IOException {
        Random rand = new Random();
        StopWatch clock = new StopWatch();

        //the parameters for how much data is gathered
        int increment = 100000;//also the first data point
        int dataPoints = 100;
        int trialsPerSize = 30;

        BigInteger n1;
        BigInteger n2;
        FileWriter sizeWriter = new FileWriter("MultiplicationSizes.txt");
        FileWriter timeWriter = new FileWriter("MultiplicationTimes.txt");
        System.out.println("Size, Time(ms)");
        for (int i = increment; i <= dataPoints*increment; i += increment) {//each desired data point
            clock.reset();
            for (int j = 0; j < trialsPerSize; j++) {//running tests for each data point
                do {
                    n1 = new BigInteger(i, rand);
                }while (n1.bitLength() != i);//first bit can be generated as 0, this ensures that does not happen
                do {
                    n2 = new BigInteger(i, rand);
                }while (n2.bitLength() != i);
                clock.start();
                n1.multiply(n2);
                clock.stop();
            }
            System.out.println(i + ", " + clock.elapsed()*Math.pow(10, 9)/trialsPerSize);
            sizeWriter.write(i + "\n");
            timeWriter.write(clock.elapsed()*Math.pow(10, 9)/trialsPerSize + "\n");
        }
        sizeWriter.close();
        timeWriter.close();
    }
}
