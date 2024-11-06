import java.util.Scanner;

public class WorstFit{
	public static void worstfit(int blockSize[],int m,int processSize[],int n){
		int allocation[] = new int[n];

		for (int i = 0;i< allocation.length ; i++)
			allocation[i]=-1;

		for(int i = 0;i < n;i++){

			int worstId = -1;
			for(int j = 0;j < m; j++){
				if(blockSize[j] >= processSize[i]){
					if(worstId == -1)
						worstId = j;
					else if(blockSize[worstId] < blockSize[j])
						worstId = j;
				}
			}

			if(worstId != -1){
				
				allocation[i] = worstId;

				blockSize[worstId] -= processSize[i];
			}
		}

		System.out.println("\nProcess Number \tProcess Size \tBlock Number");
		for(int i = 0; i < n ; i++){
			System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
			if(allocation[i] !=-1)
				System.out.print(allocation[i] + 1);
			else
				System.out.print("Not Allocated");
			System.out.println();
		}
	}

	public static void main(String[] args){
			Scanner scanner = new Scanner(System.in);

			System.out.print("Enter the number of memory blocks: ");
			int m = scanner.nextInt();

			int[] blockSize = new int[m];
			System.out.println("Enter the sizes of " + m + " memory blocks:");
			for(int i = 0; i < m ; i++){
				System.out.print("Block " + ( i + 1 ) + ": ");
				blockSize[i] = scanner.nextInt();
			}

			System.out.print("Enter the number of processes: ");
			int n = scanner.nextInt();

			int[] processSize = new int[n];
			System.out.println("Enter the sizes of " + n + " processes:");
			for(int i = 0; i < n ; i++){
				System.out.print("Process " + ( i + 1 ) + ": ");
				processSize[i] = scanner.nextInt();
			}

			worstfit(blockSize,m,processSize,n);

			scanner.close();

	}
}