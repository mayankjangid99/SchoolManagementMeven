package com.school.common.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.model.StudentAttendanceDetailsModel;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.StudentDetailsModel;


public class AttendanceExcelManager 
{
	
	private static Logger log = LoggerFactory.getLogger(AttendanceExcelManager.class);
	
	/**
	 * Builds the report layout. 
	 * <p>
	 * This doesn't have any data yet. This is your template.
	 */
	public static void buildReport(Sheet worksheet, int startRowIndex, int startColIndex, ClassInformationModel classInformation, 
			SectionInformationModel sectionInformation, String date, String status) {
		// Set column widths
		worksheet.setColumnWidth(0, 5000);
		worksheet.setColumnWidth(1, 5000);
		worksheet.setColumnWidth(2, 5000);
		worksheet.setColumnWidth(3, 5000);
		worksheet.setColumnWidth(4, 5000);
		worksheet.setColumnWidth(5, 5000);
		worksheet.setColumnWidth(6, 5000);
		
		// Build the title and date headers
		buildTitle(worksheet, startRowIndex, startColIndex, classInformation, sectionInformation, date, status);
		// Build the column headers
		buildHeaders(worksheet, startRowIndex, startColIndex);
	}
	
	
	
	/**
	 * Builds the report title and the date header
	 * 
	 * @param worksheet
	 * @param startRowIndex starting row offset
	 * @param startColIndex starting column offset
	 */
	public static void buildTitle(Sheet worksheet, int startRowIndex, int startColIndex, ClassInformationModel classInformation, 
			SectionInformationModel sectionInformation, String date, String status) {
		// Create font style for the report title
		Font fontTitle = worksheet.getWorkbook().createFont();
		fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fontTitle.setFontHeight((short) 280);
		
        // Create cell style for the report title
//        HSSFCellStyle cellStyleTitle = worksheet.getWorkbook().createCellStyle();
        CellStyle cellStyleTitle = worksheet.getWorkbook().createCellStyle();
        cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
//        cellStyleTitle.setAlignment(CellStyle.VERTICAL_CENTER);
        cellStyleTitle.setWrapText(true);
        cellStyleTitle.setFont(fontTitle);
		
        // Create report title
//		HSSFRow rowTitle = worksheet.createRow((short) startRowIndex);
		Row rowTitle = worksheet.createRow((short) startRowIndex);
		rowTitle.setHeight((short) 500);
//		HSSFCell cellTitle = rowTitle.createCell(startColIndex);
		Cell cellTitle = rowTitle.createCell(startColIndex);
		cellTitle.setCellValue("Attendance Report");
		cellTitle.setCellStyle(cellStyleTitle);
		
		// Create merged region for the report title
		worksheet.addMergedRegion(new CellRangeAddress(0,0,0,6));
		
		
		
		// Create date header
//		HSSFRow dateTitle = worksheet.createRow((short) startRowIndex +1);
		Row dateTitle = worksheet.createRow((short) startRowIndex +1);
//		HSSFCell cellDate = dateTitle.createCell(startColIndex);
		Cell cellDate = dateTitle.createCell(startColIndex);
		cellDate.setCellValue("Ex-(Attendance=A/P, SMS=Y/N, Email=Y/N)");
		// Create merged region for the report title
		worksheet.addMergedRegion(new CellRangeAddress(1,1,0,1));

		Cell cellSession = dateTitle.createCell(startColIndex + 2);
		cellSession.setCellValue(SessionManagerDataHelper.getSchoolCurrentSession());
		
		Cell cellClassLabel = dateTitle.createCell(startColIndex + 3);
		cellClassLabel.setCellValue(classInformation.getClassName() + " - " + classInformation.getClassCode());
		
		Cell cellStatusLabel = dateTitle.createCell(startColIndex + 4);
		cellStatusLabel.setCellValue(sectionInformation.getSectionName() + " - " +sectionInformation.getSectionCode());
		
		Cell cellStatus = dateTitle.createCell(startColIndex + 5);
		cellStatus.setCellValue(status);
		
		/*SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
		String date = sm.format(new Date());*/
		Cell cellAttDate = dateTitle.createCell(startColIndex + 6);
		cellAttDate.setCellValue(date);
	}
	
	
	
