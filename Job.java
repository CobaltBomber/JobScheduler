//OUTLAB 1 by Zach Wadhams

public class Job {

    public int jobNumber;
    public int priority;
    public int arrivalTime;
    public int duration;
    public int timeRan = 0;
    public int waitTime = -1;
    public int startTime = -1;

    public Job(int jobNumber, int priority, int arrivalTime, int duration) { //constructor for job data type

        this.jobNumber =  jobNumber;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.duration = duration;

    }

    public String toString() { //makes a special to string method

        String myString = "" + jobNumber + " " + priority + " " + arrivalTime
                + " " + duration;

        return myString;

    }




}
