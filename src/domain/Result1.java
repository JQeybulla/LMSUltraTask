package domain;


import java.util.ArrayList;
import java.util.List;

public class Result1 {
    private List<Row> rows;
    private int pageNumber;
    private int totalPageCount;
    private int recordCount;

    public Result1() {
        rows = new ArrayList<Row>();
    }

    public void add(Row row) {
        rows.add(row);
    }
    public int getRecordCount() {
        return recordCount;

    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    @Override
    public String toString() {
        return "Result{" +
                "rows=" + rows +
                ", pageNumber=" + pageNumber +
                ", totalPageCount=" + totalPageCount +
                ", recordCount=" + recordCount +
                '}';
    }
}
