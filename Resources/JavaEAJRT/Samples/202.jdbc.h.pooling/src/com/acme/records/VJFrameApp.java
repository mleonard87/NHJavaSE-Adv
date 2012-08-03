package com.acme.records;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VJFrameApp extends JFrame {
	private static final long serialVersionUID = 1L;
	private static int x;
	private static int y;
	private VMJFrameApp viewModel;
	private Controller controller;
	private VJPanelBeanRecord recordView;
	private JButton bGet, bPut;

	private class Controller implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if ("put".equals(e.getActionCommand())) {
				viewModel.put();
			} else if ("get".equals(e.getActionCommand())) {
				viewModel.get();
				recordView.setModel(viewModel.currentBeanRecord());
				pack();
			}
		}
	}

	public VJFrameApp() {
		super("Acme Records App");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout());
		this.controller = new Controller();
		this.recordView = new VJPanelBeanRecord();
		this.bGet = new JButton("get");
		this.bPut = new JButton("put");
		this.getContentPane().add(this.recordView);
		this.getContentPane().add(this.bGet);
		this.getContentPane().add(this.bPut);
		bGet.addActionListener(controller);
		bPut.addActionListener(controller);
		this.setBounds(x, y, 0, 0);
		if (x > 400) {
			x = 0;
			y += 200;
		} else {
			x += 500;
		}
		this.pack();
		this.setVisible(true);
	}

	public void setModel(VMJFrameApp viewModel) {
		this.viewModel = viewModel;
		this.recordView.setModel(this.viewModel.currentBeanRecord());
		pack();
	}
}