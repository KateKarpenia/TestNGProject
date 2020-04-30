package com.epam.task.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.epam.task.test.bean.Config;
import com.epam.task.test.bean.SourceTest;

public class FileReader
{
	private static final Logger logger = LogManager.getLogger(FileReader.class);

	public static List<SourceTest> readParamsFromFile(Config config) throws FileNotFoundException {
		String filePath = config.getFilePath();
		String delimiter = config.getDelimiter();
		List<SourceTest> sourceTestList = new ArrayList<>();
		File file = new File(filePath);
		InputStream inputStream = new FileInputStream(file);
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
		{
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(delimiter);
				sourceTestList.add(new SourceTest(data[0], data[1], Arrays.asList(data[2], data[3])));
			}
		}
		catch (IOException e)
		{
			logger.debug(e);
		}
		return sourceTestList;
	}

	public static Properties getProperties(String propFile) {
		File file = new File(propFile);
		Properties properties = new Properties();
		try (InputStream inputStream = new FileInputStream(file.getAbsolutePath()))
		{
			properties.load(inputStream);
		}
		catch (IOException e)
		{
			logger.debug(e);
		}
		return properties;
	}

	public static List<SourceTest> readParamsFromExcelFile(Config config) throws IOException {
		String filePath = config.getFilePath();
		List<SourceTest> sourceTestList = new ArrayList<>();
		File file = new File(filePath);
		try (Workbook workbook = new XSSFWorkbook(new FileInputStream(file)))
		{
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext())
			{
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				SourceTest sourceTest = new SourceTest();
				List<String> stringParams = new ArrayList<>();
				List<Integer> params = new ArrayList<>();
				while (cellIterator.hasNext())
				{
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex)
					{
						case 0:
							sourceTest.setName((String) getCellValue(nextCell));
							break;
						case 1:
							sourceTest.setSourceMethod((String) getCellValue(nextCell));
							break;
						case 2:
							Double param1 = (Double) getCellValue(nextCell);
							params.add(param1.intValue());
							stringParams.add(params.get(0).toString());
							break;
						case 3:
							Double param2 = (Double) getCellValue(nextCell);
							params.add(param2.intValue());
							stringParams.add(params.get(1).toString());
							break;
					}
					sourceTest.setParams(stringParams);
				}
				sourceTestList.add(sourceTest);
			}
		}
		return sourceTestList;
	}

	private static Object getCellValue(Cell cell) {

		if (cell.getCellType().equals(CellType.STRING))
		{
			return cell.getStringCellValue();
		}
		else if (cell.getCellType().equals(CellType.NUMERIC))
		{
			return cell.getNumericCellValue();
		}
		return null;
	}
}
