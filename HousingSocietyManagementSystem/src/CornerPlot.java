public class CornerPlot extends Plot {
	double depth1;
	double width1;
	double width2;   
    double premiumRate = 0.08;  
        public CornerPlot(String id, PlotType type, boolean available, 
	String shape,double width1, double depth1,double width2, double depth2,
	double front,double back) {
        super(id, type, available, shape,width1,depth1,width2,
		depth2,front,back); // call parent constructor
		this.depth1=depth1;
		this.width1=width1;
		this.width2=width2;
		this.area=calcCornerArea(width1,width2,depth1);
		this.price=applyPremium();
    }

        public double calcCornerArea(double width1,double width2,double depth1) {
        this.area = ((width1 + width2) / 2) * depth1; // averaged frontage area
		return area;
	}

   
    public double applyPremium() {
        this.price = type.price + (type.price * premiumRate);
		return price;
    }

    @Override
    public String toString() {
        return "ID: " + ID + ", Type: " + type + 
               ", Area: " + area + "Price without Premium:"+ type.price+ "Price with Premium: " + price + 
               " PKR, Available: " + availability;
    }
}

        