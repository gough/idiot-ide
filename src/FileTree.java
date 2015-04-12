import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class FileTree extends JTree implements MouseListener, ActionListener
{
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel model;
	private int nodeCount;
	private File fileHistory;
	
	public FileTree()
	{
		super(new DefaultMutableTreeNode("Files"));
		this.setEditable(false);
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.setShowsRootHandles(true);
		model = (DefaultTreeModel) this.getModel();
		rootNode = (DefaultMutableTreeNode) model.getRoot();
		this.addMouseListener(this);
		this.fileHistory = new File("file_history");
		try
		{
			if(fileHistory.exists())
			{
				BufferedReader bufferedReader = new BufferedReader(
						new FileReader(fileHistory));

				String line;
				while ((line = bufferedReader.readLine()) != null) {
					this.model.insertNodeInto(new DefaultMutableTreeNode(line), rootNode, rootNode.getChildCount());
					this.expandRow(this.getRowCount()-1);
					nodeCount += 1;
				}
				bufferedReader.close();	
			}
			else
			{
				fileHistory.createNewFile();
			}
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public FileTree(DefaultMutableTreeNode node)
	{
		super(node);
		this.setEditable(false);
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.setShowsRootHandles(true);
		rootNode = node;
	}
	
	public void addNode(DefaultMutableTreeNode node)
	{
		boolean found = false;
		for(int i = 0; i < this.nodeCount; i++)
		{
			if(this.rootNode.getChildAt(i).toString().equals(node.toString()))
			{
				found = true;
			}
		}
		if(!found)
		{
			this.model.insertNodeInto(node, rootNode, rootNode.getChildCount());
			this.expandRow(this.getRowCount()-1);
			this.nodeCount += 1;
			this.saveFileTree();
		}
	}
	public void removeNode(DefaultMutableTreeNode node)
	{
		
		for(int i = 0; i < this.nodeCount; i++)
		{
			if(this.rootNode.getChildAt(i).toString().equals(node.toString()))
			{
				this.model.removeNodeFromParent((MutableTreeNode) this.rootNode.getChildAt(i));
				this.nodeCount -= 1;
			}
		}
		this.saveFileTree();
	}

	public DefaultMutableTreeNode getRootNode() {
		return this.rootNode;
	}
	private void saveFileTree()
	{
		FileWriter writer;
		try {
			this.fileHistory.createNewFile();

		writer = new FileWriter(this.fileHistory);
		for(int i = 0; i < this.rootNode.getChildCount(); i++)
		{
			writer.write(this.rootNode.getChildAt(i).toString()+ "\n");
		}
		writer.flush();
		writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		JPopupMenu menu = new JPopupMenu();
		
		JMenuItem open = new JMenuItem("Open Selected");
		open.addActionListener(this);
		open.setActionCommand("file_open");
		
		JMenuItem delete = new JMenuItem("Delete Selected");
		delete.addActionListener(this);
		delete.setActionCommand("file_delete");
		
		JMenuItem newFile = new JMenuItem("New File");
		newFile.addActionListener(this);
		newFile.setActionCommand("file_new");
		
		menu.add(open);
		menu.add(delete);
		menu.add(newFile);
		
		if (e.getClickCount() == 2 && e.getButton() != e.BUTTON3) {
			//System.out.println("double click");
			File file = new File(this.getLastSelectedPathComponent().toString());
			String fileContents = "";
			
			if(Main.getGUI().getEditor().indexOfTab(this.getLastSelectedPathComponent().toString().substring(this.getLastSelectedPathComponent().toString().lastIndexOf('/'))) != -1)
			{
				Main.getGUI().getEditor().setSelectedIndex(Main.getGUI().getEditor().indexOfTab(this.getLastSelectedPathComponent().toString().substring(this.getLastSelectedPathComponent().toString().lastIndexOf('/'))));
			}
			
			else
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(
							new FileReader(file));

					String line;
					while ((line = bufferedReader.readLine()) != null) {
						fileContents = fileContents + line + "\n";
					}
					bufferedReader.close();
				} catch (FileNotFoundException e1) {
					//e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(Main.getGUI().getEditor().indexOfTab(file.getPath().split("/")[file.getPath().split("/").length - 1]) != -1)
				{
					Main.getGUI().getEditor().setSelectedIndex(Main.getGUI().getEditor().indexOfTab(file.getPath().split("/")[file.getPath().split("/").length - 1]));
				}
				else
				{
					Main.getGUI().getEditor().newTab(file.getPath().split("/")[file.getPath().split("/").length - 1]);
					Main.getGUI().getEditor().getLastTab().setText(fileContents);
					Main.getGUI().getEditor().setLastSaveDirectory(file.getAbsolutePath(),Main.getGUI().getEditor().getIndexOfLastTab());
					//System.out.println(Main.getGUI().getEditor().getLastSaveDirectory(Main.getGUI().getEditor().getSelectedIndex()));
				}
			}
		}
		else if(e.getButton() == e.BUTTON3)
		{
			if (SwingUtilities.isRightMouseButton(e))
			{
				int row = this.getClosestRowForLocation(e.getX(), e.getY());
				this.setSelectionRow(row);
				System.out.println(row + " = " + this.getSelectionRows()[0]);
				if(!menu.isVisible())
				{
					if(row == this.getSelectionRows()[0])
					{
						this.setComponentPopupMenu(menu);
					}
				}
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();
		if(action.equals("file_open"))
		{
			File file = new File(this.getLastSelectedPathComponent().toString());
			String fileContents = "";
			
			if(Main.getGUI().getEditor().indexOfTab(this.getLastSelectedPathComponent().toString().substring(this.getLastSelectedPathComponent().toString().lastIndexOf('/'))) != -1)
			{
				Main.getGUI().getEditor().setSelectedIndex(Main.getGUI().getEditor().indexOfTab(this.getLastSelectedPathComponent().toString().substring(this.getLastSelectedPathComponent().toString().lastIndexOf('/'))));
			}
			
			else
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(
							new FileReader(file));

					String line;
					while ((line = bufferedReader.readLine()) != null) {
						fileContents = fileContents + line + "\n";
					}
					bufferedReader.close();
				} catch (FileNotFoundException e1) {
					//e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(Main.getGUI().getEditor().indexOfTab(file.getPath().split("/")[file.getPath().split("/").length - 1]) != -1)
				{
					Main.getGUI().getEditor().setSelectedIndex(Main.getGUI().getEditor().indexOfTab(file.getPath().split("/")[file.getPath().split("/").length - 1]));
				}
				else
				{
					Main.getGUI().getEditor().newTab(file.getPath().split("/")[file.getPath().split("/").length - 1]);
					Main.getGUI().getEditor().getLastTab().setText(fileContents);
					Main.getGUI().getEditor().setLastSaveDirectory(file.getAbsolutePath(),Main.getGUI().getEditor().getIndexOfLastTab());
					//System.out.println(Main.getGUI().getEditor().getLastSaveDirectory(Main.getGUI().getEditor().getSelectedIndex()));
				}
			}
		}
		else if(action.equals("file_delete"))
		{
			int saved = 1;
			//System.out.println(this.getLastSelectedPathComponent().toString());
			File file = new File(this.getLastSelectedPathComponent().toString());
			String fileContents = "";
			FileWriter writer;

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
						
			if((Main.getGUI().getEditor().getTab(this.getLastSelectedPathComponent().toString().substring(this.getLastSelectedPathComponent().toString().lastIndexOf("/")+1)) != null && !fileContents.equals(Main.getGUI().getEditor().getTab(this.getLastSelectedPathComponent().toString().substring(this.getLastSelectedPathComponent().toString().lastIndexOf("/")+1)).getText())) || saved == 0)
			{
				System.out.println("not saved");
				int option = JOptionPane.showConfirmDialog(null, "File not saved. Would you like to save before quiting?");
				
				if(option == JOptionPane.YES_OPTION)
				{
					try {
						file.createNewFile();

						writer = new FileWriter(file);
						writer.write(Main.getGUI().getEditor().getActiveTab().getText());
						writer.flush();
						writer.close();
						JOptionPane.showMessageDialog(null, file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					Main.getGUI().getEditor().removeTab(this.getLastSelectedPathComponent().toString().substring(this.getLastSelectedPathComponent().toString().lastIndexOf("/")+1));
					this.removeNode((DefaultMutableTreeNode) this.getLastSelectedPathComponent());
				}
				else if(option == JOptionPane.NO_OPTION)
				{
					Main.getGUI().getEditor().removeTab(this.getLastSelectedPathComponent().toString().substring(this.getLastSelectedPathComponent().toString().lastIndexOf("/")+1));
					this.removeNode((DefaultMutableTreeNode) this.getLastSelectedPathComponent());
				}
			}
			else{
				Main.getGUI().getEditor().removeTab(this.getLastSelectedPathComponent().toString().substring(this.getLastSelectedPathComponent().toString().lastIndexOf("/")+1));
				System.out.println((DefaultMutableTreeNode)this.getLastSelectedPathComponent());
				this.removeNode((DefaultMutableTreeNode)this.getLastSelectedPathComponent());
			}
		}
		else if(action.equals("file_new"))
		{
			String title = "";
			while (title.length() < 1) {
				title = JOptionPane.showInputDialog("Enter a new file name:");
				if (title.length() < 1) {
					JOptionPane.showMessageDialog(null,
							"Please enter a valid file name.");
				}
				this.addNode(new DefaultMutableTreeNode(new File(System.getProperty("user.home")+ File.separator + title + ".idiot")));
			}

			Main.getGUI().getEditor().newTab(title + ".idiot");
		}
	}
}