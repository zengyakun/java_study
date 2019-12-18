
package cc.eslink;


import java.io.Serializable;

/**
 *
 * 单元格
 *
 */
public class SfCell implements Serializable { //16

    /**
     *
     */
    private static long serialVersionUID = 7689098098594246805L; //8
    /** 单元格对应列名ID */
    private String columnId; //8
    /** 单元格描述 */
    private String cellDesc; //8
//    private int startRow; //4
//    private int endRow; //4
//    private List<SfCell> childList;

//    public SfCell() {
//    }
//
//    public SfCell(String columnId) {
//        this.columnId = columnId;
////        this.childList = new ArrayList<>();
//    }
//
//    public SfCell(String columnId, String cellDesc) {
//        this.columnId = columnId;
//        this.cellDesc = cellDesc;
////        this.childList = new ArrayList<>();
//    }
//
//    public SfCell(String columnId, String cellDesc, int startRow, int endRow) {
//        this.columnId = columnId;
//        this.cellDesc = cellDesc;
//        this.startRow = startRow;
//        this.endRow = endRow;
////        this.childList = new ArrayList<>();
//    }
//
//    public String getColumnId() {
//        return columnId;
//    }
//
//    public void setColumnId(String columnId) {
//        this.columnId = columnId;
//    }
//
//    public String getCellDesc() {
//        return cellDesc;
//    }
//
//    public void setCellDesc(String cellDesc) {
//        this.cellDesc = cellDesc;
//    }
//
//    public int getStartRow() {
//        return startRow;
//    }
//
//    public void setStartRow(int startRow) {
//        this.startRow = startRow;
//    }
//
//    public int getEndRow() {
//        return endRow;
//    }
//
//    public void setEndRow(int endRow) {
//        this.endRow = endRow;
//    }
//
////    public List<SfCell> getChildList() {
////        return childList;
////    }
////
////    public void setChildList(List<SfCell> childList) {
////        this.childList = childList;
////    }
//
//    public boolean isMerge() {
//        if (this.startRow == this.endRow) {
//            return false;
//        }
//
//        return true;
//    }


}
