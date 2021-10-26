/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *   @Minsik Jeong (21135840)
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class DrawPolygon extends JPanel {

    static List<Point> plist = new ArrayList<Point>();
    private int mouseX, mouseY;
    private int PostX, PostY;
    private int count = 0;
    boolean confirm = false;
    boolean bf = false;
    boolean gd = false;
    boolean ex = false;
    JLabel result;
    Scanner sc = new Scanner(System.in);

    private static final int RADIUS = 2;
    private static final int DIAMETER = 2 * RADIUS;
    private static final Color DOT_COLOR = Color.black;

    public DrawPolygon() {
        addMouseListener(new ClickListener());
        JButton button_br = new JButton("Brute-force");
        JButton button_gr = new JButton("Greedy");
        JButton button_ex = new JButton("Exact");
        JButton button_new = new JButton("Draw new");
        result = new JLabel();
        result.setSize(80, 30);
        button_br.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                bf = true;
                ex = false;
                gd = false;
                repaint();

            }
        });
        button_gr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                bf = false;
                ex = false;
                gd = true;
                repaint();
            }
        });
        button_ex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                bf = false;
                ex = true;
                gd = false;
                repaint();

            }
        });
        button_new.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                bf = false;
                ex = false;
                gd = false;
                confirm = false;
                count = 0;
                plist.clear();
                repaint();
                result.setText("0");

            }
        });
        this.add(button_br);
        this.add(button_gr);
        this.add(button_ex);
        this.add(button_new);
        this.add(result);
    }

    public void paintComponent(Graphics g) {
        g.setColor(DOT_COLOR);

        if (confirm) {
            int[] x = new int[count];
            int[] y = new int[count];

            for (int i = 0; i < plist.size(); i++) {
                Point p = plist.get(i);

                x[i] = p.getX();
                y[i] = p.getY();
                g.fillOval(p.getX() - RADIUS, p.getY() - RADIUS, DIAMETER, DIAMETER);

            }

            g.drawPolygon(x, y, count);
                 

            if (bf) {

                Brute_force bc = new Brute_force(plist);

                List a = bc.plist2;
                List b = bc.plist3;
                Point[] a1 = bc.number4;
                Point[] b2 = bc.number5;
                
                for (int i = 0; i < bc.plist2.size(); i++) {
                    int x1 = ((Point) a.get(i)).getX();
                    int y1 = ((Point) a.get(i)).getY();
                    int x2 = ((Point) b.get(i)).getX();
                    int y2 = ((Point) b.get(i)).getY();
                    g.setColor(Color.RED);
                    g.drawLine(x1, y1, x2, y2);
                }

                for (int i = 0; i < bc.number4.length; i++) {
                    int x1 = a1[i].getX();
                    int y1 = a1[i].getY();
                    int x2 = b2[i].getX();
                    int y2 = b2[i].getY();
                    g.setColor(Color.red);
                    g.drawLine(x1, y1, x2, y2);

                }
                result.setText(String.format("%.3f", bc.minSum));

                

                bf = false;

            }

            else if (gd) {

                Greedy greedy = new Greedy(plist);
                List ag = greedy.aList;
                List bg = greedy.bList;

                for (int i = 0; i < ag.size(); i++) {
                    int x1 = ((Point) ag.get(i)).getX();
                    int y1 = ((Point) ag.get(i)).getY();
                    int x2 = ((Point) bg.get(i)).getX();
                    int y2 = ((Point) bg.get(i)).getY();
                    g.setColor(Color.BLUE);
                    g.drawLine(x1, y1, x2, y2);
                }
                System.out.println("Greedy algorithm : " + String.format("%.3f", greedy.sum));
                result.setText(String.format("%.3f", greedy.sum));

                gd = false;
            }

            else if (ex) {
                Exact e = new Exact(plist);

                Point[] number = e.number;

                for (int i = 0; i < number.length; i++) {
                    int x1 = x[number[i].getX()];
                    int y1 = y[number[i].getX()];
                    int x2 = x[number[i].getY()];
                    int y2 = y[number[i].getY()];
                    g.setColor(Color.MAGENTA);
                    g.drawLine(x1, y1, x2, y2);

                }
                g.setColor(Color.black);
                g.drawPolygon(x, y, count);

                System.out.println("Exact algorithm : " + String.format("%.3f", e.minsum));
                result.setText(String.format("%.3f", e.minsum));
                ex = false;
            }

        }

        else if (count > 0 && confirm == false) {
            for (int i = 0; i < plist.size(); i++) {
                Point p = plist.get(i);
                g.fillOval(p.getX() - RADIUS, p.getY() - RADIUS, DIAMETER, DIAMETER);
            }
        }

    }

    private class ClickListener implements MouseListener {

        public void mouseClicked(MouseEvent click) {

            mouseX = click.getX();
            mouseY = click.getY();

            if (PostX == mouseX && PostY == mouseY) {
                confirm = true;
            }

            else if (confirm) {

            }

            else {

                count++;
                Point p = new Point(mouseX, mouseY);
                plist.add(p);
                PostX = mouseX;
                PostY = mouseY;
            }
            repaint();

        } // end mouseClicked

        // Ignore these actions on this panel:
        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw Polygon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        DrawPolygon panel = new DrawPolygon();
        frame.add(panel);
        frame.setVisible(true);
    }
}

