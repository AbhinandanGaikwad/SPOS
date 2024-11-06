import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        System.out.print("Enter time quantum: ");
        int quantum = sc.nextInt();

        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] remainingBurstTime = new int[n];

        System.out.println("Enter burst time for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            remainingBurstTime[i] = burstTime[i];
        }

        int time = 0;
        boolean done;

        do {
            done = true;

            for (int i = 0; i < n; i++) {
                if (remainingBurstTime[i] > 0) {
                    done = false;

                    if (remainingBurstTime[i] > quantum) {
                        time += quantum;
                        remainingBurstTime[i] -= quantum;
                    } else {
                        time += remainingBurstTime[i];
                        waitingTime[i] = time - burstTime[i];
                        remainingBurstTime[i] = 0;
                    }
                }
            }
        } while (!done);

        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = burstTime[i] + waitingTime[i];
        }

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(
                    "P" + (i + 1) + "\t\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnAroundTime[i]);
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
