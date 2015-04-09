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
	
	public FileTree()
	{
		super(new DefaultMutableTreeNode("files"));
		this.setEditable(false);
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.setShowsRootHandles(true);
		rootNode = (DefaultMutableTreeNode)this.getModel().getRoot();
		model = (DefaultTreeModel) this.getModel();
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
	}

	public DefaultMutableTreeNode getRootNode() {
		return this.rootNode;
	}
}