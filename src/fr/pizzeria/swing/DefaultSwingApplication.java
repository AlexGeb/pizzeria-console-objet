package fr.pizzeria.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/** Classe qui centralise des fonctions basiques afin de créer une application SWING simplement
 * @author DIGINAMIC
 */
public abstract class DefaultSwingApplication {
	private JFrame frame;
	private JPanel contentPane;
	private JTextArea textArea;
	private ApplicationContext context;

	private int defaultButtonWidth = 50;
	private int defaultLabelWidth = 300;
	private int defaultComponentHeight = 30;
	private int defaultSpace = 10;

	/** Méthode obligatoire qui permet d'initialiser le contexte, i.e. stocké des objets
	 * @param context contexte de l'application
	 */
	public abstract void initContext(ApplicationContext context);
	
	/**
	 * Méthode à implémenter dans la classe fille et qui initialise les composants de l'interface (libellé, boutons, etc.)
	 */
	public abstract void configureUI();

	/** Méthode qui intercepte le choix de l'utilisateur (i.e. bouton) sur lequel il a cliqué
	 * @param choix identifiant de l'option de menu
	 * @param context contexte de l'application
	 */
	public abstract void execute(int choix, ApplicationContext context);

	/** Démarre l'application
	 * @param classe classe de l'application main
	 */
	public static final void start(Class<? extends DefaultSwingApplication> classe) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					classe.newInstance().configureApplication();
				} catch (InstantiationException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
	
	protected final void configureApplication(){
		context = new ApplicationContext();
		initContext(context);
		configureUI();
	}

	/** Permet de définir l'emplacement de la zone de texte (point haut à gauche)
	 * @param x abscisse
	 * @param y ordonnée
	 */
	protected final void setTextArea(int x, int y) {
		textArea = new JTextArea();
		textArea.setLocation(x, y);
		textArea.setSize(contentPane.getWidth() - 2 * x, contentPane.getHeight() - y - x);
		textArea.setBackground(Color.WHITE);
		textArea.setLineWrap(true);
		contentPane.add(textArea);
	}

	/** Configuration initiale de l'application avec définition du titre, de la hauteur de la fenêtre et de la largeur
	 * @param title titre
	 * @param width largeur
	 * @param height hauteur
	 */
	protected final void configureWindow(String title, int width, int height) {

		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		
		Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - frame.getWidth()) / 2;
        int iCoordY = (objDimension.height - frame.getHeight()) / 2;
        frame.setLocation(iCoordX, iCoordY); 
		
		contentPane = new JPanel();
		contentPane.setOpaque(true);
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setLayout(null);

		Border padding = BorderFactory.createEmptyBorder(0, 0, 0, 0);
		contentPane.setBorder(padding);

		frame.setContentPane(contentPane);

		frame.setVisible(true);

	}

	/** Ajoute un bouton à l'interface
	 * @param choix identifiant de l'option de menu
	 * @param libelle libellé de l'option de menu
	 * @param x coordonnées en X
	 * @param y coordonnées en Y
	 */
	protected final void addButton(int choix, String libelle, int x, int y) {
		JButton button = new JButton(Integer.toString(choix));
		button.setSize(defaultButtonWidth, defaultComponentHeight);
		button.setLocation(x, y);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String choix = ((JButton) e.getSource()).getText();
				execute(Integer.parseInt(choix), context);
			}
		});

		JLabel label = new JLabel(libelle);
		label.setLocation(x + defaultButtonWidth + defaultSpace, y);
		label.setSize(defaultLabelWidth, defaultComponentHeight);

		contentPane.add(button);
		contentPane.add(label);
	}

	/** Pose une question à l'utilisateur et retourne la valeur sous forme de chaine de caractères
	 * @param code code
	 * @return String
	 */
	protected String ask(String code) {

		JTextField field = new JTextField(10);

		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbcLabel = new GridBagConstraints();
		gbcLabel.gridx = 0;
		gbcLabel.gridy = 1;
		gbcLabel.anchor = GridBagConstraints.WEST;
		gbcLabel.weightx = 0.7;

		myPanel.add(new JLabel(code), gbcLabel);

		GridBagConstraints gbcField = new GridBagConstraints();
		gbcField.gridx = 1;
		gbcField.gridy = 1;

		myPanel.add(field, gbcField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			return field.getText();
		}
		return null;
	}

	/** Pose des questions à l'utilisateur et stocke les réponses dans une instance d'Input
	 * @param codes codes des questions
	 * @return {@link Input}
	 */
	protected Input askMultiple(String... codes) {
		Input input = new Input(codes);

		JTextField[] fields = new JTextField[codes.length];

		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridBagLayout());
		for (int i = 0; i < codes.length; i++) {
			GridBagConstraints gbcLabel = new GridBagConstraints();
			gbcLabel.gridx = 0;
			gbcLabel.gridy = i;
			gbcLabel.anchor = GridBagConstraints.WEST;
			gbcLabel.weightx = 0.7;

			myPanel.add(new JLabel(codes[i]), gbcLabel);

			GridBagConstraints gbcField = new GridBagConstraints();
			gbcField.gridx = 1;
			gbcField.gridy = i;
			fields[i] = new JTextField(10);

			myPanel.add(fields[i], gbcField);
		}

		int result = JOptionPane.showConfirmDialog(null, myPanel, "", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			for (int i = 0; i < codes.length; i++) {
				input.putValue(codes[i], fields[i].getText());
			}
		}
		return input;
	}

	/** Affiche un message, suivi d'un retour à la ligne, dans la zone d'échanges
	 * @param message message à afficher
	 */
	protected void println(String string) {
		print(string + "\n");
	}
	
	/** Affiche un message, suivi d'un retour à la ligne, dans la zone d'échanges
	 * @param message message à afficher
	 */
	protected void println(Object obj) {
		print(obj.toString() + "\n");
	}

	/** Affiche un message dans la zone d'échanges
	 * @param message message à afficher
	 */
	protected void print(String message) {
		checkTextArea();
		textArea.append(message);
	}
	
	/** Affiche un message dans la zone d'échanges
	 * @param message message à afficher
	 */
	protected void print(Object obj) {
		checkTextArea();
		textArea.append(obj.toString());
	}

	/** Efface le contenu de la zone d'échanges
	 */
	protected void clearScreen() {
		checkTextArea();
		textArea.setText("");
	}

	/** Méthode qui vérifie que la zone de texte a bien été initialisée
	 * 
	 */
	private void checkTextArea() {
		if (textArea==null){
			throw new RuntimeException("La zone d'affiche de texte n'a pas été initialisée. Veuillez utiliser la méthode setTextArea(...)");
		}
	}
}