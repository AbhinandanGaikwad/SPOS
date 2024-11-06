import java.util.Scanner;

public class SJF {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] processId = new int[n];

        System.out.println("Enter burst time for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            processId[i] = i + 1; // Store process ID
        }

        // Sort burstTime array, and rearrange processId accordingly
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (burstTime[i] > burstTime[j]) {
                    // Swap burstTime
                    int temp = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = temp;

                    // Swap processId to maintain the correct order
                    temp = processId[i];
                    processId[i] = processId[j];
                    processId[j] = temp;
                }
            }
        }

        // Calculate waiting and turnaround times
        waitingTime[0] = 0; // First process has no waiting time
        for (int i = 1; i < n; i++) {
            waitingTime[i] = waitingTime[i - 1] + burstTime[i - 1];
        }

        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = waitingTime[i] + burstTime[i];
        }

        // Display results
        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        float totalWT = 0, totalTAT = 0;
        for (int i = 0; i < n; i++) {
            System.out.println(
                    "P" + processId[i] + "\t\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnAroundTime[i]);
            totalWT += waitingTime[i];
            totalTAT += turnAroundTime[i];
        }

        System.out.println("\nAverage Waiting Time: " + (totalWT / n));
        System.out.println("Average Turnaround Time: " + (totalTAT / n));

        sc.close();
    }
}
