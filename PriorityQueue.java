//OUTLAB 1 by Zach Wadhams

public class PriorityQueue {

    private int end;

    private Job[] jobArray;

    public Job IsNext() { //gets the next job
         return jobArray[1];
    }

    public void Remove() { //removes the top job once it has been completed
        jobArray[1] = jobArray[end - 1];
        end --;
        Sink(1);
    }

    public void Insert(Job job) { //inserts the job into the processor
        jobArray[end] = job;
        SwimUp(end);
        end++;
    }

    private void SwimUp(int k) { //compares child with parent and either swaps them or does nothing

        if (k == 1){ //basecase
            return;
        }

        if (jobArray[k].priority > jobArray[k/2].priority) { //swap
            Job myJob = jobArray[k];
            jobArray[k] = jobArray[k/2];
            jobArray[k/2] = myJob;
            SwimUp(k/2);
        }
    }

    private void Sink(int k) { //compares parent with child and either swaps them or does nothing

        if (k*2 >= end) { //basecase
            return;
        }

        int maxChildIdx = k * 2;

        if (k * 2 + 1 < end) {
            if (jobArray[k * 2].priority < jobArray[k * 2 + 1].priority) {
                maxChildIdx = k * 2 + 1;
            }
        }

        if (jobArray[maxChildIdx].priority > jobArray[k].priority) {
                        Job myJob = jobArray[k];
                        jobArray[k] = jobArray[maxChildIdx];
                        jobArray[maxChildIdx] = myJob;
                        Sink(maxChildIdx);
        }
    }

    public PriorityQueue (int size) {  //controls the size of the array

        jobArray = new Job[size + 1];
        this.end = 1;
    }

    public boolean IsEmpty () { //checks to see if the job is empty
        boolean empty = false;
        if (end == 1) {
            empty = true;
        }
        return empty;
    }











}
