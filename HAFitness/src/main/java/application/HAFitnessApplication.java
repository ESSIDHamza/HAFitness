package application;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HAFitnessApplication {
	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		JOptionPane jOptionPane = new JOptionPane("Lancement de l'application en cours...",
				JOptionPane.INFORMATION_MESSAGE);
		JDialog jDialog = jOptionPane.createDialog(null, "HAFitness");
		jDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		new Thread(new JOptionPaneAutocloseThread(jDialog)).start();
		jDialog.setVisible(true);
		try {
			ApplicationContext ac = SpringApplication.run(HAFitnessApplication.class, args);
			JOptionPane.showMessageDialog(null, "Lancez votre navigateur Web préféré et allez sur :\nlocalhost:8080",
					"HAFitness", JOptionPane.INFORMATION_MESSAGE);
		} catch (RuntimeException e) {
			JOptionPane.showMessageDialog(null, "Votre application est déjà lancée !\nAllez sur : localhost:8080",
					"HAFitness", JOptionPane.ERROR_MESSAGE);
		}
	}
}