package api.utilities;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "AllData")
	public String[][] allDataProvider()
	{
		String fileName = System.getProperty("user.dir")+"//TestData//TestData.xlsx";
		int rowsCount = ReadExcelFile.getRowCount(fileName, "Sheet1");
		int colsCount = ReadExcelFile.getColCount(fileName, "Sheet1");
		
		
		
		String userData[][] = new String[rowsCount-1][colsCount];
		
		for(int i=1; i<rowsCount; i++)
		{
			for(int j = 0; j<colsCount; j++)
			{
				userData[i-1][j] = ReadExcelFile.getCellValue(fileName, "Sheet1", i, j);
			}
		}
		return userData;
		
	} //End of Data Provider AllData

	
	@DataProvider(name="UserNameData")
	public String[] UserNameDataProvider()
	{
		String fileName = System.getProperty("user.dir")+"//TestData//TestData.xlsx";
		int rowsCount = ReadExcelFile.getRowCount(fileName, "Sheet1");
		//int colsCount = ReadExcelFile.getColCount(fileName, "TestData");
		
		String userNamesData[] = new String[rowsCount-1];
		
		for(int i=1; i<rowsCount; i++)
		{
			
				userNamesData[i-1] = ReadExcelFile.getCellValue(fileName, "Sheet1", i, 1);
			
		}
		return userNamesData;
	}
}
