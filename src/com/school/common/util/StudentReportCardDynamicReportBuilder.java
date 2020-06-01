/**
 * @author		Monu Kumar
 * @version		1.0
 */
package com.school.common.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

public class StudentReportCardDynamicReportBuilder {
	// The prefix used in defining the field name that is later used by the
	// JasperFillManager
	public static final String COL_EXPR_PREFIX = "col";

	// The prefix used in defining the column header name that is later used by
	// the JasperFillManager
	public static final String COL_HEADER_EXPR_PREFIX = "header";

	// The page width for a page in portrait mode with 10 pixel margins
	private static int TOTAL_PAGE_WIDTH = 545;

	// The whitespace between columns in pixels
	private final static int SPACE_BETWEEN_COLS = 0;

	// The height in pixels of an element in a row and column
	private final static int COLUMN_HEIGHT = 13;

	// The total height of the column header or detail band
	private final static int BAND_HEIGHT = 20;

	// The JasperDesign object is the internal representation of a report
	private JasperDesign jasperDesign;
	
	private Map<String, Object> reportCardData;
	
	private Map<Integer, Integer> columnWidthMap = new HashMap<Integer, Integer>();

	public StudentReportCardDynamicReportBuilder(JasperDesign jasperDesign, Map<String, Object> reportCardData) {
		this.jasperDesign = jasperDesign;
		this.reportCardData = reportCardData;
	}
	
	public StudentReportCardDynamicReportBuilder(JasperDesign jasperDesign, int pageWidth, Map<String, Object> reportCardData) {
		this.jasperDesign = jasperDesign;
		this.TOTAL_PAGE_WIDTH = pageWidth;
		this.reportCardData = reportCardData;
	}


	@SuppressWarnings({ "unchecked", "deprecation" })
	public int addDynamicHeaders() throws JRException {
		JRDesignBand headerBand = (JRDesignBand) jasperDesign.getColumnHeader();

		JRDesignStyle normalStyle = getNormalStyle();
		JRDesignStyle columnHeaderStyle = getColumnHeaderStyle();
		jasperDesign.addStyle(normalStyle);
		jasperDesign.addStyle(columnHeaderStyle);
		
		int xPos = jasperDesign.getLeftMargin() - 3; // Current Jasper file Columns Total Width
		
		//int columnWidth = 30;// (TOTAL_PAGE_WIDTH - (SPACE_BETWEEN_COLS *
								// (numColumns - 1))) / numColumns;
		int dynamicHeadersWidth = 1;
		List<String> headers = (List<String>) reportCardData.get("headers");
		for (int i = 0; i < headers.size(); i++) {
			int columnWidth = 25;
			// Create a Header Field
			/*JRDesignField headerField = new JRDesignField();
			headerField.setName(COL_HEADER_EXPR_PREFIX + i);
			headerField.setValueClass(java.lang.String.class);
			jasperDesign.addField(headerField);*/

			// Add a Header Field to the headerBand
			String headerName = headers.get(i);
			if("Subjects".equalsIgnoreCase(headerName))
				columnWidth = 60;
			else if(headerName.startsWith("Total"))
				columnWidth = 35;
			else if(headerName.startsWith("Term"))
				columnWidth = 30;
			else if(headerName.startsWith("Overall"))
				columnWidth = 40;
			
			columnWidthMap.put(i, columnWidth);
			JRDesignTextField colHeaderField = new JRDesignTextField();
			colHeaderField.setX(xPos);
			colHeaderField.setY(1);
			colHeaderField.setWidth(columnWidth);
			colHeaderField.setHeight(BAND_HEIGHT);
			colHeaderField.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
			colHeaderField.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
			colHeaderField.setStyle(columnHeaderStyle);
			colHeaderField.setBlankWhenNull(true);
			colHeaderField.setMode(ModeEnum.OPAQUE);
			JRDesignExpression headerExpression = new JRDesignExpression();
			headerExpression.setValueClass(java.lang.String.class);
			headerExpression.setText("$R{" + headerName + "}");
			colHeaderField.setExpression(headerExpression);
			styleBox(colHeaderField);

			colHeaderField.setBlankWhenNull(true);
			colHeaderField.setStretchType(StretchTypeEnum.RELATIVE_TO_TALLEST_OBJECT);
			colHeaderField.setStretchWithOverflow(true);
			colHeaderField.setPositionType(PositionTypeEnum.FLOAT);
			colHeaderField.setPrintWhenDetailOverflows(true);
			
			//colHeaderField.setRemoveLineWhenBlank(true);
			headerBand.addElement(colHeaderField);

			xPos = xPos + columnWidth + SPACE_BETWEEN_COLS;
			
			dynamicHeadersWidth += columnWidth + SPACE_BETWEEN_COLS;
		}
		jasperDesign.setColumnHeader(headerBand);
		dynamicHeadersWidth += jasperDesign.getLeftMargin() + jasperDesign.getRightMargin();
		return dynamicHeadersWidth;
	}
	
	
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public int addDynamicColumns() throws JRException {
		int noOfDynamicColumns = 0;
		JRBand[] bands = jasperDesign.getDetailSection().getBands();
		JRDesignBand detailBand = null;
		boolean isNewdetailBand = false;
		/*
		 * Assuming, we have to append the columns into first Band of
		 * DetailSection
		 */
		if (bands != null && bands.length > 0) {
			detailBand = (JRDesignBand) bands[0];
		} else {
			detailBand = new JRDesignBand();
			isNewdetailBand = true;
		}

		JRDesignStyle normalStyle = getNormalStyle();
		/*jasperDesign.addStyle(normalStyle);
		jasperDesign.addStyle(columnHeaderStyle);*/
		
		int xPos = jasperDesign.getLeftMargin() - 3; // Current Jasper file Columns Total Width
		int xPosNew = xPos;
		//int columnWidth = 30;// (TOTAL_PAGE_WIDTH - (SPACE_BETWEEN_COLS *
								// (numColumns - 1))) / numColumns;

		List<List<String>> rowsData = (List<List<String>>) reportCardData.get("rowsData");
		int yPosR = 0;
		for (int i = 0; i < rowsData.size(); i++) {
			// Create a Column Field
			/*JRDesignField field = new JRDesignField();
			field.setName(COL_EXPR_PREFIX + i);
			field.setValueClass(java.lang.String.class);
			jasperDesign.addField(field);*/

			// Add text field to the detailBand
			int j = 0;
			for(String col : rowsData.get(i)) {
				int columnWidth = columnWidthMap.get(j);
				JRDesignTextField textField = new JRDesignTextField();
				textField.setX(xPos);
				textField.setY(yPosR);
				textField.setWidth(columnWidth);
				textField.setHeight(COLUMN_HEIGHT);
				textField.setStyle(normalStyle);
				JRDesignExpression expression = new JRDesignExpression();
				expression.setValueClass(java.lang.String.class);
				expression.setText("$R{" + col + "}");
				textField.setExpression(expression);
				textField.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
				textField.setVerticalAlignment(VerticalAlignEnum.MIDDLE);
				textField.setBlankWhenNull(true);
				textField.setStretchType(StretchTypeEnum.RELATIVE_TO_TALLEST_OBJECT);
				textField.setStretchWithOverflow(true);
				textField.setPositionType(PositionTypeEnum.FLOAT);
				textField.setPrintWhenDetailOverflows(true);
				//textField.setRemoveLineWhenBlank(true);
				styleBox(textField);
				detailBand.addElement(textField);

				xPos = xPos + columnWidth + SPACE_BETWEEN_COLS;
				j++;
			}
			yPosR = yPosR + COLUMN_HEIGHT;
			xPos = xPosNew;
			noOfDynamicColumns++;
		}
		if (isNewdetailBand) {
			((JRDesignSection) jasperDesign.getDetailSection()).addBand(detailBand);
		}
		return noOfDynamicColumns;
	}

