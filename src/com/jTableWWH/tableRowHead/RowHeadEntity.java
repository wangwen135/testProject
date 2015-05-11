package com.jTableWWH.tableRowHead;

/**
 * 描述：行头实体对象
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE            PERSON         REASON
 *  1    2013-9-9      313921         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 313921
 * @since 1.0
 */
public class RowHeadEntity {

	private boolean auto;

	private int height;

	public RowHeadEntity() {
	}

	public RowHeadEntity(int height) {
		this.auto = false;
		this.height = height;
	}

	public RowHeadEntity(boolean auto, int height) {
		this.auto = auto;
		this.height = height;
	}

	public boolean isAuto() {
		return auto;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
