import java.util.Scanner;

public class LRU {

    // Function to implement LRU Page Replacement
    public static void lruPageReplacement(int frames[], int f, int pages[], int n) {
        int[] pageFrames = new int[f]; // To store the pages in frames
        int pageFaults = 0; // To count page faults
        int[] lastUsed = new int[f]; // To store the last used time for each frame

        // Initialize frames with -1 (empty)
        for (int i = 0; i < f; i++) {
            pageFrames[i] = -1;
            lastUsed[i] = -1;
        }

        // Process each page
        for (int i = 0; i < n; i++) {
            int currentPage = pages[i];
            boolean pageHit = false;

            // Check if the page is already in one of the frames (Page Hit)
            for (int j = 0; j < f; j++) {
                if (pageFrames[j] == currentPage) {
                    pageHit = true;
                    lastUsed[j] = i; // Update the last used time
                    break;
                }
            }

            // If it is a page fault
            if (!pageHit) {
                int leastRecentlyUsed = 0;
                for (int j = 1; j < f; j++) {
                    // Find the least recently used frame
                    if (lastUsed[j] < lastUsed[leastRecentlyUsed]) {
                        leastRecentlyUsed = j;
                    }
                }

                // Replace the least recently used page with the new page
                pageFrames[leastRecentlyUsed] = currentPage;
                lastUsed[leastRecentlyUsed] = i;
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

        // Call the LRU page replacement function
        lruPageReplacement(pages, f, pages, n);

        scanner.close();
    }
}
