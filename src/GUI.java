import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterJob;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;

public class GUI extends JFrame implements ActionListener {
    private Editor editor = null;
    private JFileChooser fileChooser = new JFileChooser();

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

                JMenuItem cutMenuItem = new JMenuItem(new DefaultEditorKit.CutAction());
                cutMenuItem.setText("Cut");
                cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
                cutMenuItem.addActionListener(this);
                cutMenuItem.setActionCommand("edit_cut");

                JMenuItem copyMenuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
                copyMenuItem.setText("Copy");
                copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
                copyMenuItem.addActionListener(this);
                copyMenuItem.setActionCommand("edit_copy");

                JMenuItem pasteMenuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
                pasteMenuItem.setText("Paste");
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
            newToolBarButton.setIcon(new ImageIcon(getClass().getResource("icons/page_add.png")));
            newToolBarButton.addActionListener(this);
            newToolBarButton.setActionCommand("file_new");

            JButton openToolBarButton = new JButton("Open");
            openToolBarButton.setIcon(new ImageIcon(getClass().getResource("icons/folder_page.png")));
            openToolBarButton.addActionListener(this);
            openToolBarButton.setActionCommand("file_open");

            JButton saveToolBarButton = new JButton("Save");
            saveToolBarButton.setIcon(new ImageIcon(getClass().getResource("icons/disk.png")));
            saveToolBarButton.addActionListener(this);
            saveToolBarButton.setActionCommand("file_save");

            JButton saveAsToolBarButton = new JButton("Save As");
            saveAsToolBarButton.setIcon(new ImageIcon(getClass().getResource("icons/page_save.png")));
            saveAsToolBarButton.addActionListener(this);
            saveAsToolBarButton.setActionCommand("file_saveAs");

            JButton printToolBarButton = new JButton("Print");
            printToolBarButton.setIcon(new ImageIcon(getClass().getResource("icons/printer.png")));
            printToolBarButton.addActionListener(this);
            printToolBarButton.setActionCommand("file_print");

            JButton cutToolBarButton = new JButton(new DefaultEditorKit.CutAction());
            cutToolBarButton.setText("Cut");
            cutToolBarButton.setIcon(new ImageIcon(getClass().getResource("icons/cut.png")));
            cutToolBarButton.addActionListener(this);
            cutToolBarButton.setActionCommand("edit_cut");

            JButton copyToolBarButton = new JButton(new DefaultEditorKit.CopyAction());
            copyToolBarButton.setText("Copy");
            copyToolBarButton.setIcon(new ImageIcon(getClass().getResource("icons/page_copy.png")));
            copyToolBarButton.addActionListener(this);
            copyToolBarButton.setActionCommand("edit_copy");

            JButton pasteToolBarButton = new JButton(new DefaultEditorKit.PasteAction());
            pasteToolBarButton.setText("Paste");
            pasteToolBarButton.setIcon(new ImageIcon(getClass().getResource("icons/page_paste.png")));
            pasteToolBarButton.addActionListener(this);
            pasteToolBarButton.setActionCommand("edit_paste");

            JButton runToolBarButton = new JButton("Run");
            runToolBarButton.setIcon(new ImageIcon(getClass().getResource("icons/resultset_next.png")));

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
            leftSplitPane.setMinimumSize(new Dimension(0, 0));

            // rightSplitPane currently contains two tabbed panes for editing files and output
            JSplitPane rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

                this.editor = new Editor();
                this.editor.setPreferredSize(new Dimension(0, 400));

                JTabbedPane bottomTabbedPane = new JTabbedPane();

                    // JEditorPane(s) used as a placeholder, could be changed in future
                    JEditorPane consolePane = new JEditorPane();
                    JEditorPane errorPane = new JEditorPane();

                bottomTabbedPane.add(consolePane, "Console");
                bottomTabbedPane.add(errorPane, "Errors");

            rightSplitPane.add(this.editor);
            rightSplitPane.add(bottomTabbedPane);
            rightSplitPane.setMinimumSize(new Dimension(0, 0));
            rightSplitPane.setOneTouchExpandable(true);

        mainSplitPane.add(leftSplitPane);
        mainSplitPane.add(rightSplitPane);
        mainSplitPane.setOneTouchExpandable(true);
        
        pane.add(mainSplitPane);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        if (action.equals("file_new")) {
            String title = "";
            while (title.length() < 1) {
                title = JOptionPane.showInputDialog("Enter a new file name:");
                if (title.length() < 1) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid file name.");
                }
            }

            this.editor.newTab(title + ".idiot");
        } else if (action.equals("file_open")) {
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("IDIOT", "idiot"));
            
            int returnValue = this.fileChooser.showOpenDialog(null);
            String filePath = String.valueOf(this.fileChooser.getSelectedFile());
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = new File(filePath);
                String fileContents = "";
                
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        fileContents = fileContents + line + "\n";
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
                this.editor.newTab(filePath.split("/")[filePath.split("/").length - 1]);
                this.editor.getLastTab().setText(fileContents);
            } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            } else {
                JOptionPane.showMessageDialog(null, "Error opening file");
            }
        } else if (action.equals("file_save")) {
            JOptionPane.showMessageDialog(null, "file_save");
        } else if (action.equals("file_saveAs")) {
            int returnValue = this.fileChooser.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = new File(String.valueOf(this.fileChooser.getSelectedFile()));
                System.out.println(file);
            }
        } else if (action.equals("file_print")) {
            // TODO: get printing working
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.printDialog();
        } else if (action.equals("file_quit")) {
            // TODO: ask to save file
            System.exit(0);
        }
        
        if (action.equals("edit_cut")) {
        } else if (action.equals("edit_copy")) {
        } else if (action.equals("edit_paste")) {
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