	@SuppressWarnings("deprecation")
	private JRDesignStyle getNormalStyle() {
		JRDesignStyle normalStyle = new JRDesignStyle();
		normalStyle.setName("Arial");
		normalStyle.setDefault(true);
		normalStyle.setFontName("Arial");
		normalStyle.setFontSize(8);
		normalStyle.setPdfFontName("Helvetica");
		normalStyle.setPdfEncoding("Cp1252");
		normalStyle.setPdfEmbedded(false);
		return normalStyle;
	}

	@SuppressWarnings("deprecation")
	private JRDesignStyle getColumnHeaderStyle() {
		JRDesignStyle columnHeaderStyle = new JRDesignStyle();
		columnHeaderStyle.setName("Arial_Header");
		columnHeaderStyle.setDefault(false);
		columnHeaderStyle.setFontName("Arial");
		columnHeaderStyle.setFontSize(8);
		columnHeaderStyle.setBold(true);
		columnHeaderStyle.setPdfFontName("Helvetica");
		columnHeaderStyle.setPdfEncoding("Cp1252");
		columnHeaderStyle.setPdfEmbedded(false);
		columnHeaderStyle.setBackcolor(new Color(204, 204, 255));		
		return columnHeaderStyle;
	}

	private JRLineBox styleBox(JRDesignTextField designTextfield) {
		float width = .5f;
		Color c = new Color(0, 0, 0);
		JRLineBox box = designTextfield.getLineBox();
		box.getLeftPen().setLineWidth(width);
		box.getLeftPen().setLineColor(c);
		box.getRightPen().setLineWidth(width);
		box.getRightPen().setLineColor(c);
		box.getTopPen().setLineWidth(width);
		box.getTopPen().setLineColor(c);
		box.getBottomPen().setLineWidth(width);
		box.getTopPen().setLineColor(c);
		return box;
	}

}