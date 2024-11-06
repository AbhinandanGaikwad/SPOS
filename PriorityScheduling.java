import java.util.Scanner;

public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] burstTime = new int[n];
        int[] priority = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] processId = new int[n]; // To keep track of original process IDs

        System.out.println("Enter burst time and priority for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + " Burst Time: ");
            burstTime[i] = sc.nextInt();
            System.out.print("Process " + (i + 1) + " Priority (lower number = higher priority): ");
            priority[i] = sc.nextInt();
            processId[i] = i + 1; // Storing process IDs
        }

        // Sort processes by priority (lower number = higher priority)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (priority[i] > priority[j]) {
                    // Swap priority
                    int temp = priority[i];
                    priority[i] = priority[j];
                    priority[j] = temp;

                    // Swap burstTime
                    temp = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = temp;

                    // Swap process ID
                    temp = processId[i];
                    processId[i] = processId[j];
                    processId[j] = temp;
                }
            }
        }

        // Calculate waiting time and turnaround time
        waitingTime[0] = 0; // First process has no waiting time
        for (int i = 1; i < n; i++) {
            waitingTime[i] = waitingTime[i - 1] + burstTime[i - 1];
        }
        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = waitingTime[i] + burstTime[i];
        }

        // Display results
        System.out.println("\nProcess ID\tPriority\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + processId[i] + "\t\t" + priority[i] + "\t\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnAroundTime[i]);
        }

        // Calculate and display average waiting and turnaround times
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
