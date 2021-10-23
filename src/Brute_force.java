

import java.util.ArrayList;
import java.util.List;

public class Brute_force {

    int num_points;
    List<Point> plist;
    List<Point> plist2 = new ArrayList<Point>();
    List<Point> plist3 = new ArrayList<Point>();
    Point[] number2;
    Point[] number3;
    Point[] number4;
    Point[] number5;
    double minSum;


    public Brute_force(List<Point> plist) {
        this.plist = plist;
        number2 = new Point[plist.size()-3];
        number3 = new Point[plist.size()-3];

        Node start = new Node();
        start.val1 = plist.get(0);
        start.Index = 0;

        Node temp = start;
        for (int i = 1; i < plist.size(); i++) {
            temp.Next = new Node();
            temp.Next.val1 = plist.get(i);
            temp.Next.Prev = temp;
            temp.Next.Index = i;
            temp = temp.Next;
        }


        temp.Next = start;
        start.Prev = temp;
        minSum = 1000000000;

        findinternaledge(start, 0, plist.size());
        System.out.println("Brute Force algorithm : " + String.format("%.3f",minSum));

    }

    public void findinternaledge(Node start, double Sum, int length) {

        if (length == 3) {
            
            if (minSum > Sum) {
                minSum = Sum;
                number4 = number2.clone();
                number5 = number3.clone();                
            }


            return;
        }

        Node temp = start;
        while (temp != null) {
            Node remove = temp;
            Sum += Point.calcDistance(remove.Next.val1, remove.Prev.val1);
            plist2.add(remove.Next.val1);
            plist3.add(remove.Prev.val1);
            number2[plist.size()-length] = remove.Next.val1;
            number3[plist.size()-length] = remove.Prev.val1;
            remove.Prev.Next = remove.Next;
            remove.Next.Prev = remove.Prev;
            findinternaledge(remove.Next, Sum, length - 1);
            remove.Prev.Next = remove;
            remove.Next.Prev = remove;
            Sum -= Point.calcDistance(remove.Next.val1, remove.Prev.val1);
            if (temp.Next == start) {
                break;
            }
            temp = temp.Next;
        }
        return;

    }

}