	/**
	 * Builds the column headers
	 * 
	 * @param worksheet
	 * @param startRowIndex starting row offset
	 * @param startColIndex starting column offset
	 */
	public static void buildHeaders(Sheet worksheet, int startRowIndex, int startColIndex) {
		// Create font style for the headers
		Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
//		HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
        CellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
//		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
//		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Create the column headers
//		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
		Row rowHeader = worksheet.createRow((short) startRowIndex +2);
		rowHeader.setHeight((short) 500);
		
//		HSSFCell cell1 = rowHeader.createCell(startColIndex+0);
		Cell cell1 = rowHeader.createCell(startColIndex+0);
		cell1.setCellValue("Roll");
		cell1.setCellStyle(headerCellStyle);

//		HSSFCell cell2 = rowHeader.createCell(startColIndex+1);
		Cell cell2 = rowHeader.createCell(startColIndex+1);
		cell2.setCellValue("Admission No");
		cell2.setCellStyle(headerCellStyle);

//		HSSFCell cell3 = rowHeader.createCell(startColIndex+2);
		Cell cell3 = rowHeader.createCell(startColIndex+2);
		cell3.setCellValue("Student Name");
		cell3.setCellStyle(headerCellStyle);

//		HSSFCell cell4 = rowHeader.createCell(startColIndex+3);
		Cell cell4 = rowHeader.createCell(startColIndex+3);
		cell4.setCellValue("Father Name");
		cell4.setCellStyle(headerCellStyle);

//		HSSFCell cell5 = rowHeader.createCell(startColIndex+4);
		Cell cell5 = rowHeader.createCell(startColIndex+4);
		cell5.setCellValue("Attendance");
		cell5.setCellStyle(headerCellStyle);
	
//		HSSFCell cell6 = rowHeader.createCell(startColIndex+5);
		Cell cell6 = rowHeader.createCell(startColIndex+5);
		cell6.setCellValue("SMS");
		cell6.setCellStyle(headerCellStyle);
		
//		HSSFCell cell7 = rowHeader.createCell(startColIndex+6);
		Cell cell7 = rowHeader.createCell(startColIndex+6);
		cell7.setCellValue("Email");
		cell7.setCellStyle(headerCellStyle);
	}
	
	
	
	
	/**
	 * Fills the report with content
	 * 
	 * @param worksheet
	 * @param startRowIndex starting row offset
	 * @param startColIndex starting column offset
	 * @param datasource the data source
	 */
	public static void fillReport(Sheet worksheet, int startRowIndex, int startColIndex, List<StudentAttendanceDetailsModel> attendanceList) {
		// Row offset
		startRowIndex += 2;
		
		// Create cell style for the body
//		HSSFCellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
		CellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
		bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellStyle.setWrapText(true);
		
		// Create body
		for (int i=startRowIndex; i< attendanceList.size()+2; i++) {
			StudentAttendanceDetailsModel attendanceDetails = attendanceList.get(i-2);
			// Create a new row
//			HSSFRow row = worksheet.createRow((short) i+1);
			Row row = worksheet.createRow((short) i+1);

			// Retrieve the id value
//			HSSFCell cell1 = row.createCell(startColIndex+0);
			Cell cell1 = row.createCell(startColIndex+0);
			cell1.setCellValue(attendanceDetails.getAdmissionDetails().getRollNo());
			cell1.setCellStyle(bodyCellStyle);

			// Retrieve the brand value
//			HSSFCell cell2 = row.createCell(startColIndex+1);
			Cell cell2 = row.createCell(startColIndex+1);
			cell2.setCellValue(attendanceDetails.getAdmissionDetails().getAdmissionNumber().getAdmissionNo());
			cell2.setCellStyle(bodyCellStyle);
			
			// Retrieve the model value
//			HSSFCell cell3 = row.createCell(startColIndex+2);
			Cell cell3 = row.createCell(startColIndex+2);
			cell3.setCellValue(attendanceDetails.getAdmissionDetails().getStudentDetails().getFullname());
			cell3.setCellStyle(bodyCellStyle);
			
			// Retrieve the maximum power value
//			HSSFCell cell4 = row.createCell(startColIndex+3);
			Cell cell4 = row.createCell(startColIndex+3);
			cell4.setCellValue(attendanceDetails.getAdmissionDetails().getStudentDetails().getParentDetails().getFatherName());
			cell4.setCellStyle(bodyCellStyle);

			// Retrieve the price value
//			HSSFCell cell5 = row.createCell(startColIndex+4);
			Cell cell5 = row.createCell(startColIndex+4);
//			cell5.setCellValue(datasource.get(i-2).getPrice());
			cell5.setCellStyle(bodyCellStyle);
		
			// Retrieve the efficiency value
//			HSSFCell cell6 = row.createCell(startColIndex+5);
			Cell cell6 = row.createCell(startColIndex+5);
//			cell6.setCellValue(datasource.get(i-2).getEfficiency());
			cell6.setCellStyle(bodyCellStyle);
			
			// Retrieve the efficiency value
//			HSSFCell cell7 = row.createCell(startColIndex+6);
			Cell cell7 = row.createCell(startColIndex+6);
//			cell7.setCellValue(datasource.get(i-2).getEfficiency());
			cell7.setCellStyle(bodyCellStyle);
		}
	}
	
	
	
