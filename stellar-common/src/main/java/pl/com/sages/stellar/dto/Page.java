package pl.com.sages.stellar.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({ConstellationDTO.class})
public class Page<T> {

    private List<T> items;

    private long total = 1;

    private long pageNo = 1;

    private long pageSize = 100;

    private long pageCount = 1;

    public Page(List<T> list, long total, long pageNo, long pageSize){
        this.items = list;
        this.total = total;
        this.pageNo = pageNo;

        pageCount = (total / pageSize);
        if((total % pageSize) != 0) pageCount++;
    }

    public Page(List<T> list){
        this.items = list;
        this.total = list.size();
        this.pageSize = total;
    }

    public Page(){
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Page{" +
                "items=" + items +
                ", total=" + total +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                '}';
    }
}
