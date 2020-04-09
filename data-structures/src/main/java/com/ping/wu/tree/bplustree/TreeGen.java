package com.ping.wu.tree.bplustree;

import java.util.ArrayList;

/**
 * @author wuping
 * @date 2019-07-23
 */

public class TreeGen {
    private int max = 4;
    private int min = 2;

    private TreeLeaf leaf_tmp = null;
    private INode rootNode = new TreeLeaf();

    public TreeGen(int m) {
        max = m;
        min = (int) Math.ceil((double) max / (double) 2);
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public boolean insert(float indexNo, Object val) {
        if (rootNode.childNodes.size() <= 0) {
            // 首次插入
            if (!rootNode.isLeaf) {
                rootNode.isLeaf = true;
            }
            int indexLOC = -1;
            int cindex = 0;
            for (float f1 : rootNode.keys) {
                if (f1 == indexNo) {
                    return false;
                }
                if (indexNo > f1) {
                    indexLOC = cindex;
                }
                if (indexNo < f1) {
                    break;
                }
                cindex++;
            }
            rootNode.keys.add(indexLOC + 1, indexNo);
            ((TreeLeaf) rootNode).values.add(indexLOC + 1, val);
            recursion_division_after_insert(rootNode);
            return true;
        } else {
            TreeLeaf theLeaf = recursion_search_suitable_leaf(rootNode, indexNo);
            if (theLeaf == null) {
                return false;
            }
            int indexLOC = -1;
            int cindex = 0;
            for (float f1 : theLeaf.keys) {
                if (f1 == indexNo) {
                    return false;
                } else if (indexNo > f1) {
                    indexLOC = cindex;
                } else {
                    break;
                }
                cindex++;
            }
            insertIndexNo(theLeaf, indexLOC, indexNo, val);
            if (indexLOC == -1) {
                recursion_changeMinimum(theLeaf, indexNo);
            }
            recursion_division_after_insert(theLeaf);
        }
        return true;
    }

    private void recursion_division_after_insert(INode currentNode) {
        if (currentNode.keys.size() <= max) {
            return;
        }
        TreeLeaf currentLeaf = null;
        TreeNode currentNode2 = null;
        INode parentNode = currentNode.parent;
        if (currentNode.isLeaf) {
            currentLeaf = (TreeLeaf) currentNode;
            TreeLeaf rightLeaf = new TreeLeaf();
            rightLeaf.parent = currentLeaf.parent;
            rightLeaf.rightBrother = currentLeaf.rightBrother;
            currentLeaf.rightBrother = rightLeaf;
            int originalSize = currentLeaf.keys.size();
            for (int i = originalSize - 1; i >= min; i++) {
                rightLeaf.keys.add(currentLeaf.keys.get(i));
                rightLeaf.values.add(currentLeaf.values.get(i));
                currentLeaf.keys.remove(i);
                currentLeaf.values.remove(i);
            }
            // 之前是根节点
            if (currentLeaf.parent == null) {
                TreeNode parent = new TreeNode();
                parent.childNodes.add(currentLeaf);
                parent.childNodes.add(rightLeaf);
                parent.keys.add(currentLeaf.keys.get(0));
                parent.keys.add(rightLeaf.keys.get(0));
                currentLeaf.parent = parent;
                rightLeaf.parent = parent;
                rootNode = parent;
                return;
            // 有父亲节点
            } else {
                int cIndex = parentNode.keys.indexOf(currentLeaf.keys.get(0));
                parentNode.keys.add(cIndex + 1, rightLeaf.keys.get(0));
                parentNode.childNodes.add(cIndex + 1, rightLeaf);
                recursion_division_after_insert(parentNode);
                return;
            }
        } else {
            currentNode2 = (TreeNode) currentNode;
            TreeNode rightBrother = new TreeNode();
            int originalSize = currentNode2.keys.size();
            for (int i = originalSize - 1; i >= min; i--) {
                rightBrother.keys.add(currentNode2.keys.get(i));
                rightBrother.childNodes.add(currentNode2.childNodes.get(i));
                currentNode2.keys.remove(i);
                currentNode2.childNodes.remove(i);
            }
            if (currentNode2.parent == null) {
                TreeNode parent = new TreeNode();
                currentNode2.parent = parent;
                rightBrother.parent = parent;
                parent.keys.add(currentNode2.keys.get(0));
                parent.keys.add(rightBrother.keys.get(0));
                parent.childNodes.add(currentNode2);
                parent.childNodes.add(rightBrother);
                rootNode = parent;
                return;
            } else {
                INode parent = currentNode2.parent;
                rightBrother.parent = parent;
                int cIndex = currentNode2.keys.indexOf(currentNode2.keys.get(0));
                parent.keys.add(cIndex + 1, rightBrother.keys.get(0));
                parent.childNodes.add(cIndex + 1, rightBrother);
                recursion_division_after_insert(parent);
                return;
            }
        }
    }

    public boolean delete(float indexNo) {
        TreeLeaf currentNode = search(indexNo);
        if (currentNode == null) {
            return false;
        }
        int idx = currentNode.keys.indexOf(indexNo);
        if (idx == -1) {
            return false;
        }
        // 根节点，没有合并步骤
        if (currentNode.parent == null) {
            currentNode.keys.remove(idx);
            currentNode.values.remove(idx);
        } else {
            if (currentNode.keys.size() > min) {
                currentNode.keys.remove(idx);
                currentNode.values.remove(idx);
                if (idx == 0) {
                    recursion_handler_firstOneDelete(currentNode.parent,indexNo, currentNode.keys.get(0));
                }
                return true;
            } else {
                // 向左右节点借一个，如果左右节点够的话
                 INode parentNode = currentNode.parent;
                 int idxInParent = parentNode.keys.indexOf(currentNode.keys.get(0));
                 currentNode.keys.remove(idx);
                 currentNode.values.remove(idx);
                 TreeLeaf leftBrother = null;
                 TreeLeaf rightBrother = currentNode.rightBrother;
                 if (idxInParent > 0) {
                     leftBrother = (TreeLeaf) parentNode.childNodes.get(idxInParent - 1);
                 }
                 if (leftBrother != null && leftBrother.keys.size() > min) {
                     currentNode.keys.add(0, leftBrother.keys.get(leftBrother.keys.size() - 1));
                     currentNode.values.add(0, leftBrother.values.get(leftBrother.values.size() - 1));
                     leftBrother.keys.remove(leftBrother.keys.size() - 1);
                     leftBrother.values.remove(leftBrother.values.size() - 1);
                 } else if (rightBrother != null && rightBrother.keys.size() > min) {
                     currentNode.keys.add(rightBrother.keys.get(0));
                     currentNode.values.add(rightBrother.values.get(0));
                     float indexNo2 = rightBrother.keys.remove(0);
                     rightBrother.values.remove(0);
                     recursion_handler_firstOneDelete(rightBrother, indexNo2, rightBrother.keys.get(0));
                     if (idx == 0) {
                         recursion_handler_firstOneDelete(currentNode, indexNo, currentNode.keys.get(0));
                     }
                 } else {
                     if (leftBrother != null) {
                         if (idx == 0) {
                             recursion_handler_firstOneDelete(currentNode, indexNo, currentNode.keys.get(0));
                         }
                         for (float f1 : currentNode.keys) {
                             leftBrother.keys.add(f1);
                         }
                         INode parent = currentNode.parent;
                         leftBrother.rightBrother = currentNode.rightBrother;
                         int cIdx = parent.keys.indexOf(currentNode.keys.get(0));
                         parent.keys.remove(cIdx);
                         parent.childNodes.remove(cIdx);
                         recursion_combination(parent);
                         return true;
                     } else if (rightBrother != null) {
                         currentNode.keys.remove(idx);
                         if (idx == 0) {
                             recursion_handler_firstOneDelete(currentNode, indexNo, currentNode.keys.get(0));
                         }
                         for (float f1 : rightBrother.keys) {
                             currentNode.keys.add(f1);
                         }
                         currentNode.rightBrother = rightBrother.rightBrother;
                         INode parent = currentNode.parent;
                         int cIndex = parent.keys.indexOf(rightBrother.keys.get(0));
                         parent.keys.remove(cIndex);
                         parent.childNodes.remove(cIndex);
                         recursion_combination(parent);
                         return true;
                     }
                 }
            }
        }
        return false;
    }

    private void recursion_combination(INode currentNode) {
        if (currentNode == null) {
            return;
        }
        INode parentNode = currentNode.parent;
        if (currentNode.keys.size() >= min) {
            return;
        }
        if (currentNode.keys.size() == 1 && parentNode == null) {
            rootNode = currentNode.childNodes.get(0);
            rootNode.parent = null;
            return;
        }
        if (parentNode == null && currentNode.keys.size() >= 2) {
            return;
        }
        // todo
    }

    private void recursion_handler_firstOneDelete(INode node, float indexNo, float newIndexNo) {
        if (node == null) {
            return;
        }
        int idx = node.keys.indexOf(indexNo);
        node.keys.set(idx, newIndexNo);
    }

    private TreeLeaf search(float indexNo) {
        if (rootNode == null) {
            return null;
        } else if (rootNode.isLeaf) {
            return (TreeLeaf) rootNode;
        } else {
            return recursion_search(rootNode, indexNo);
        }
    }

    private TreeLeaf recursion_search(INode currentNode, float indexNo) {
        if (currentNode.isLeaf) {
            return (TreeLeaf) currentNode;
        }
        int idx = currentNode.keys.size() - 1;
        for (int i = 0; i < currentNode.keys.size(); i++) {
            if (currentNode.keys.get(i) > indexNo) {
                idx = i;
                break;
            }
        }
        return recursion_search(currentNode.childNodes.get(idx), indexNo);
    }

    private boolean insertIndexNo(TreeLeaf currentNode, int indexLOC, float indexNo, Object val) {
        if (currentNode == null) {
            return false;
        }
        currentNode.keys.add(indexLOC + 1, indexNo);
        currentNode.values.add(indexLOC + 1, val);
        return true;
    }

    private void recursion_changeMinimum(INode currNode, float indexNo) {
        if (currNode == null) {
            return;
        }
        currNode.keys.set(0, indexNo);
        recursion_changeMinimum(currNode.parent, indexNo);
    }

    private TreeLeaf recursion_search_suitable_leaf(INode currentNode, float indexNo) {
        if (currentNode == null) {
            return null;
        }
        if (currentNode.isLeaf || currentNode.childNodes.size() <= 0) {
            return (TreeLeaf) currentNode;
        }
        int indexLoc = -1;
        int cindex = 0;
        for (float f1 : currentNode.keys) {
            if (f1 == indexNo) {
                return null;
            } else if (f1 > indexNo) {
                break;
            } else {
                indexLoc = cindex;
            }
            cindex++;
        }
        if (indexLoc == -1) {
            return recursion_getLeftLeaf(currentNode);
        } else {
            return recursion_search_suitable_leaf(currentNode.childNodes.get(indexLoc), indexNo);
        }
    }

    private TreeLeaf recursion_getLeftLeaf(INode currentNode) {
        if (currentNode == null) {
            return null;
        }
        if (currentNode.isLeaf) {
            return (TreeLeaf) currentNode;
        }
        if (currentNode.childNodes.size() <= 0) {
            return null;
        }
        return recursion_getLeftLeaf(currentNode.childNodes.get(0));
    }

    public static TreeGen getCopyGen(TreeGen gen) {
        TreeGen gen1 = new TreeGen(gen.getMax());

        ArrayList<Float> arrList = gen.getAllKeyList();
        for (float f1 : arrList) {
            gen1.insert(f1, f1);
        }

        return gen1;
    }

    public TreeLeaf getFirstLeaf() {
        leaf_tmp = null;
        recursion_search_first_leaf(rootNode);
        return leaf_tmp;
    }

    private void recursion_search_first_leaf(INode curNode) {
        if (curNode == null) {
            return;
        }
        if (curNode.isLeaf) {
            leaf_tmp = (TreeLeaf) curNode;
            return;
        } else {
            if (curNode.childNodes.size() <= 0) {
                return;
            } else {
                recursion_search_first_leaf(curNode.childNodes.get(0));
                return;
            }
        }
    }

    public ArrayList<Float> getAllKeyList() {
        ArrayList<Float> flist = new ArrayList();
        TreeLeaf fLeaf = getFirstLeaf();
        while (fLeaf != null) {
            for (float f2 : fLeaf.keys) {
                flist.add(f2);
            }
            fLeaf = fLeaf.rightBrother;
        }
        return flist;
    }
}
