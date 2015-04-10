import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class FileTree extends JTree
{
	private DefaultMutableTreeNode rootNode;
	private DefaultTreeModel model;
	private int nodeCount;
	private File fileHistory;
	
	public FileTree()
	{
		super(new DefaultMutableTreeNode("files"));
		this.setEditable(false);
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.setShowsRootHandles(true);
		model = (DefaultTreeModel) this.getModel();
		rootNode = (DefaultMutableTreeNode) model.getRoot();
		this.fileHistory = new File("File_Data");
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
		
		this.model.insertNodeInto(node, rootNode, rootNode.getChildCount());
		this.expandRow(this.getRowCount()-1);
		this.nodeCount += 1;
		this.saveFileTree();
	}
	public void removeNode(DefaultMutableTreeNode node)
	{
		
		for(int i = 0; i < this.nodeCount; i++)
		{
			System.out.println(this.rootNode.getChildAt(i) + " = " + node);
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
}