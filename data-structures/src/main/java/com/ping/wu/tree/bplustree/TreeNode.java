package com.ping.wu.tree.bplustree;

/**
 * @author wuping
 * @date 2019-07-23
 * 一棵m阶B树满足下列条件
 * 1、每个节点至多有m棵子树
 * 2、除根节点，其他每个分支至少有[m/2]棵子树
 * 3、根节点至少有两棵子树（除非B树只包含一个节点）
 * 4、所有叶节点在同一层上。B树的叶节点可以看成一种外部节点，不包含任何信息
 * 5、有j个孩子的非叶节点恰好有j-1关键码，关键码按递增次序排列
 */

public class TreeNode extends INode {

}
