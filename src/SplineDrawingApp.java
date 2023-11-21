import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
//full
public class SplineDrawingApp extends JFrame {
    private List<Point> points = new ArrayList<>();
    private boolean isMousePressed;
    private int pIndex;
    private JCheckBox showPointsAndLinesCheckBox;
    private JButton clearButton;
    private DrawingPanel drawingPanel;

    public SplineDrawingApp() {
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Spline Drawing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));

        // Checkbox to show/hide points and lines
        showPointsAndLinesCheckBox = new JCheckBox("Show Points and Lines", true);
        showPointsAndLinesCheckBox.addActionListener(e -> drawingPanel.repaint());

        // Button to clear the drawing
        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            points.clear();
            drawingPanel.repaint();
        });

        // Setup the drawing panel
        drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.WHITE);

        // Layout for buttons and checkbox
        JPanel controlsPanel = new JPanel();
        controlsPanel.add(showPointsAndLinesCheckBox);
        controlsPanel.add(clearButton);

        // Adding components to the frame
        add(drawingPanel, BorderLayout.CENTER);
        add(controlsPanel, BorderLayout.SOUTH);

        // Display the frame
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw the spline
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(2));
            if (points.size() >= 4) {
                for (int i = 0; i < points.size() - 3; i++) {
                    for (float t = 0; t <= 1; t += 0.01f) {
                        float x = calculateSplineCoordinate(points.get(i).x, points.get(i + 1).x, points.get(i + 2).x, points.get(i + 3).x, t);
                        float y = calculateSplineCoordinate(points.get(i).y, points.get(i + 1).y, points.get(i + 2).y, points.get(i + 3).y, t);
                        g2d.draw(new Ellipse2D.Float(x, y, 1, 1));
                    }
                }
            }

            // Draw points and lines if checkbox is selected
            if (showPointsAndLinesCheckBox.isSelected()) {
                g2d.setColor(Color.BLACK);
                for (int i = 0; i < points.size(); i++) {
                    g2d.fill(new Ellipse2D.Double(points.get(i).x - 3, points.get(i).y - 3, 6, 6));
                    if (i < points.size() - 1) {
                        g2d.drawLine(points.get(i).x, points.get(i).y, points.get(i + 1).x, points.get(i + 1).y);
                    }
                }
            }
        }

        public DrawingPanel() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    isMousePressed = true;
                    for (int i = 0; i < points.size(); i++) {
                        if (containsPoint(points.get(i), e.getPoint())) {
                            pIndex = i;
                            return;
                        }
                    }
                    points.add(e.getPoint());
                    isMousePressed = false;
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    isMousePressed = false;
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (isMousePressed) {
                        points.set(pIndex, e.getPoint());
                        repaint();
                    }
                }
            });
        }
    }

    // Method to check if a mouse click is within a certain distance of a point
    private boolean containsPoint(Point p1, Point p2) {
        return p1.distance(p2) <= 3;
    }

    // Calculate spline coordinate
    private float calculateSplineCoordinate(float p0, float p1, float p2, float p3, float t) {
        float a3 = (-p0 + 3 * (p1 - p2) + p3) / 6;
        float a2 = (p0 - 2 * p1 + p2) / 2;
        float a1 = (p2 - p0) / 2;
        float a0 = (p0 + 4 * p1 + p2) / 6;

        return ((a3 * t + a2) * t + a1) * t + a0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SplineDrawingApp::new);
    }
}
