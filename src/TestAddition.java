import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

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
