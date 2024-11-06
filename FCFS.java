import java.util.Scanner;

public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of processes should be greater than zero.");
            return; // Exit if invalid input
        }

        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];

        // Input burst time for each process
        System.out.println("Enter burst time for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
        }

        // Waiting time for the first process is 0
        waitingTime[0] = 0;

        // Calculate waiting time for other processes
        for (int i = 1; i < n; i++) {
            waitingTime[i] = waitingTime[i - 1] + burstTime[i - 1];
        }

        // Calculate turnaround time for each process
        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = waitingTime[i] + burstTime[i];
        }

        // Output the process information
        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(
                    "P" + (i + 1) + "\t\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnAroundTime[i]);
        }

        // Calculate and output average waiting time and turnaround time
        float totalWT = 0, totalTAT = 0;
        for (int i = 0; i < n; i++) {
            totalWT += waitingTime[i];
            totalTAT += turnAroundTime[i];
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / n);

        sc.close();
    }
}
