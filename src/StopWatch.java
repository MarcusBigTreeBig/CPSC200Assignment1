/**
 * StopWatch class
 * The time is measured in nanoseconds
 * Can be started, stopped, and reset
 * Start, stop, and reset all return this
 * Can check if the StopWatch is currently running
 *
 */
public class StopWatch {

    private boolean running;
    private double startTime;//used when the stopwatch is running
    private double currentTime;//used when the stopwatch is stopped

    /**
     * sets the time to 0 and does not start
     */
    public StopWatch () {
        running = false;
        startTime = 0;
        currentTime = 0;
    }

    /**
     *
     * @return the time counted since start
     */
    public double elapsed(){
        if (isRunning()) {
            return System.nanoTime() - startTime;
        }else{
            return currentTime;
        }
    }
    public boolean isRunning(){return running;}

    /**
     * starts the clock
     *
     * @return this
     */
    public StopWatch start() {
        if (!isRunning()) {
            running = true;
            startTime = System.nanoTime();
        }
        return this;
    }

    /**
     * stops the clock
     *
     * @return this
     */
    public StopWatch stop() {
        if (isRunning()) {
            currentTime = elapsed();
            running = false;
        }
        return this;
    }

    /**
     * resets the clock to 0
     *
     * @return this
     */
    public StopWatch reset(){
        if (isRunning()) {
            startTime = System.nanoTime();
        }else{
            currentTime = 0;
        }
        return this;
    }
}
