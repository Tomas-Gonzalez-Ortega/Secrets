import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SecretComp extends JComponent {

	// note: this can be done with no instance variables
	private final JTextField txtOut;
	private final JTextField txtInKey;
	private final JTextField txtInMsg;
	// IVs for the buttons is fine

	public SecretComp() {
		final Secrets secrets = SecretsFactory.makeSecrets();

		setLayout(new BorderLayout());
		txtOut = new JTextField();
		txtOut.setEditable(false);
		txtOut.setPreferredSize(new Dimension(600, 100));
		add(txtOut, BorderLayout.CENTER);

        // initialize & layout
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(3, 3));
		add(south, BorderLayout.SOUTH);

		south.add(new JLabel("key"));
		south.add(new JLabel("msg"));
		JButton btnEnc = new JButton("Encrypt");
		south.add(btnEnc);

		txtInKey = new JTextField();
		south.add(txtInKey);

		txtInMsg = new JTextField();
		south.add(txtInMsg);
		JButton btnDcrypt = new JButton("Decrypt");
		south.add(btnDcrypt);

		String[] bs = { "A", "B", "C" };
		for (String s : bs) {
			JButton b = new JButton(s);
			b.addActionListener(ae->{
				secrets.unlock(s);
				txtOut.setText(secrets.getMessage());
			});
			south.add(b);
		}

		txtInKey.addActionListener(ae->{
			if (secrets.getState() == Secrets.State.LOCKED) {
			    secrets.unlock(txtInKey.getText().trim());
			    txtOut.setText(secrets.getMessage());
			}
		});

		btnEnc.addActionListener(ae->{
			String enc = secrets.encrypt(txtInKey.getText(), txtInMsg.getText());
			txtOut.setText(enc);
		});

		btnDcrypt.addActionListener(ae->{
			String dec = secrets.decrypt(txtInKey.getText(), txtInMsg.getText());
			txtOut.setText(dec);
		});

		txtOut.setText(secrets.getMessage());
	}
}

/*
 * STEP 09 * Message from assignment.
            key:
    cipher-text: cgmMbvbyDONppeM2paS+7A==
     plain-text:

 * STEP 10 * Message sent to classmate.
            key:
    cipher-text:
     plain-text:

 * STEP 11 * Message received from classmate.
            key:
    cipher-text:
     plain-text:

 * STEP 12 * Message for marker.
            key:
    cipher-text:
 */
