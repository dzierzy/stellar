package com.ericpol.lab.rest.skymap.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ListWrapper<T> {

    private List<T> list;

    private long count = 1;

    private long page = 1;

    public ListWrapper(List<T> list, long count, long page){
        this.list = list;
        this.count = count;
        this.page = page;
    }

    public ListWrapper(List<T> list){
        this.list = list;
    }

    public ListWrapper(){
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

}
