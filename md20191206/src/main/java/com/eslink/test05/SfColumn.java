package com.eslink.test05;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *@ClassName SfColumn
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/11/14 16:33
 *@Version 1.0
 **/
public class SfColumn implements Serializable {
    private static final long serialVersionUID = -2843723278572300333L;

    private String id;
    private String name;
    private int width = 2800;
    private List<SfColumn> childNodeList;

    public SfColumn() {
        this(System.currentTimeMillis() + "skfdsjfdsgdgjfljdgkjfkjkgfjkjkfjkkjdsakdjajkdjadjsajkdjkfjkejkfjskdjkgjdksjgdjskgjdksjkdfjdfk",
                "dsfdjkfjlkdjskjfdklfklskfjdkfjkldsjkfjdksljflkdskjlfjdlsjgkldjskgkdsfjkdfsksjdsd-hello world-" + Thread.currentThread().getName(),
                new ArrayList<>());
    }


    public SfColumn(String id, String name, List<SfColumn> childNodeList) {
        this.id = id;
        this.name = name;
        this.childNodeList = childNodeList;
    }


    public SfColumn(String id, String name, int width, List<SfColumn> childNodeList) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.childNodeList = childNodeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<SfColumn> getChildNodeList() {
        return childNodeList;
    }

    public void setChildNodeList(List<SfColumn> childNodeList) {
        this.childNodeList = childNodeList;
    }

    public int childNum() {
        if (CollectionUtils.isEmpty(childNodeList)) {
            return 1;
        }
        return childNodeList.parallelStream().mapToInt(SfColumn::childNum).sum();
    }
}
