package gameDatabase;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class ResultMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton queryButton;
	private JTable table;
	private JScrollPane scrollPane;
	private	String inputFile = ".\\games.json";
	private DatabaseManager DBManager = new DatabaseManager(inputFile);
	private Queries queries = new Queries(DBManager);
	@SuppressWarnings("rawtypes")
	private JList list;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultMenu frame = new ResultMenu();
					frame.setTitle("Andreas game database");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("165px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("97px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("28px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(69dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(69dlu;default)"),}));
		
		textField = new JTextField();
		contentPane.add(textField, "1, 2, right, bottom");
		textField.setColumns(16);
		
		
		
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "5, 2, 1, 5, fill, fill");
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		String listItems[] = {"Games by Genre", "Number of games published by", "Games on platforms made by", "Games made by publisher from", "Games ordered by"};
		
		queryButton = new JButton("Query");
		queryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selected = list.getSelectedIndex();
				if (selected == 0) {
					fillTable(queries.gamesInAGenre(textField.getText()));
				} else if (selected == 1) {
					fillTable(queries.numberOfGamesPublished(textField.getText()));
				} else if (selected == 2) {
					fillTable(queries.gamesOnPlatformsMadeBy(textField.getText()));
				} else if (selected == 3) {
					fillTable(queries.gamesWithPublisherFrom(textField.getText()));
				} else if (selected == 4) {
					fillTable(queries.gamesInOrder(textField.getText()));
				}
			}
		});
		contentPane.add(queryButton, "3, 2, left, bottom");
		list = new JList(listItems);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(list, "1, 4, right, top");
		
	}

	public void fillTable(DefaultTableModel result) {
		table.setModel(result);
		
	}

}
