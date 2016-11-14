package application;

import javax.swing.JDialog;

public class JOptionPaneAutocloseThread implements Runnable {
	private JDialog jDialog;

	public JOptionPaneAutocloseThread(JDialog jDialog) {
		this.jDialog = jDialog;
	}

	@Override
	public void run() {
		long d = System.currentTimeMillis();
		long f = d + 3000;
		while (System.currentTimeMillis() < f)
			;
		jDialog.setVisible(false);
	}
}