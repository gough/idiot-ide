import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Idiot IDE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JEditorPane filePane = new JEditorPane();
        filePane.setBorder(new TitledBorder("Files"));
        filePane.setPreferredSize(new Dimension(200, 0));

        JEditorPane editorPane = new JEditorPane(); // multiple files?
        JEditorPane consolePane = new JEditorPane();
        JEditorPane errorPane = new JEditorPane();

        JScrollPane fileScrollPane = new JScrollPane(filePane);
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        JScrollPane consoleScrollPane = new JScrollPane(consolePane);
        JScrollPane errorScrollPane = new JScrollPane(errorPane);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Console", consoleScrollPane);
        tabbedPane.add("Errors", errorScrollPane);
        tabbedPane.setPreferredSize(new Dimension(0, 200));

        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane2.add(editorScrollPane);
        splitPane2.add(tabbedPane);
        splitPane2.setOneTouchExpandable(true);

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane1.add(fileScrollPane);
        splitPane1.add(splitPane2);
        splitPane1.setOneTouchExpandable(true);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New...");
        JMenuItem openMenuItem = new JMenuItem("Open...");
        JMenuItem saveMenuItem = new JMenuItem("Save...");
        JMenuItem saveAsMenuItem = new JMenuItem("Save As...");
        JMenuItem printMenuItem = new JMenuItem("Print...");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(printMenuItem);
        fileMenu.add(exitMenuItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        JMenuItem selectAllMenuItem = new JMenuItem("Select All");
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(selectAllMenuItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem viewHelpMenuItem = new JMenuItem("View Help");
        JMenuItem aboutMenuItem = new JMenuItem("About my IDE");
        helpMenu.add(viewHelpMenuItem);
        helpMenu.add(aboutMenuItem);

        JToolBar toolBar = new JToolBar("Tool Bar");

        JButton newToolBarButton = new JButton();
        newToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/page_add.png"));

        JButton saveToolBarButton = new JButton();
        saveToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/page_save.png"));

        JButton cutToolBarButton = new JButton();
        cutToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/cut.png"));

        JButton copyToolBarButton = new JButton();
        copyToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/page_copy.png"));

        JButton pasteToolBarButton = new JButton();
        pasteToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/page_paste.png"));

        JButton printToolBarButton = new JButton();
        printToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/printer.png"));

        JButton runToolBarButton = new JButton();
        runToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/resultset_next.png"));

        JButton helpToolBarButton = new JButton();
        helpToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/help.png"));

        JButton closeToolBarButton = new JButton();
        closeToolBarButton.setIcon(new ImageIcon("/home/gougha/workspace/idiot-ide/src/img/cancel.png"));

        toolBar.add(newToolBarButton);
        toolBar.add(saveToolBarButton);
        toolBar.add(cutToolBarButton);
        toolBar.add(copyToolBarButton);
        toolBar.add(pasteToolBarButton);
        toolBar.add(printToolBarButton);
        toolBar.add(runToolBarButton);
        toolBar.add(helpToolBarButton);
        toolBar.add(closeToolBarButton);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        frame.add(splitPane1);
        frame.setJMenuBar(menuBar);
        frame.add(toolBar, BorderLayout.NORTH);

        frame.setMinimumSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

