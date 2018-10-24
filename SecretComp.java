import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** A GUI class to encrypt and decrypt messages using a key.
    @author Jeremy Hilliker @ Langara
    @author Tomas Gonzalez Ortega
    @version 2017-07-12 22h00
    @see <a href="https://d2l.langara.bc.ca/d2l/lms/dropbox/user/folder_submit_files.d2l?db=51408&grpid=0&isprv=0&bp=0&ou=88736">a09: GUI - Secrets</a>
*/

public class SecretComp extends JComponent {

    // TODO place any instance variables here
	private final static String[] LETTERS = {"A", "B", "C"};

	public SecretComp() {

        // TODO get a Secrets object from the factory
		Secrets secrets = SecretsFactory.makeSecrets();

        // TODO set the initial layout
		setLayout(new BorderLayout());

		JTextField txtOutSecret = new JTextField();
		txtOutSecret.setEditable(false);

	    JPanel pnlInControls = new JPanel();
		pnlInControls.setLayout(new GridLayout(3,3));

		JLabel keyLabel = new JLabel("key");
		JLabel msgLabel = new JLabel("msg");
		JButton encryptButton = new JButton("Encrypt");
		JTextField keyText = new JTextField();
		JTextField msgText = new JTextField();
		JButton decryptButton = new JButton("Decrypt");
		JButton[] buttons = new JButton[LETTERS.length]; // create and populate the array buttons with the index of LETTERS
		for (int i = 0; i < LETTERS.length; i++) {
			buttons[i] = new JButton(LETTERS[i]);
		}

        // TODO add the output text box
		add(txtOutSecret);

        // TODO add the control panel
		pnlInControls.add(keyLabel);
		pnlInControls.add(msgLabel);
		pnlInControls.add(encryptButton);
		pnlInControls.add(keyText);
		pnlInControls.add(msgText);
		pnlInControls.add(decryptButton);
		for (int i = 0 ; i < LETTERS.length; i++) {
			pnlInControls.add(buttons[i]);
		}

		add(pnlInControls, BorderLayout.SOUTH);

        // TODO attach listeners to each of the buttons and the key text field
        //      use lambda expressions
		for(JButton index : buttons) {
			index.addActionListener(event -> {
				secrets.unlock(index.getText());
				txtOutSecret.setText(secrets.getMessage());
			});
		}

		keyText.addActionListener(event -> {
			if(secrets.getState() == Secrets.State.LOCKED) {
				secrets.unlock(keyText.getText());
				txtOutSecret.setText(secrets.getMessage());
			}
		});

		encryptButton.addActionListener(event -> {
			txtOutSecret.setText(
				secrets.encrypt(keyText.getText(), msgText.getText()));
		});

		decryptButton.addActionListener(event -> {
			txtOutSecret.setText(
				secrets.decrypt(keyText.getText(), msgText.getText()));
		});

        // TODO set the txt field to be whatever is returned by getMessage on Secrets
		txtOutSecret.setText(secrets.getMessage());
		add(txtOutSecret, BorderLayout.CENTER);
	}
}

/*
 * STEP 09 * Message from assignment.
            key: The.Secret.Key
    cipher-text: cgmMbvbyDONppeM2paS+7A==
     plain-text: You did it!

 * STEP 10 * Message sent to classmate.
            key: Tomas.Hiroki
    cipher-text: iOfzvFm/PA2tnkX6WvHzIgtEEyZYrevM+5i8vJyRkEE=
     plain-text: Man I'm tired X___x

 * STEP 11 * Message received from classmate.
            key: Hiro.Tom
    cipher-text: ByQQ3z+Akuqkzff7bt2jLw==
     plain-text: Hey what's up?

 * STEP 12 * Message for marker.
            key: Tom.Marker
    cipher-text: KwrcwMDRz1zpr32CSjwBle9uJQVx9LNqPPhCAU+TVoT5AhJioT7eSLE9IWi9Ln1R5EdfVvYS8aGu667BXSGv5g==
 */