	/**
	 * Writes the report to the output stream
	 */
	public static void write(HttpServletResponse response, Sheet worksheet) {
		
		log.debug("Writing report to the stream");
		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			worksheet.getWorkbook().write(outputStream);
			// Flush the stream
			outputStream.flush();

		} catch (Exception e) {
			log.error("Unable to write report to the output stream");
		}
	}
	
	
	
	
	
	
	
	
	public static Map<String, Object> readExcelData(Workbook workbook, int headerIndex) {
		//Get the number of sheets in the xlsx file
        int numberOfSheets = workbook.getNumberOfSheets();

        Map<String, Object> excelData = new HashMap<String, Object>();
        //loop through each of the sheets
        for(int i=0; i < numberOfSheets; i++){
             
            //Get the nth sheet from the workbook
            Sheet sheet = workbook.getSheetAt(i);
            List<Map<String, Object>> studentDataList = new ArrayList<Map<String, Object>>();
            
            int rowIndex = 0;
            String currentSession = "";
            String classInfo = "";
            String sectionInfo = "";
            String status = "";
            String date = "";
            
//          every sheet has rows, iterate over them
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) 
            {
                //Get the row object
                Row row = rowIterator.next();
                 
                //Every row has columns, get the column iterator and iterate over them
                Iterator<Cell> cellIterator = row.cellIterator();
                if(rowIndex == (headerIndex-1))
                {
                	int cellIndex = 0;
 	                while (cellIterator.hasNext()) 
 	                {
 	                    //Get the Cell object
 	                    Cell cell = cellIterator.next();
 	                     
 	                    //check the cell type and process accordingly
 	                    switch(cell.getCellType()){
 	                    case Cell.CELL_TYPE_STRING:
 	                    	if(cellIndex == 0){
 	                    		
 	                        }else if(cellIndex == 2){
 	                        	currentSession = cell.getStringCellValue().trim();
 	                        }else if(cellIndex == 3){
 	                        	classInfo = cell.getStringCellValue().trim();
 	                        }else if(cellIndex == 4){
 	                        	sectionInfo = cell.getStringCellValue().trim();
 	                        }else if(cellIndex == 5){
 	                        	status = cell.getStringCellValue().trim();
 	                        }else if(cellIndex == 6){
 	                        	date = cell.getStringCellValue().trim();
 	                        }else{
 	                            //random data, leave it
 	                            System.out.println("Random data::"+cell.getStringCellValue());
 	                        }
 	                        break;
 	                    case Cell.CELL_TYPE_NUMERIC:
 	                    	if(cellIndex == 6){
 	                            //2nd column
 	                    		 System.out.println("Random data::"+cell.getNumericCellValue());
 	                        	date = Double.toString(cell.getNumericCellValue());
 	                        }
 	                        System.out.println("Random data::"+cell.getNumericCellValue());
 	                        break;
 	                    case Cell.CELL_TYPE_BLANK:
 	                    	if(cellIndex == 0){
 	                        	log.error("ERROR: Example Column Should not Blank in Attendance Report");
 	                        }else if(cellIndex == 1){
 	                        	log.error("ERROR: Current Session Should not Blank in Attendance Report");
 	                        }else if(cellIndex == 2){
 	                        	log.error("ERROR: Class Information  not Blank in Attendance Report");
 	                        }else if(cellIndex == 3){
 	                        	log.error("ERROR: Section Information Should not Blank in Attendance Report");
 	                        }else if(cellIndex == 4){
 	                        	log.error("ERROR: Status Should not Blank in Attendance Report");
 	                        }else if(cellIndex == 5){
 	                        	log.error("ERROR: Date Should not Blank in Attendance Report");
 	                        }else{
 	                        	log.error("ERROR: Error in Report at reading Condition Row Blank");
 	                        }
 	                    }
 	                    cellIndex++;
 	                } //end of cell iterator
 	                if(!classInfo.equals(""))
 	                {
 	                	excelData.put("classInfo", classInfo);
 	                }
 	                if(!sectionInfo.equals(""))
	                {
 	                	excelData.put("sectionInfo", sectionInfo);
	                }
 	                if(!status.equals(""))
	                {
 	                	excelData.put("status", status);
	                }
 	                if(!currentSession.equals(""))
 	                {
 	                	excelData.put("currentSession", currentSession);
 	                }
 	                if(!date.equals(""))
 	                {
 	                	excelData.put("date", date);
 	                }
                }

                if(rowIndex > headerIndex)
                {
                	Map<String, Object> studentData = new HashMap<String, Object>();
                    int rollNo = 0;
                    String admissionNo = "";
                    String attendance = "";
                    String sms = "";
                    String email = "";
                    int cellIndex = 0;
	                while (cellIterator.hasNext()) 
	                {
	                    //Get the Cell object
	                    Cell cell = cellIterator.next();
	                     
	                    //check the cell type and process accordingly
	                    switch(cell.getCellType()){
	                    case Cell.CELL_TYPE_STRING:
	                    	if(cellIndex == 0){
	                        	rollNo = Integer.parseInt(cell.getStringCellValue().trim());
	                        }else if(cellIndex == 1){
	                        	admissionNo = cell.getStringCellValue().trim();
	                        }else if(cellIndex == 4){
	                        	attendance = cell.getStringCellValue().trim();
	                        }else if(cellIndex == 5){
	                        	sms = cell.getStringCellValue().trim();
	                        }else if(cellIndex == 6){
	                        	email = cell.getStringCellValue().trim();
	                        }else{
	                            //random data, leave it
	                            System.out.println("Random data::"+cell.getStringCellValue());
	                        }
	                        break;
	                    case Cell.CELL_TYPE_NUMERIC:
	                    	if(cellIndex == 0){
	                        	rollNo = (int) cell.getNumericCellValue();
	                        }
	                        System.out.println("Random data::"+cell.getNumericCellValue());
	                        break;
	                    case Cell.CELL_TYPE_BLANK:
	                    	if(cellIndex == 0){
	                        	log.error("ERROR: Roll No. Should not Blank in Attendance Report");
	                        }else if(cellIndex == 1){
	                        	log.error("ERROR: Admission No. Should not Blank in Attendance Report");
	                        }else if(cellIndex == 4){
	                        	log.error("ERROR: Attendance Should not Blank in Attendance Report");
	                        }else if(cellIndex == 5){
	                        	log.error("ERROR: SMS Should not Blank in Attendance Report");
	                        }else if(cellIndex == 6){
	                        	log.error("ERROR: Email Should not Blank in Attendance Report");
	                        }else{
	                        	log.error("ERROR: Error in Report due to some value are Blank");
	                        }
	                    }
	                    cellIndex++;
	                } //end of cell iterator

	                if(!attendance.equals("") && !sms.equals("") && !email.equals(""))
	                {
	                	studentData.put("attendance", attendance);
	                	studentData.put("sms", sms);
	                	studentData.put("email", email);
	                }
	                if(!admissionNo.equals(""))
	                {
	                	studentData.put("admissionNo", admissionNo);
	                }
	                if(rollNo != 0)
	                {
	                	studentData.put("rollNo", rollNo);
	                }
	                
	                studentDataList.add(studentData);
                }
                rowIndex++;
            } //end of rows iterator
             
             excelData.put("studentDataList", studentDataList);
        } //end of sheets for loop
        
        return excelData;
	}
	
	
	
	
