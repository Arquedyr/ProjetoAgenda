package agenda.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import agenda.io.AgendaIO;
import agenda.utils.AgendaUtils;
import agenda.utils.PeriodicidadeEnum;
import agenda.vo.Evento;

import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class CadastroEventoPanel extends JPanel {
	private JTextField tfDescEvento;
	private JTextField tfDtEvento;
	private JTextField tfEmailEvento;
	private JCheckBox chckbxAlarme;
	private JRadioButton rdbtnUmaVez;
	private JRadioButton rdbtnSemanal;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Object listaEventos;


	public CadastroEventoPanel(ListaEventosPanel listaEventosPanel) {
		this.listaEventos = listaEventos;
		setLayout(null);
		
		JLabel lblDescEvento = new JLabel("Descrição do Evento");
		lblDescEvento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescEvento.setBounds(10, 15, 119, 14);
		add(lblDescEvento);
		
		tfDescEvento = new JTextField();
		tfDescEvento.setBounds(10, 40, 383, 20);
		add(tfDescEvento);
		tfDescEvento.setColumns(10);
		
		JLabel lblDtEvento = new JLabel("Data do Evento");
		lblDtEvento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDtEvento.setBounds(10, 71, 98, 14);
		add(lblDtEvento);
		
		tfDtEvento = new JTextField();
		tfDtEvento.setBounds(140, 71, 108, 20);
		add(tfDtEvento);
		tfDtEvento.setColumns(10);
		
		JLabel lblEncaminharEmail = new JLabel("Encaminhar E-mail");
		lblEncaminharEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEncaminharEmail.setBounds(10, 105, 108, 14);
		add(lblEncaminharEmail);
		
		tfEmailEvento = new JTextField();
		tfEmailEvento.setBounds(138, 102, 228, 20);
		add(tfEmailEvento);
		tfEmailEvento.setColumns(10);
		
		JLabel lblPeriodicidadeEvento = new JLabel("Periodicidade do Evento");
		lblPeriodicidadeEvento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPeriodicidadeEvento.setBounds(10, 143, 152, 14);
		add(lblPeriodicidadeEvento);
		
		JRadioButton rdbUmavez = new JRadioButton("Uma vez");
		buttonGroup.add(rdbUmavez);
		rdbUmavez.setBounds(168, 139, 73, 23);
		add(rdbUmavez);
		
		JRadioButton rdbSemanal = new JRadioButton("Semanal");
		buttonGroup.add(rdbSemanal);
		rdbSemanal.setBounds(243, 139, 73, 23);
		add(rdbSemanal);
		
		JRadioButton rdbMensal = new JRadioButton("Mensal");
		buttonGroup.add(rdbMensal);
		rdbMensal.setBounds(318, 139, 73, 23);
		add(rdbMensal);
		
		JCheckBox chckbxAlarme = new JCheckBox("Alarme");
		chckbxAlarme.setBounds(10, 175, 73, 23);
		add(chckbxAlarme);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(102, 175, 89, 23);
		add(btnSalvar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(227, 175, 89, 23);
		add(btnLimpar);
		
		btnSalvar.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent arg0) {
			  chamaCadastroEvento();
			 }
		});

			btnLimpar.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent arg0) {
			  limparCampos();
			 }
		});
			
	}
	
	private void limparCampos(){
		  tfDtEvento.setText("");
		  tfDescEvento.setText("");
		  chckbxAlarme.setSelected(false);
		  tfEmailEvento.setText("");
		  rdbtnUmaVez.setSelected(true);
		}
	
	private void chamaCadastroEvento(){
		  AgendaIO io = new AgendaIO();
		  Evento evento = new Evento();
		  ListaEventosPanel listaEventos = new ListaEventosPanel();

		  Object[] novaLinha = new Object[5];

		  evento.setDataEvento(AgendaUtils.getDateFromString(tfDtEvento.getText()));
		  evento.setDescEvento(tfDescEvento.getText());
		  evento.setAlarme(chckbxAlarme.isSelected() ? 1 : 0);
		  evento.setEmailEncaminhar(tfEmailEvento.getText());

		  novaLinha[0] = tfDtEvento.getText();
		  novaLinha[1] = tfDescEvento.getText();
		  novaLinha[4] = chckbxAlarme.isSelected() ? "LIGADO" : "DESLIGADO";
		  novaLinha[3] = tfEmailEvento.getText();

		  if(rdbtnUmaVez.isSelected()){
		   evento.setPeriodicidade(PeriodicidadeEnum.UNICO);
		   novaLinha[2] = PeriodicidadeEnum.UNICO;
		  }
		  else if(rdbtnSemanal.isSelected()){
		   evento.setPeriodicidade(PeriodicidadeEnum.SEMANAL);
		   novaLinha[2] = PeriodicidadeEnum.SEMANAL;
		  }
		  else {
		   evento.setPeriodicidade(PeriodicidadeEnum.MENSAL);
		   novaLinha[2] = PeriodicidadeEnum.MENSAL;
		  }

		  try {
		   io.gravarEvento(evento);
		  }catch(Exception ex){
		   JOptionPane.showMessageDialog(null, "ERRO", ex.getMessage(),
		   JOptionPane.ERROR_MESSAGE);
		  }
		  listaEventos.addNewRow(novaLinha);
		  limparCampos();
		}
}

