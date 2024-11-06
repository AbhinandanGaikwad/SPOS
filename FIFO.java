import java.util.Scanner;

public class FIFO {

    // Function to implement FIFO Page Replacement
    public static void fifoPageReplacement(int frames[], int f, int pages[], int n) {
        int[] pageFrames = new int[f]; // To store the pages in frames
        int pageFaults = 0; // To count page faults
        int pagePointer = 0; // To keep track of where to replace the page (FIFO)

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
                // Replace the page at the current pointer (FIFO logic)
                pageFrames[pagePointer] = currentPage;
                pagePointer = (pagePointer + 1) % f; // Move the pointer in a circular manner
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

        // Call the FIFO page replacement function
        fifoPageReplacement(pages, f, pages, n);

        scanner.close();
    }
}
