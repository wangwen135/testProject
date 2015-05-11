package com.swing.edi.test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.swing.edi.reportModel.area.cell.entity.BaseEntity;
import com.swing.edi.reportModel.area.cell.entity.IEntity;

public class aaaaaaa {
	public static void main(String[] args) {
		BaseEntity b = new BaseEntity("key", "value","aa");
		System.out.println(b);
		
		System.out.println(b instanceof IEntity);
	}
}
