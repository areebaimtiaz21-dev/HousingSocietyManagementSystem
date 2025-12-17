public class CityHousing {
    private HousingSociety[] societies;
    private int count; // tracks active societies

        public CityHousing() {
        societies = new HousingSociety[2];
        societies[0] = new HousingSociety("LDA Avenue 1", 5);
        societies[1] = new HousingSociety("LDA Avenue 2", 5);
        count = 2;
    }

        public void addSociety(String name, int streets) {
        if (count == societies.length) {
            HousingSociety[] temp = new HousingSociety[societies.length * 2 + 1];
            for (int i = 0; i < societies.length; i++) {
                temp[i] = societies[i];
            }
            societies = temp;
        }
        societies[count++] = new HousingSociety(name, streets);
        System.out.println("Society '" + name + "' added successfully!");
    }

        public void removeSociety(String name) {
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (societies[i] != null && societies[i].getName().equalsIgnoreCase(name)) {
                // shift all next elements left
                for (int j = i; j < count - 1; j++) {
                    societies[j] = societies[j + 1];
                }
                societies[count - 1] = null;
                count--;
                found = true;
                break;
            }
        }

        if (found)
            System.out.println("Society '" + name + "' removed successfully!");
        else
            System.out.println("Society '" + name + "' not found.");
    }

       public HousingSociety findSociety(String keyword) {
        for (int i = 0; i < count; i++) {
            if (societies[i].getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("Society found: " + societies[i].getName());
                return societies[i];
            }
        }
        System.out.println("Society '" + keyword + "' not found.");
        return null;
    }

    
    public void bookPlot(String societyKeyword, String blockName, String plotID) {
        HousingSociety s = findSociety(societyKeyword);
        if (s != null) {
            s.bookPlot(blockName, plotID);
        }
    }

       public void cancelPlot(String societyKeyword, String blockName, String plotID) {
        HousingSociety s = findSociety(societyKeyword);
        if (s != null) {
            s.cancelPlot(blockName, plotID);
        }
    }

        public void computeCityTotals() {
        System.out.println("\nCITY TOTALS (All Societies Combined):");
        for (int i = 0; i < count; i++) {
            HousingSociety s = societies[i];
            s.computeTotals(); // assumes computeTotals prints society totals
        }
    }
	
public void printCityReport() {
    System.out.println("\n------------- COMPLETE CITY REPORT ------------");
    for (int i = 0; i < count; i++) {
        HousingSociety s = societies[i];
        s.printReports();
    }
    System.out.println("--------------------------------------------------");
}

}