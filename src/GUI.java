import java.awt.*;
import java.io.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterJob;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import javax.swing.tree.DefaultMutableTreeNode;

public class GUI extends JFrame implements ActionListener {
	private Editor editor = null;
	private JFileChooser fileChooser = new JFileChooser();
	private FileTree fileTree = new FileTree();
    private JEditorPane consolePane;

	public GUI(String title, int width, int height) {
		// call JFrame constructor to create frame with our title
		super(title);

		// set frame menuBar
		addMenuBarToPane();

		// add toolBar to frame and set to top of frame
		addToolBarToPane(this.getContentPane());

		// add remaining components to frame
		addComponentsToPane(this.getContentPane());

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e) {
				FileWriter writer;
				String[] buttons = { "Exit without saving", "Save and exit", "Cancel" };
				int warnopt = JOptionPane
						.showOptionDialog(
								null,
								"Are you sure you want to exit this program?\n All unsaved progress will be lost.",
								"Warning", JOptionPane.WARNING_MESSAGE, 0, null,
								buttons, buttons[1]);

				if (warnopt == 0) {
					System.exit(0);
				}
				if (warnopt == 1) {
					for(int i = editor.getTabCount()-1; i >= 0; i--)
					{
						try {
							File file = new File(editor.getLastSaveDirectory(i));
							file.createNewFile();

							writer = new FileWriter(file);
							writer.write(editor.getActiveTab().getText());

							writer.flush();
							writer.close();
							//JOptionPane.showMessageDialog(null, file);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						editor.removeTab(0);
					}
					System.exit(0);
				}
			}
			
		});
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
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		newMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/page_add.png")));
		newMenuItem.addActionListener(this);
		newMenuItem.setActionCommand("file_new");

		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		openMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/folder_page.png")));
		openMenuItem.addActionListener(this);
		openMenuItem.setActionCommand("file_open");
		
		JMenuItem closeMenuItem = new JMenuItem("Close");
		closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,
				ActionEvent.CTRL_MASK));
		closeMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/application_delete.png")));
		closeMenuItem.addActionListener(this);
		closeMenuItem.setActionCommand("file_close");

		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		saveMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/disk.png")));
		saveMenuItem.addActionListener(this);
		saveMenuItem.setActionCommand("file_save");

		JMenuItem saveAsMenuItem = new JMenuItem("Save As");
		saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		saveAsMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/page_save.png")));
		saveAsMenuItem.addActionListener(this);
		saveAsMenuItem.setActionCommand("file_saveAs");

		JMenuItem printMenuItem = new JMenuItem("Print");
		printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		printMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/printer.png")));
		printMenuItem.addActionListener(this);
		printMenuItem.setActionCommand("file_print");

		JMenuItem quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		quitMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/cross.png")));
		quitMenuItem.addActionListener(this);
		quitMenuItem.setActionCommand("file_quit");

		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(closeMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(printMenuItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(quitMenuItem);

		JMenu editMenu = new JMenu("Edit");

		JMenuItem cutMenuItem = new JMenuItem(new DefaultEditorKit.CutAction());
		cutMenuItem.setText("Cut");
		cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		cutMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/cut.png")));
		cutMenuItem.addActionListener(this);
		cutMenuItem.setActionCommand("edit_cut");

		JMenuItem copyMenuItem = new JMenuItem(
				new DefaultEditorKit.CopyAction());
		copyMenuItem.setText("Copy");
		copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		copyMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/page_copy.png")));
		copyMenuItem.addActionListener(this);
		copyMenuItem.setActionCommand("edit_copy");

		JMenuItem pasteMenuItem = new JMenuItem(
				new DefaultEditorKit.PasteAction());
		pasteMenuItem.setText("Paste");
		pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));
		pasteMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/page_paste.png")));
		pasteMenuItem.addActionListener(this);
		pasteMenuItem.setActionCommand("edit_paste");

		//JMenuItem selectAllMenuItem = new JMenuItem("Select All");
		//selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
			//	ActionEvent.CTRL_MASK));
		//selectAllMenuItem.addActionListener(this);
		//selectAllMenuItem.setActionCommand("edit_selectAll");

		editMenu.add(cutMenuItem);
		editMenu.add(copyMenuItem);
		editMenu.add(pasteMenuItem);
		editMenu.add(new JSeparator());
		//editMenu.add(selectAllMenuItem);

        JMenu runMenu = new JMenu("Run");

        JMenuItem runMenuItem = new JMenuItem("Run");
        runMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        runMenuItem.setIcon(new ImageIcon(getClass().getResource("img/resultset_next.png")));
        runMenuItem.addActionListener(this);
        runMenuItem.setActionCommand("run_run");

        JMenuItem debugMenuItem = new JMenuItem("Debug");
        debugMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        debugMenuItem.setIcon(new ImageIcon(getClass().getResource("img/wrench.png")));
        debugMenuItem.addActionListener(this);
        debugMenuItem.setActionCommand("run_debug");

        runMenu.add(runMenuItem);
        runMenu.add(debugMenuItem);

		JMenu helpMenu = new JMenu("Help");

		JMenuItem viewHelpMenuItem = new JMenuItem("View Help");
		viewHelpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				ActionEvent.CTRL_MASK));
		viewHelpMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/help.png")));
		viewHelpMenuItem.addActionListener(this);
		viewHelpMenuItem.setActionCommand("help_viewHelp");

		JMenuItem aboutMenuItem = new JMenuItem("About My IDE");
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		aboutMenuItem.setIcon(new ImageIcon(getClass().getResource(
                "img/information.png")));
		aboutMenuItem.addActionListener(this);
		aboutMenuItem.setActionCommand("help_about");

		helpMenu.add(viewHelpMenuItem);
		helpMenu.add(aboutMenuItem);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
        menuBar.add(runMenu);
		menuBar.add(helpMenu);

		this.setJMenuBar(menuBar);
	}

	public void addToolBarToPane(Container pane) {
		JToolBar toolBar = new JToolBar();

		JButton newToolBarButton = new JButton("New");
		newToolBarButton.setIcon(new ImageIcon(getClass().getResource(
                "img/page_add.png")));
		newToolBarButton.addActionListener(this);
		newToolBarButton.setActionCommand("file_new");

		JButton openToolBarButton = new JButton("Open");
		openToolBarButton.setIcon(new ImageIcon(getClass().getResource(
                "img/folder_page.png")));
		openToolBarButton.addActionListener(this);
		openToolBarButton.setActionCommand("file_open");
		
		JButton closeToolBarButton = new JButton("Close");
		closeToolBarButton.setIcon(new ImageIcon(getClass().getResource(
                "img/application_delete.png")));
		closeToolBarButton.addActionListener(this);
		closeToolBarButton.setActionCommand("file_close");

		JButton saveToolBarButton = new JButton("Save");
		saveToolBarButton.setIcon(new ImageIcon(getClass().getResource(
                "img/disk.png")));
		saveToolBarButton.addActionListener(this);
		saveToolBarButton.setActionCommand("file_save");
		JButton saveAsToolBarButton = new JButton("Save As");
		saveAsToolBarButton.setIcon(new ImageIcon(getClass().getResource(
                "img/page_save.png")));
		saveAsToolBarButton.addActionListener(this);
		saveAsToolBarButton.setActionCommand("file_saveAs");

		JButton printToolBarButton = new JButton("Print");
		printToolBarButton.setIcon(new ImageIcon(getClass().getResource(
                "img/printer.png")));
		printToolBarButton.addActionListener(this);
		printToolBarButton.setActionCommand("file_print");

		JButton cutToolBarButton = new JButton(new DefaultEditorKit.CutAction());
		cutToolBarButton.setText("Cut");
		cutToolBarButton.setIcon(new ImageIcon(getClass().getResource(
                "img/cut.png")));
		cutToolBarButton.addActionListener(this);
		cutToolBarButton.setActionCommand("edit_cut");

		JButton copyToolBarButton = new JButton(
				new DefaultEditorKit.CopyAction());
		copyToolBarButton.setText("Copy");
		copyToolBarButton.setIcon(new ImageIcon(getClass().getResource(
                "img/page_copy.png")));
		copyToolBarButton.addActionListener(this);
		copyToolBarButton.setActionCommand("edit_copy");

		JButton pasteToolBarButton = new JButton(
				new DefaultEditorKit.PasteAction());
		pasteToolBarButton.setText("Paste");
		pasteToolBarButton.setIcon(new ImageIcon(getClass().getResource(
                "img/page_paste.png")));
		pasteToolBarButton.addActionListener(this);
		pasteToolBarButton.setActionCommand("edit_paste");

		JButton runToolBarButton = new JButton("Run");
		runToolBarButton.setIcon(new ImageIcon(getClass().getResource(
                "img/resultset_next.png")));
        runToolBarButton.addActionListener(this);
        runToolBarButton.setActionCommand("run_run");

		toolBar.add(newToolBarButton);
		toolBar.add(openToolBarButton);
		toolBar.add(closeToolBarButton);
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
		JScrollPane fileScrollPane = new JScrollPane(fileTree);

		leftSplitPane.add(fileScrollPane);
		leftSplitPane.setMinimumSize(new Dimension(0, 0));

		// rightSplitPane currently contains two tabbed panes for editing files
		// and output
		JSplitPane rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		this.editor = new Editor();
		this.editor.setPreferredSize(new Dimension(0, 400));

		JTabbedPane bottomTabbedPane = new JTabbedPane();

		// JEditorPane(s) used as a placeholder, could be changed in future
		this.consolePane = new JEditorPane();
        this.consolePane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        this.consolePane.setFont(new Font("Monaco", 0, 14));
        this.consolePane.setEditable(false);

		bottomTabbedPane.add(consolePane, "Console");

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
		FileWriter writer;

		if (action.equals("file_new")) {
			String title = "";
			while (title.length() < 1) {
				title = JOptionPane.showInputDialog("Enter a new file name:");
				if (title.length() < 1) {
					JOptionPane.showMessageDialog(null,
							"Please enter a valid file name.");
				}
				fileTree.addNode(new DefaultMutableTreeNode(new File(System.getProperty("user.home")+ File.separator + title + ".idiot")));
			}

			this.editor.newTab(title + ".idiot");
		} else if (action.equals("file_open")) {
			this.fileChooser.setFileFilter(new FileNameExtensionFilter("IDIOT",
					"idiot"));

			int returnValue = this.fileChooser.showOpenDialog(null);
			String filePath = String.valueOf(this.fileChooser.getSelectedFile());
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = new File(filePath);
				String fileContents = "";

				try {
					BufferedReader bufferedReader = new BufferedReader(
							new FileReader(file));

					String line;
					while ((line = bufferedReader.readLine()) != null) {
						fileContents = fileContents + line + "\n";
					}
					bufferedReader.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				this.editor.newTab(filePath.split("/")[filePath.split("/").length - 1]);
				this.editor.getLastTab().setText(fileContents);

				editor.setLastSaveDirectory(file.getAbsolutePath(),
				editor.getIndexOfLastTab());
				System.out.println(editor.getLastSaveDirectory(editor.getSelectedIndex()));
				this.fileTree.addNode(new DefaultMutableTreeNode(file));
			} else if (returnValue == JFileChooser.CANCEL_OPTION) {
			} else {
				JOptionPane.showMessageDialog(null, "Error opening file");
			}
			
		} else if (action.equals("file_close")) {
			int saved = 1;
			File file = new File(this.editor.getLastSaveDirectory(this.editor.getSelectedIndex()));
			String fileContents = "";

			try {
				BufferedReader bufferedReader = new BufferedReader(
						new FileReader(file));

				String line;
				while ((line = bufferedReader.readLine()) != null) {
					fileContents = fileContents + line + "\n";
				}
				bufferedReader.close();
			} catch (FileNotFoundException e1) {
				saved = 0;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(!fileContents.equals(editor.getActiveTab().getText()) || saved == 0)
			{
				System.out.println("not saved");
				int option = JOptionPane.showConfirmDialog(null, "File not saved. Would you like to save before quiting?");
				
				if(option == JOptionPane.YES_OPTION)
				{
					try {
						file.createNewFile();

						writer = new FileWriter(file);
						writer.write(editor.getActiveTab().getText());

						writer.flush();
						writer.close();
						//JOptionPane.showMessageDialog(null, file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					this.editor.removeTab(this.editor.getSelectedIndex());
				}
				else if(option == JOptionPane.NO_OPTION)
				{
					this.editor.removeTab(this.editor.getSelectedIndex());
				}
			}
			else{
				this.editor.removeTab(this.editor.getSelectedIndex());	
			}
		} else if (action.equals("file_save")) {
			try {
				File file = new File(editor.getLastSaveDirectory(editor
						.getSelectedIndex()));
				file.createNewFile();

				writer = new FileWriter(file);
				writer.write(editor.getActiveTab().getText());

				writer.flush();
				writer.close();
				//JOptionPane.showMessageDialog(null, file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (action.equals("file_saveAs")) {
			int returnValue = this.fileChooser.showSaveDialog(null);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(new File(this.editor.getLastSaveDirectory(this.editor.getSelectedIndex())));
			System.out.println(node);
			fileTree.removeNode(node);
			
			File file;
			
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				if (this.fileChooser.getSelectedFile().getName().substring(this.fileChooser.getSelectedFile().getName().length() - 6).equals(".idiot")) {
					file = new File(String.valueOf(this.fileChooser.getSelectedFile()));
				} else {
					file = new File(String.valueOf(this.fileChooser.getSelectedFile()) + ".idiot");
				}
				try {
					writer = new FileWriter(file);
					writer.write(editor.getActiveTab().getText());

					writer.flush();
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				editor.setLastSaveDirectory(file.getAbsolutePath(),
						editor.getSelectedIndex());
				System.out.println(editor.getLastSaveDirectory(editor
						.getSelectedIndex()));
				
				this.fileTree.addNode(new DefaultMutableTreeNode(file));
			}

		} else if (action.equals("file_print")) {
			// TODO: get printing working
			PrinterJob printerJob = PrinterJob.getPrinterJob();
			printerJob.printDialog();
		} else if (action.equals("file_quit")) {
			String[] buttons = { "Exit without saving", "Save and exit", "Cancel" };
			int warnopt = JOptionPane
					.showOptionDialog(
							null,
							"Are you sure you want to exit this program?\nAll unsaved progress will be lost.",
							"Warning", JOptionPane.WARNING_MESSAGE, 0, null,
							buttons, buttons[1]);

			if (warnopt == 0) {
				System.exit(0);
			}
			if (warnopt == 1) {
				for(int i = this.editor.getTabCount()-1; i >= 0; i--)
				{
					try {
						File file = new File(editor.getLastSaveDirectory(i));
						file.createNewFile();

						writer = new FileWriter(file);
						writer.write(editor.getActiveTab().getText());

						writer.flush();
						writer.close();
						//JOptionPane.showMessageDialog(null, file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.editor.removeTab(0);
				}
				System.exit(0);
			}
			if (warnopt == 2) {}

		}

		if (action.equals("edit_cut")) {
		} else if (action.equals("edit_copy")) {
		} else if (action.equals("edit_paste")) {
		} else if (action.equals("edit_selectAll")) {
			JOptionPane.showMessageDialog(null, "edit_selectAll");
		}

        if (action.equals("run_run")) {
            try {
                Interpreter interpreter = new Interpreter();
                this.setConsoleText(interpreter.interpret(editor.getActiveTab().getText()));
            } catch (ArrayIndexOutOfBoundsException e1) {
                JOptionPane.showMessageDialog(null, "There is no file open to run. Please open a file and try again.");
            }
        } else if (action.equals("run_debug")) {
            try {
                Interpreter interpreter = new Interpreter();
                this.setConsoleText(interpreter.interpret(editor.getActiveTab().getText(), true));
            } catch (ArrayIndexOutOfBoundsException e1) {
                JOptionPane.showMessageDialog(null, "There is no file open to debug. Please open a file and try again.");
            }
        }

		if (action.equals("help_viewHelp")) {
            Desktop desktop = Desktop.getDesktop();
            InputStream in = getClass().getResourceAsStream("pdf/user_manual.pdf");

            try {
                File file = File.createTempFile("user_manual", ".pdf");
                file.deleteOnExit();
                OutputStream out = new FileOutputStream(file);
                try {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                } finally {
                    out.close();
                }
                desktop.open(file);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
		} else if (action.equals("help_about")) {
			JOptionPane.showMessageDialog(null, "Version 4.0 \n" + "\n"
					+ "Created By: \n" + "Ben Potter, \n" + "Adam Gough, \n"
					+ "Keylan Norum, \n" + "Kaori Millar, \n"
					+ "Kuan Heng Kuo \n" + "\n" + "Icons provided by: \n"
					+ "http://www.famfamfam.com/lab/icons/silk/\n"
					+ "FamFamFam, 2015");
		}
	}

	public Editor getEditor()
	{
		return this.editor;
	}

    public void setConsoleText(String string) {
        this.consolePane.setText(string);
    }

    public String getConsoleText() {
        return this.consolePane.getText();
    }
	
}
