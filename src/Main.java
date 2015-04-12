public class Main {
	private static GUI gui;
	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui = new GUI("IDIOT IDE", 800, 600);
            }
        });

    }

    public static GUI getGUI() {
    	return gui;
    }
}
