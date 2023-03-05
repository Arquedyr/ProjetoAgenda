package agenda.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class AgendaFrame extends JFrame {
 private static final long serialVersionUID = 1L;
 private JPanel contentPane;
 public AgendaFrame() {
  setTitle("Agenda");
  setResizable(false);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 450, 300);
  contentPane = new JPanel();
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  contentPane.setLayout(new BorderLayout(0, 0));
  setContentPane(contentPane);

  JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
  ListaEventosPanel listaEventosPanel = new ListaEventosPanel();
  tabbedPane.addTab("Cadastro de Eventos",
  new CadastroEventoPanel(listaEventosPanel));
  tabbedPane.addTab("Lista de Eventos", listaEventosPanel);

  contentPane.add(tabbedPane, BorderLayout.CENTER);

  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
}