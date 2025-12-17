public class Park{
	String shape = "RECTANGLE";
    double width;
    double length;
    double area;
	Park(double width,double length){
		this.width=width;
		this.length=length;
		this.area=calc_area();
	}
	public double calc_area(){
	area=length*width;
	return area;}
	
	public String toString(){
		return String.format(
		"Park Shape: %s | Area: %.2f",
		shape,area);
	}
		
}