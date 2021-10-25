import java.util.List;


public class Exact {

    List<Point> plist;
    double minsum;
    Point[] number;
    int rn = 0;

    public Exact(List<Point> plist) {

        this.plist = plist;
        Point[] points = new Point[plist.size()];
        number = new Point[plist.size()-2];

        for (int i = 0; i < plist.size(); i++) {

            points[i] = plist.get(i);
            
        }

        int n = points.length;
        minsum = mTCDP(points, n);



    }

    public void printSolution(Point[] points, int[][] s, int i, int j) {
		if(j-i < 2)
			return;
		printSolution(points,s,i,s[i][j]);
        Point p = new Point(i, j);
        number[rn] = p;
        rn += 1;
		printSolution(points,s,s[i][j],j);
	}


    // Utility function to find minimum of two double values
    double min(double x, double y) {
        return (x <= y) ? x : y;
    }

    // A utility function to find distance between two points in a plane

    double cost(Point points[], int i, int j, int k) {
        Point p1 = points[i], p2 = points[j], p3 = points[k];
        return Point.calcDistance(p1, p2) + Point.calcDistance(p2, p3) + Point.calcDistance(p3, p1);
    }

    
    double mTCDP(Point points[], int n) {
        // There must be at least 3 points to form a triangle
        if (n < 3)
            return 0;

        // table to store results of subproblems. table[i][j] stores cost of
        // triangulation of points from i to j. The entry table[0][n-1] stores
        // the final result. 
        double[][] table = new double[n][n];
        int[][] s = new int[n][n];

        // Fill table using above recursive formula. Note that the table
        // is filled in diagonal fashion i.e., from diagonal elements to
        // table[0][n-1] which is the result.
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (j < i + 2)
                    {table[i][j] = 0.0;}
                    

                else {
                    table[i][j] = 1000000.0;
                    for (int k = i + 1; k < j; k++) {
                        double val = table[i][k] + table[k][j] + cost(points, i, j, k);
                        if (table[i][j] > val)
                            {table[i][j] = val;
                            s[i][j] = k;}
                            

                    }
                }
            }
        }
        
        double externalsum = 0;
        for (int i = 0; i < points.length; i++) {

            double externalsuma;
            if (i == points.length-1) {
            externalsuma = Point.calcDistance(points[i], points[0]);}
                
            else{
            externalsuma = Point.calcDistance(points[i], points[i+1]);}
            
            externalsum += externalsuma;

        }        

		printSolution(points,s,0,n-1);
        return (table[0][n - 1]-externalsum)/2;
    }

}

