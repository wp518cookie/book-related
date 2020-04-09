package com.ping.wu.tree.bplustree;

import java.util.ArrayList;

/**
 * @author wuping
 * @date 2019-07-23
 */

public class INode {
    public boolean isLeaf = false;
    public INode parent = null;
    public ArrayList<Float> keys = new ArrayList();
    public ArrayList<INode> childNodes = new ArrayList();
}
