package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviders {
	

	@DataProvider(name="data")
	public String[][] getdata() throws IOException{
		String path=System.getProperty("user.dir")+"//testdata//details.xlsx";
		XLUtils xl=new XLUtils(path);
		int rownum=xl.getRowCount("Sheet1");
		int colcount=xl.getColCount("Sheet1", 1);
		String[][] apidata=new String[rownum][colcount];
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]=xl.getcellValue("Sheet1", i, j);
			}
		}
		
		return apidata;
		
	}
	@DataProvider(name="UserNames")
	public String[] getUserName() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testdata//details.xlsx";
		XLUtils xl=new XLUtils(path);
		int rowcount=xl.getRowCount("Sheet1");
		String[] apidata=new String[rowcount];
		for(int i=1;i<=rowcount;i++)
		{
			apidata[i-1]=xl.getcellValue("Sheet1", i, 1);
		}
		
		return apidata;
		
	}
}
