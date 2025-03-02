import javax.swing.*; // GUI components like JFrame, JPanel, JButton
import java.awt.*; // classes for layouts and graphics
import javax.swing.Timer; // Timer for animations

public class Main {

    private static double angle = 0; // keeps track of the rotation angle
    private static Timer rotationTimer; // timer to control rotation

    public static void main(String[] args) {
        JFrame frame = new JFrame(); // main application window
        Container pane = frame.getContentPane(); // container
        pane.setLayout(new BorderLayout()); // divides window in five regions

        // ball animation
        JPanel renderPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.black); // draws the background
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // ball properties
                int baseSize = 300;
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                int distance = 110;

                // drawing the ball and rotating
                g2d.translate(centerX, centerY);

                g2d.rotate(Math.toRadians(angle));

                g2d.setColor(Color.blue);
                g2d.fillOval(-distance - baseSize / 2, -baseSize / 2, baseSize, baseSize);

            }
        };
        pane.add(renderPanel, BorderLayout.CENTER);

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> startRotation(renderPanel));
        buttonPanel.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> stopRotation());
        buttonPanel.add(stopButton);
        pane.add(buttonPanel, BorderLayout.NORTH);

        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void startRotation(JPanel panel) {
        if (rotationTimer != null && rotationTimer.isRunning()) return;

        rotationTimer = new Timer(10, e -> {
            angle += 2;
            if (angle >= 360) {
                angle = 0;
            }
            panel.repaint();
        });
        rotationTimer.start();
    }

    private static void stopRotation() {
        if (rotationTimer != null) {
            rotationTimer.stop();
        }
    }
}