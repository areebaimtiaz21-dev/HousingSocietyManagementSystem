public class HousingSociety {
    private Block[] blocks = new Block[3];
    private int count = 0;  // tracks how many blocks are currently stored
    private String name;    // society name

    // Constructor: preload default blocks
    public HousingSociety(String name, int streets) {
        this.name = name; // FIX: assign society name

        String[] pre_blocks = {"A", "B", "C"};

        // Preload default blocks
        for (int i = 0; i < pre_blocks.length; i++) {
            blocks[i] = new Block(pre_blocks[i], streets); 
            count++;
        }

        // Removed: addBlock(name, streets); // society name should not be a block
    }

    // Array resizing if blocks[] is full
    private void resizeArray() {
        Block[] newArray = new Block[blocks.length * 2 + 1];
        for (int i = 0; i < blocks.length; i++) {
            newArray[i] = blocks[i];
        }
        blocks = newArray;
    }

    // Add new block to society
    public void addBlock(String blockName, int streets) {
        if (count == blocks.length) {
            resizeArray();
        }
        blocks[count++] = new Block(blockName, streets);
        System.out.println("New block added: " + blockName);
    }

    // Find block by name
    public Block findBlock(String blockName) {
        for (int i = 0; i < count; i++) {
            if (blockName.equalsIgnoreCase(blocks[i].getName())) {
                System.out.println("Block found successfully: " + blockName);
                return blocks[i];
            }
        }
        System.out.println("Block not found: " + blockName);
        return null;
    }

    // Book a plot inside a block
    public void bookPlot(String blockName, String plotID) {
        Block block = findBlock(blockName);
        if (block != null) {
            if (block.bookPlot(plotID)) {
                System.out.println("Plot booked successfully!");
            } else {
                System.out.println("Plot not available or not found.");
            }
        } else {
            System.out.println("Block not found!");
        }
    }

    // Cancel a plot inside a block
    public void cancelPlot(String blockName, String plotID) {
        Block block = findBlock(blockName);
        if (block != null) {
            if (block.cancelPlot(plotID)) {
                System.out.println("Plot Booking cancelled successfully");
            } else
                System.out.println("Plot not available or not found.");
        } else {
            System.out.println("Block not found!");
        }
    }

    // Getter for society name
    public String getName() {
        return name;
    }

    // Compute totals
    public void computeTotals() {
        int total = 0, available = 0;
        for (int i = 0; i < count; i++) {
            total += blocks[i].getTotalPlots();
            available += blocks[i].getAvailablePlots();
        }
        System.out.println("Society Summary Report: " + name);
        System.out.println("Total Plots: " + total);
        System.out.println("Available Plots: " + available);
        System.out.println("Booked Plots: " + (total - available));
    }

    // Print detailed report
    public void printReports() {
        System.out.println("\n----------------- SOCIETY REPORT ------------------");
        for (int i = 0; i < count; i++) {
            Block b = blocks[i];
            System.out.printf("\nBlock: %s | Total Plots: %d | Available: %d\n",
                    b.getName(), b.getTotalPlots(), b.getAvailablePlots());

            System.out.println("---------------------------------------------------------");
            System.out.println("Street Layout (A = Available, X = Booked):");
            b.printStreetLayout();

            System.out.println("\nPlot Details:");
            b.printPlotDetails();

            System.out.println("\nAmenities:");
            b.printAmenities();
            System.out.println("-------------------------------------------");
        }
    }
}