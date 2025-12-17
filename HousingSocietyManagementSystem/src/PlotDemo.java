public class PlotDemo {
    public static void main(String[] args) {

                Plot p1 = new Plot("A1", PlotType.RES_5_MARLA, true, "RECTANGLE",
                           40,50,0,0,0,0);

                Plot p2 = new Plot("B2", PlotType.RES_10_MARLA, false, "TRAPEZOID",
                           0,0,0,0,35,50);

               Plot p3 = new Plot("C3", PlotType.COMM_SHOP, true, "L_SHAPE",
                           30,20,15,10,0,0);

                CornerPlot cp = new CornerPlot("CP1", PlotType.RES_10_MARLA, true, "RECTANGLE",
                                       35,50,40,20,0,0);
        cp.calc_Lshape(35,50,40,20);

        System.out.println("--- Plot Details ---");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(cp);
    }
}
