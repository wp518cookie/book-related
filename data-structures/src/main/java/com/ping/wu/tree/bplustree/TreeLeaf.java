package com.ping.wu.tree.bplustree;

import java.util.ArrayList;

/**
 * @author wuping
 * @date 2019-07-23
 */

public class TreeLeaf extends INode {
    public TreeLeaf() {
        this.isLeaf = true;
    }

    public ArrayList<Object> values = new ArrayList();
    public TreeLeaf rightBrother = null;
}
