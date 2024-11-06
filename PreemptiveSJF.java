import java.util.Scanner;

public class PreemptiveSJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];

        System.out.println("Enter arrival time and burst time for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + " Arrival Time: ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Process " + (i + 1) + " Burst Time: ");
            burstTime[i] = sc.nextInt();
            remainingTime[i] = burstTime[i]; // Initial remaining time is the burst time
        }

        int completed = 0, time = 0, shortest = -1;
        boolean foundProcess;

        while (completed < n) {
            foundProcess = false;
            shortest = -1;

            // Find the process with the smallest remaining burst time that has arrived
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= time && remainingTime[i] > 0) {
                    if (shortest == -1 || remainingTime[i] < remainingTime[shortest]) {
                        shortest = i;
                        foundProcess = true;
                    }
                }
            }

            if (!foundProcess) {
                time++; // Increment time if no process is ready
                continue;
            }

            remainingTime[shortest]--; // Execute one unit of time for the shortest process
            time++; // Increment time

            // If the process is finished, calculate waiting and turnaround times
            if (remainingTime[shortest] == 0) {
                completed++;
                int finishTime = time;
                turnAroundTime[shortest] = finishTime - arrivalTime[shortest];
                waitingTime[shortest] = turnAroundTime[shortest] - burstTime[shortest];
            }
        }

        System.out.println("\nProcess\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.printf("P%d\t\t%d\t\t%d\t\t%d\t\t%d\n", (i + 1), arrivalTime[i], burstTime[i], waitingTime[i],
                    turnAroundTime[i]);
        }

        float totalWT = 0, totalTAT = 0;
        for (int i = 0; i < n; i++) {
            totalWT += waitingTime[i];
            totalTAT += turnAroundTime[i];
        }
        System.out.println("\nAverage Waiting Time: " + (totalWT / n));
        System.out.println("Average Turnaround Time: " + (totalTAT / n));

        sc.close();
    }
}
