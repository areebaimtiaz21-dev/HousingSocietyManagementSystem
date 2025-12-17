public class Plot {
    protected String ID;
    protected PlotType type;
    protected Boolean availability;
    private String shape;
    protected double area;
    protected double price;

    
    public Plot(String ID, PlotType type, Boolean availability, String shape,
	double width1, double depth1,double width2, double depth2,
	double front,double back) {
        this.ID = ID;
        this.type = type;
        this.availability = availability;
        this.shape = shape;
        this.area = 0;
        this.price = type.price;  // Base price from PlotType
		if(shape.equals("RECTANGLE")){
			calc_rect(width1,depth1);
		}
		else if(shape.equals("TRAPEZOID")){
			calc_trape(front,back,depth1);
		}
		else if(shape.equals("L_SHAPE")){
			calc_Lshape(width1,depth1,width2,depth2);
		}
    }

        public void calc_rect(double width1, double depth1) {
        area = width1 * depth1;
    }

    public void calc_trape(double front, double back, double depth1) {
        area = ((front + back) / 2) * depth1;
    }

    public void calc_Lshape(double width1, double depth1, double width2, double depth2) {
        area = (width1 * depth1) + (width2 * depth2);
    }

        public void setID(String ID) {
        this.ID = ID;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    
    public String getID() {
        return ID;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public String getShape() {
        return shape;
    }

    public double getArea() {
        return area;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %s, Type: %s, Area: %.2f sq units, Price: %d %s, Availability: %s",
            ID, type.name(), area, type.price, type.currency, availability
        );
    }
}