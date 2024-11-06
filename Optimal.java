import java.util.Scanner;

public class Optimal {

    // Function to implement Optimal Page Replacement
    public static void optimalPageReplacement(int frames[], int f, int pages[], int n) {
        int[] pageFrames = new int[f]; // To store the pages in frames
        int pageFaults = 0; // To count page faults
        int pagePointer = 0; // To keep track of the next frame to fill initially

        // Initialize frames with -1 (empty)
        for (int i = 0; i < f; i++) {
            pageFrames[i] = -1;
        }

        // Process each page
        for (int i = 0; i < n; i++) {
            int currentPage = pages[i];
            boolean pageHit = false;

            // Check if the page is already in one of the frames (Page Hit)
            for (int j = 0; j < f; j++) {
                if (pageFrames[j] == currentPage) {
                    pageHit = true;
                    break;
                }
            }

            // If it is a page fault
            if (!pageHit) {
                // Check for an empty frame
                if (pagePointer < f) {
                    pageFrames[pagePointer] = currentPage;
                    pagePointer++;
                } else {
                    // If no empty frame, replace the optimal page
                    int farthest = -1;
                    int indexToReplace = -1;
                    for (int j = 0; j < f; j++) {
                        int nextUse = -1;
                        for (int k = i + 1; k < n; k++) {
                            if (pageFrames[j] == pages[k]) {
                                nextUse = k;
                                break;
                            }
                        }
                        // If the page is not found in the future, it can be replaced
                        if (nextUse == -1) {
                            indexToReplace = j;
                            break;
                        }
                        // Find the page that is used farthest in the future
                        if (nextUse > farthest) {
                            farthest = nextUse;
                            indexToReplace = j;
                        }
                    }
                    // Replace the page in the frame
                    pageFrames[indexToReplace] = currentPage;
                }
                pageFaults++;
            }

            // Print the current state of the frames
            System.out.print("Frames: ");
            for (int j = 0; j < f; j++) {
                if (pageFrames[j] != -1) {
                    System.out.print(pageFrames[j] + " ");
                }
            }
            System.out.println();
        }

        // Print the results
        int pageHits = n - pageFaults;
        System.out.println("\nPage Faults: " + pageFaults);
        System.out.println("Page Hits: " + pageHits);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of frames
        System.out.print("Enter the number of frames: ");
        int f = scanner.nextInt();

        // Input number of pages
        System.out.print("Enter the number of pages: ");
        int n = scanner.nextInt();

        // Input the pages
        int[] pages = new int[n];
        System.out.println("Enter the page numbers: ");
        for (int i = 0; i < n; i++) {
            pages[i] = scanner.nextInt();
        }

        // Call the Optimal page replacement function
        optimalPageReplacement(pages, f, pages, n);

        scanner.close();
    }
}
