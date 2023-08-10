package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "AllUserData")
    public String[][] getAllData() throws IOException {
        String paths =System.getProperty("user.dir") + "//testData/UserData.xlsx";
        XLUtils xlData = new XLUtils(paths);

        int rowCount = xlData.getRowCount("Sheet1");
        int columnCount = xlData.getCellCount("Sheet1", 1);

        String[][] dataApiXl = new String[rowCount][columnCount];

        for (int i = 1; i <= rowCount ; i++) {
            for (int j = 0; j < columnCount ; j++) {
                dataApiXl[i -1][j] = xlData.getCellData("Sheet1", i, j);
            }
        }

        return dataApiXl;
    }

    @DataProvider(name = "AllUserNamesData")
    public String[] getAllUserNames() throws IOException {
        String paths =System.getProperty("user.dir") + "//testData/UserData.xlsx";
        XLUtils xlData = new XLUtils(paths);

        int rowCount = xlData.getRowCount("Sheet1");

        String[] dataApiXl = new String[rowCount];

        for (int i = 1; i <= rowCount ; i++) {
            dataApiXl[i -1] = xlData.getCellData("Sheet1", i, 1);
        }

        return dataApiXl;
    }

}
