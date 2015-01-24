import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import javax.swing.JOptionPane;

public class GUI implements ActionListener {
    public GUI(String title, int width, int height) {
        initGUI(title);
    }
    
    public void initGUI(String title) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        // ---------- //

        JMenuBar menuBar = new JMenuBar();

            JMenu fileMenu = new JMenu("File");

                JMenuItem newMenuItem = new JMenuItem("New");
                newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

                JMenuItem openMenuItem = new JMenuItem("Open");
                openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

                JMenuItem saveMenuItem = new JMenuItem("Save");
                saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

                JMenuItem saveAsMenuItem = new JMenuItem("Save As");
                saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));

                JMenuItem printMenuItem = new JMenuItem("Print");
                printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

                JMenuItem quitMenuItem = new JMenuItem("Quit");
                quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

            fileMenu.add(newMenuItem);
            fileMenu.add(openMenuItem);
            fileMenu.add(saveMenuItem);
            fileMenu.add(saveAsMenuItem);
            fileMenu.add(new JSeparator());
            fileMenu.add(printMenuItem);
            fileMenu.add(new JSeparator());
            fileMenu.add(quitMenuItem);

            JMenu editMenu = new JMenu("Edit");

                JMenuItem cutMenuItem = new JMenuItem("Cut");
                cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

                JMenuItem copyMenuItem = new JMenuItem("Copy");
                copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

                JMenuItem pasteMenuItem = new JMenuItem("Paste");
                pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

                JMenuItem selectAllMenuItem = new JMenuItem("Select All");
                selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

            editMenu.add(cutMenuItem);
            editMenu.add(copyMenuItem);
            editMenu.add(pasteMenuItem);
            editMenu.add(new JSeparator());
            editMenu.add(selectAllMenuItem);

            JMenu helpMenu = new JMenu("Help");

                JMenuItem viewHelpMenuItem = new JMenuItem("View Help");
                viewHelpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

                JMenuItem aboutMenuItem = new JMenuItem("About My IDE");
                aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));

            helpMenu.add(viewHelpMenuItem);
            helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        // ---------- //

        JToolBar toolBar = new JToolBar();

            JButton newToolBarButton = new JButton("New");

            JButton openToolBarButton = new JButton("Open");

            JButton saveToolBarButton = new JButton("Save");

            JButton saveAsToolBarButton = new JButton("Save As");

            JButton closeToolBarButton = new JButton("Close");

            JButton printToolBarButton = new JButton("Print");

            JButton cutToolBarButton = new JButton("Cut");

            JButton copyToolBarButton = new JButton("Copy");

            JButton pasteToolBarButton = new JButton("Paste");

            JButton runToolBarButton = new JButton("Run");

        toolBar.add(newToolBarButton);
        toolBar.add(openToolBarButton);
        toolBar.add(saveToolBarButton);
        toolBar.add(saveAsToolBarButton);
        toolBar.add(closeToolBarButton);
        toolBar.add(printToolBarButton);
        toolBar.add(cutToolBarButton);
        toolBar.add(copyToolBarButton);
        toolBar.add(pasteToolBarButton);
        toolBar.add(runToolBarButton);

        // ---------- //

        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

            // leftSplitPane currently contains the file browser
            JSplitPane leftSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

                JEditorPane filePane = new JEditorPane();
                JScrollPane fileScrollPane = new JScrollPane(filePane);

            leftSplitPane.add(fileScrollPane);

            // rightSplitPane currently contains two tabbed panes for editing files and output
            JSplitPane rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

                JTabbedPane topTabbedPane = new JTabbedPane();

                    // TODO: real tabbed file editor

                    // JEditorPane(s) used as a placeholder, could be changed in future
                    JEditorPane file1 = new JEditorPane();
                    JScrollPane file1ScrollPane = new JScrollPane(file1);

                    JEditorPane file2 = new JEditorPane();
                    JScrollPane file2ScrollPane = new JScrollPane(file2);

                topTabbedPane.add(file1ScrollPane, "file1.idiot");
                topTabbedPane.add(file2ScrollPane, "file2.idiot");

                JTabbedPane bottomTabbedPane = new JTabbedPane();

                    // JEditorPane(s) used as a placeholder, could be changed in future
                    JEditorPane consolePane = new JEditorPane();
                    JEditorPane errorPane = new JEditorPane();

                bottomTabbedPane.add(consolePane, "Console");
                bottomTabbedPane.add(errorPane, "Errors");

            rightSplitPane.add(topTabbedPane);
            rightSplitPane.add(bottomTabbedPane);

        mainSplitPane.add(leftSplitPane);
        mainSplitPane.add(rightSplitPane);

        // ---------- //

        panel.add(mainSplitPane);

        frame.add(panel);

        frame.setJMenuBar(menuBar);
        frame.add(toolBar, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("new")) {
            JOptionPane.showMessageDialog(null, "test");
        }

        if (action.equals("help")) {
            JOptionPane.showMessageDialog(null, "Version 1.0 \n" +
                    "Created By: \n" +
                    "Ben Potter, \n" +
                    "Adam Gough, \n" +
                    "Keylan Norum, \n" +
                    "Kaori Millar, \n" +
                    "Kuan Heng Kuo \n" +
                    "Icons provided by: \n" +
                    "http://www.famfamfam.com/lab/icons/silk/");

        }
    }
}

