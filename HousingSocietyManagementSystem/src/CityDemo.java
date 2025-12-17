public class CityDemo {
    public static void main(String[] args) {
        System.out.println("City Housing Management System Started\n");

          CityHousing city = new CityHousing();

        System.out.println("\n--- Adding a New Society ---");
        city.addSociety("DHA Phase 6", 4);

        
        System.out.println("\n--- Booking Plots ---");
        city.bookPlot("LDA Avenue 1", "A", "1-001");  // Block A, plot 1-001
        city.bookPlot("LDA Avenue 2", "B", "2-005");  // Block B, plot 2-005


       System.out.println("\n--- Cancelling a Booking ---");
        city.cancelPlot("LDA Avenue 1", "A", "1-001");

        
        System.out.println("\n--- Computing City Totals ---");
        city.computeCityTotals();

        System.out.println("\n--- Removing a Society ---");
        city.removeSociety("DHA Phase 6");

        
        System.out.println("\n--- Final City Report ---");
        city.printCityReport();

        System.out.println("\nProgram finished successfully!");
    }
}



		
		