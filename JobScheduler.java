//OUTLAB 1 by Zach Wadhams

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class JobScheduler {

    public static void main(String[] args) {

        In readIn = new In(args[0]);  //pulls in the text file from the arguments
                                      //i tested it with the provided text file and
                                      //the program worked

        String input[] = readIn.readAllLines();
        Job myJobs[] = new Job[input.length];

        for (int i = 0; i < input.length; i ++) { //turns the string input to type int
            String data[] = input[i].split(" ");
            int jobNumber = Integer.parseInt(data[0]);
            int priority = Integer.parseInt(data[1]);
            int arrivalTime = Integer.parseInt(data[2]);
            int duration = Integer.parseInt(data[3]);

            Job newJob = new Job(jobNumber, priority, arrivalTime, duration);  //initializes the job data type

            myJobs[i] = newJob;
        }

        StdOut.println("Unsorted Input:");
        StdOut.println(Arrays.toString(myJobs));

        for (int i = 0; i < myJobs.length; i++) {  //sorts the array of type job by arrival time
            int j = i;

            while (j > 0 && myJobs[j - 1].arrivalTime > myJobs[j].arrivalTime) {
                Job swap = myJobs[j];
                myJobs[j] = myJobs[j - 1];
                myJobs[j - 1] = swap;
                j--;
            }
        }

        StdOut.println("Sorted Input:");
        StdOut.println(Arrays.toString(myJobs));

        StdOut.println("Running Processor:");
        //beginning of processor code

        int currentTime = 0;
        int nextUp = 0;
        Job currentJob = null;

        PriorityQueue myQueue = new PriorityQueue(myJobs.length);

        while (currentJob != null || nextUp < myJobs.length) {
            if (nextUp < myJobs.length && currentTime == myJobs[nextUp].arrivalTime) { //inserting each job
                myQueue.Insert(myJobs[nextUp]);
                nextUp++;
            }

            if (currentJob != null && currentJob.duration == currentJob.timeRan) { //ending the jobs
                currentJob = null;
                myQueue.Remove();
            }

            if (currentJob == null && !myQueue.IsEmpty()) { //running each job
                currentJob = myQueue.IsNext();
                if (currentJob.waitTime == -1) {
                    currentJob.waitTime = currentTime - currentJob.arrivalTime;
                }
                if (currentJob.startTime == -1) {
                    currentJob.startTime = currentTime;
                }
            }

            if (currentJob != null && currentJob.priority < myQueue.IsNext().priority) { //switch to high priority job
                currentJob = myQueue.IsNext();
                if (currentJob.waitTime == -1) {
                    currentJob.waitTime = currentTime - currentJob.arrivalTime;
                }
                if (currentJob.startTime == -1) {
                    currentJob.startTime = currentTime;
                }
            }

            //store how long each job had been running

            if (currentJob != null) { //prints out current job running with additional info
                StdOut.println("Time " + currentTime + ", Job Running: " + currentJob.jobNumber +
                                       ", Waiting time: " + currentJob.waitTime + ", Execution time: "
                                       + (currentTime - currentJob.startTime));
            }
            else { //prints this if no job is running
                StdOut.println("Time " + currentTime + ", No Job is Currently Running");
            }

            currentTime++;
            if (currentJob != null) {
                currentJob.timeRan++;
            }
        }

        //In Order for additional processors, you would have to dupliucate the above while loop
        //and check to be sure that it does not run the same process twice


    }
}
