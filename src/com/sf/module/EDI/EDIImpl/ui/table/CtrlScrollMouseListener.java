package com.sf.module.EDI.EDIImpl.ui.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import com.sf.module.EDI.EDIImpl.TempletDragEditor;

/**
 * 描述：监听鼠标按下和放开，以在合适的时候调整表格
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2012-6-5      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class CtrlScrollMouseListener extends MouseAdapter {

	private TempletDragEditor editor;
	private EdiTable[] tables;

	public CtrlScrollMouseListener(TempletDragEditor dragEditor,
			EdiTable... myTables) {
		this.editor = dragEditor;
		this.tables = myTables;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		setScroll(true);
		if (tables[0].isCodeMoved()) {
			List<Integer> widthList = editor.getColumnWidth();
			editor.setColumnWidth(widthList);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		setScroll(false);
		tables[0].setCodeMoved(false);
	}

	private void setScroll(boolean isScroll) {
		for (EdiTable tab : tables) {
			tab.setCodeScroll(isScroll);
		}
	}
}
