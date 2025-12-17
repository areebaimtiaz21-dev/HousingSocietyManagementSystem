public class Block {
    private String name;
    private int streets;
	//Derived classes
    private Plot[][] plots;
    private Park[] parks = new Park[1];
    private CommercialMarket market = new CommercialMarket();

    // Constructor for named blocks
    public Block(String name, int streets) {
    this.name = name;
    this.streets = streets;

    // Initialize parks (example: 1 park)
    parks[0] = new Park(20, 30);

    // Create array for plots per street
    int[] plotsPerStreet = new int[streets];
    for (int i = 0; i < streets; i++) {
        plotsPerStreet[i] = (streets * 2) + i; // formula for plots per street
    }

    // Create outer array (each row = one street)
    plots = new Plot[plotsPerStreet.length][];

    // Divide streets by area type
    int resStreets = streets * 60 / 100;  // 60% residential
    int shopStreets = streets * 20 / 100; // 20% shops
    int officeStreets = streets - resStreets - shopStreets; // remainder offices

    // Divide residential further
    int marla5 = resStreets / 3;
    int marla10 = resStreets / 3;
    int kanal1 = resStreets - marla5 - marla10;

    // Set cumulative end indexes
    int marla5End = marla5;
    int marla10End = marla5End + marla10;
    int kanalEnd = marla10End + kanal1;
    int shopEnd = kanalEnd + shopStreets;
    int officeEnd = shopEnd + officeStreets;

    // Fill all streets and plots
    for (int r = 0; r < plotsPerStreet.length; r++) {
        plots[r] = new Plot[plotsPerStreet[r]]; // create plots for each street

        for (int c = 0; c < plotsPerStreet[r]; c++) {
            String ID = String.format("%d-%03d", r + 1, c + 1);
            PlotType type = null;
            String shape = "RECTANGLE";
            double width1 = 0, width2 = 0, depth1 = 0, depth2 = 0, front = 0, back = 0;

            // Assign type by street range
            if (r < marla5End) {
                type = PlotType.RES_5_MARLA;
                shape = "RECTANGLE";
                width1 = 10;
                depth1 = 20;
            } 
            else if (r < marla10End) {
                type = PlotType.RES_10_MARLA;
                shape = "RECTANGLE";
                width1 = 20;
                depth1 = 30;
            } 
            else if (r < kanalEnd) {
                type = PlotType.RES_1_KANAL;
                shape = "TRAPEZOID";
                front = 20;
                back = 20;
                depth1 = 40;
            } 
            else if (r < shopEnd) {
                type = PlotType.COMM_SHOP;
                shape = "RECTANGLE";
                width1 = 20;
                depth1 = 20;
            } 
            else if (r < officeEnd) {
                type = PlotType.COMM_OFFICE;
                shape = "RECTANGLE";
                width1 = 25;
                depth1 = 25;
            }

            // Every 5th plot is Parking
            if ((c + 1) % 5 == 0) {
                type = PlotType.PARKING;
            }

            // Fallback (avoid null crash)
            if (type == null) {
                type = PlotType.RES_5_MARLA;
            }

            // Corner plots: first 3 streets, every 4th plot
            if ((r + 1 <= 3) && (c + 1 == 4)) {
                plots[r][c] = new CornerPlot(ID, type, true, shape,
                        width1, depth1, width2, depth2, front, back);
            } 
            else {
                plots[r][c] = new Plot(ID, type, true, shape,
                        width1, depth1, width2, depth2, front, back);
            }
        }
    }
}

	
    //Overloaded constructor (for predefined blocks)
    public Block(String name) {
        this.name = name;
        this.streets = 5; // default
    }

    public String getName() { return name; }

    // 🔍 Find a plot by ID
    private Plot findPlotByID(String ID) {
        for (int r = 0; r < plots.length; r++) {
            for (int c = 0; c < plots[r].length; c++) {
                if (plots[r][c].getID().equalsIgnoreCase(ID)) {
                    return plots[r][c];
                }
            }
        }
        return null;
    }

    // 🏠 Book a plot
    public boolean bookPlot(String ID) {
        Plot plot = findPlotByID(ID);
        if (plot != null && plot.getAvailability()) {
            plot.setAvailability(false);
            return true;
        }
        return false;
    }
	
	//Cancel a plot
	public boolean cancelPlot(String ID) {
    Plot plot = findPlotByID(ID);
    if (plot != null && !plot.getAvailability()) { // booked plot
        plot.setAvailability(true); // now available again
        return true; // cancellation successful
    }
    return false; // either not found or already available
}


    // 🧾 Display everything in block
    public void displayBlock() {
        for (int r = 0; r < plots.length; r++) {
            for (int c = 0; c < plots[r].length; c++) {
                System.out.println(plots[r][c]);
            }
            System.out.println("-------------------------------------");
        }
        System.out.println("Block Parks:");
        for (Park park : parks) {
            System.out.println(park);
        }
        market.displayMarket();
    }
	public int getTotalPlots() {
    int total = 0;
    for (int i = 0; i < plots.length; i++) {
        total += plots[i].length;
    }
    return total;
}

public int getAvailablePlots() {
    int available = 0;
    for (int i = 0; i < plots.length; i++) {
        for (int j = 0; j < plots[i].length; j++) {
            if (plots[i][j].getAvailability()) {
                available++;
            }
        }
    }
    return available;
}

// ✅ A = Available, X = Booked
public void printStreetLayout() {
    for (int i = 0; i < plots.length; i++) {
        System.out.print("Street " + (i + 1) + ": ");
        for (int j = 0; j < plots[i].length; j++) {
            if (plots[i][j].getAvailability())
                System.out.print("A ");
            else
                System.out.print("X ");
        }
        System.out.println();
    }
}

// ✅ Print all plot details
public void printPlotDetails() {
    for (int i = 0; i < plots.length; i++) {
        for (int j = 0; j < plots[i].length; j++) {
            System.out.println(plots[i][j]);
        }
    }
}

// ✅ Print parks and market info
public void printAmenities() {
    for (int i = 0; i < parks.length; i++) {
        System.out.println(parks[i]);
    }
    market.displayMarket();
}

}