/*	public static List<Country> readExcelData(String fileName) {
        List<Country> countriesList = new ArrayList<Country>();
         
        try {
            //Create the input stream from the xlsx/xls file
            FileInputStream fis = new FileInputStream(fileName);
             
            //Create Workbook instance for xlsx/xls file input stream
            Workbook workbook = null;
            if(fileName.toLowerCase().endsWith("xlsx")){
                workbook = new XSSFWorkbook(fis);
            }else if(fileName.toLowerCase().endsWith("xls")){
                workbook = new HSSFWorkbook(fis);
            }
             
            //Get the number of sheets in the xlsx file
            int numberOfSheets = workbook.getNumberOfSheets();
             
            //loop through each of the sheets
            for(int i=0; i < numberOfSheets; i++){
                 
                //Get the nth sheet from the workbook
                Sheet sheet = workbook.getSheetAt(i);
                 
                //every sheet has rows, iterate over them
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) 
                {
                    String name = "";
                    String shortCode = "";
                     
                    //Get the row object
                    Row row = rowIterator.next();
                     
                    //Every row has columns, get the column iterator and iterate over them
                    Iterator<Cell> cellIterator = row.cellIterator();
                      
                    while (cellIterator.hasNext()) 
                    {
                        //Get the Cell object
                        Cell cell = cellIterator.next();
                         
                        //check the cell type and process accordingly
                        switch(cell.getCellType()){
                        case Cell.CELL_TYPE_STRING:
                            if(shortCode.equalsIgnoreCase("")){
                                shortCode = cell.getStringCellValue().trim();
                            }else if(name.equalsIgnoreCase("")){
                                //2nd column
                                name = cell.getStringCellValue().trim();
                            }else{
                                //random data, leave it
                                System.out.println("Random data::"+cell.getStringCellValue());
                            }
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.println("Random data::"+cell.getNumericCellValue());
                        }
                    } //end of cell iterator
                    Country c = new Country(name, shortCode);
                    countriesList.add(c);
                } //end of rows iterator
                 
                 
            } //end of sheets for loop
             
            //close file input stream
            fis.close();
             
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        return countriesList;
    }*/
	
	
	
	
	
}
