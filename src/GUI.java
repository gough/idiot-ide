import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import javax.swing.JOptionPane;

public class GUI extends JFrame implements ActionListener {
    public GUI(String title, int width, int height) {
        // call JFrame constructor to create frame with our title
        super(title);
        
        // set frame menuBar
        addMenuBarToPane();
        
        // add toolBar to frame and set to top of frame
        addToolBarToPane(this.getContentPane());
        
        // add remaining components to frame
        addComponentsToPane(this.getContentPane());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(width, height));
        pack();
        setLocationRelativeTo(null);
        
        // finally, display frame
        setVisible(true);
    }
    
    public void addMenuBarToPane() {
        JMenuBar menuBar = new JMenuBar();

            JMenu fileMenu = new JMenu("File");

                JMenuItem newMenuItem = new JMenuItem("New");
                newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
                newMenuItem.addActionListener(this);
                newMenuItem.setActionCommand("file_new");

                JMenuItem openMenuItem = new JMenuItem("Open");
                openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
                openMenuItem.addActionListener(this);
                openMenuItem.setActionCommand("file_open");

                JMenuItem saveMenuItem = new JMenuItem("Save");
                saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
                saveMenuItem.addActionListener(this);
                saveMenuItem.setActionCommand("file_save");

                JMenuItem saveAsMenuItem = new JMenuItem("Save As");
                saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
                saveAsMenuItem.addActionListener(this);
                saveAsMenuItem.setActionCommand("file_saveAs");

                JMenuItem printMenuItem = new JMenuItem("Print");
                printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
                printMenuItem.addActionListener(this);
                printMenuItem.setActionCommand("file_print");

                JMenuItem quitMenuItem = new JMenuItem("Quit");
                quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
                quitMenuItem.addActionListener(this);
                quitMenuItem.setActionCommand("file_quit");

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
                cutMenuItem.addActionListener(this);
                cutMenuItem.setActionCommand("edit_cut");

                JMenuItem copyMenuItem = new JMenuItem("Copy");
                copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
                copyMenuItem.addActionListener(this);
                copyMenuItem.setActionCommand("edit_copy");

                JMenuItem pasteMenuItem = new JMenuItem("Paste");
                pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
                pasteMenuItem.addActionListener(this);
                pasteMenuItem.setActionCommand("edit_paste");
        
                JMenuItem selectAllMenuItem = new JMenuItem("Select All");
                selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
                selectAllMenuItem.addActionListener(this);
                selectAllMenuItem.setActionCommand("edit_selectAll");

            editMenu.add(cutMenuItem);
            editMenu.add(copyMenuItem);
            editMenu.add(pasteMenuItem);
            editMenu.add(new JSeparator());
            editMenu.add(selectAllMenuItem);

            JMenu helpMenu = new JMenu("Help");

                JMenuItem viewHelpMenuItem = new JMenuItem("View Help");
                viewHelpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
                viewHelpMenuItem.addActionListener(this);
                viewHelpMenuItem.setActionCommand("help_viewHelp");

                JMenuItem aboutMenuItem = new JMenuItem("About My IDE");
                aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
                aboutMenuItem.addActionListener(this);
                aboutMenuItem.setActionCommand("help_about");

            helpMenu.add(viewHelpMenuItem);
            helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        
        this.setJMenuBar(menuBar);
    }
    
    public void addToolBarToPane(Container pane) {
        JToolBar toolBar = new JToolBar();

            JButton newToolBarButton = new JButton("New");
            newToolBarButton.addActionListener(this);
            newToolBarButton.setActionCommand("file_new");

            JButton openToolBarButton = new JButton("Open");
            openToolBarButton.addActionListener(this);
            openToolBarButton.setActionCommand("file_open");

            JButton saveToolBarButton = new JButton("Save");
            saveToolBarButton.addActionListener(this);
            saveToolBarButton.setActionCommand("file_save");

            JButton saveAsToolBarButton = new JButton("Save As");
            saveAsToolBarButton.addActionListener(this);
            saveAsToolBarButton.setActionCommand("file_saveAs");

            JButton printToolBarButton = new JButton("Print");
            printToolBarButton.addActionListener(this);
            printToolBarButton.setActionCommand("file_print");

            JButton cutToolBarButton = new JButton("Cut");
            cutToolBarButton.addActionListener(this);
            cutToolBarButton.setActionCommand("edit_cut");

            JButton copyToolBarButton = new JButton("Copy");
            copyToolBarButton.addActionListener(this);
            copyToolBarButton.setActionCommand("edit_copy");

            JButton pasteToolBarButton = new JButton("Paste");
            pasteToolBarButton.addActionListener(this);
            pasteToolBarButton.setActionCommand("edit_paste");

            JButton runToolBarButton = new JButton("Run");

        toolBar.add(newToolBarButton);
        toolBar.add(openToolBarButton);
        toolBar.add(saveToolBarButton);
        toolBar.add(saveAsToolBarButton);
        toolBar.add(printToolBarButton);
        toolBar.add(cutToolBarButton);
        toolBar.add(copyToolBarButton);
        toolBar.add(pasteToolBarButton);
        toolBar.add(runToolBarButton);
        
        pane.add(toolBar, BorderLayout.NORTH);
    }
    
    public void addComponentsToPane(Container pane) {
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
        
        pane.add(mainSplitPane);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        if (action.equals("file_new")) {
            JOptionPane.showMessageDialog(null, "file_new");
        } else if (action.equals("file_open")) {
            JOptionPane.showMessageDialog(null, "file_open");
        } else if (action.equals("file_save")) {
            JOptionPane.showMessageDialog(null, "file_save");
        } else if (action.equals("file_saveAs")) {
            JOptionPane.showMessageDialog(null, "file_saveAs");
        } else if (action.equals("file_print")) {
            JOptionPane.showMessageDialog(null, "file_print");
        } else if (action.equals("file_quit")) {
            JOptionPane.showMessageDialog(null, "file_quit");
        }
        
        if (action.equals("edit_cut")) {
            JOptionPane.showMessageDialog(null, "edit_cut");
        } else if (action.equals("edit_copy")) {
            JOptionPane.showMessageDialog(null, "edit_copy");
        } else if (action.equals("edit_paste")) {
            JOptionPane.showMessageDialog(null, "edit_paste");
        } else if (action.equals("edit_selectAll")) {
            JOptionPane.showMessageDialog(null, "edit_selectAll");
        }
        
        if (action.equals("help_viewHelp")) {
            JOptionPane.showMessageDialog(null, "help_viewHelp");
        } else if (action.equals("help_about")) {
            JOptionPane.showMessageDialog(null,
                "Version 1.0 \n" +
                "Created By: \n" +
                "Ben Potter, \n" +
                "Adam Gough, \n" +
                "Keylan Norum, \n" +
                "Kaori Millar, \n" +
                "Kuan Heng Kuo \n" +
                "Icons provided by: \n" +
                "http://www.famfamfam.com/lab/icons/silk/"
            );
        }
    }
}

