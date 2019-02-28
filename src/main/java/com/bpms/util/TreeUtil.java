package com.bpms.util;

import com.bpms.pojo.Auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {
    public static List<TreeNode> authsToNode(List<Auth> auths) {
        ArrayList<TreeNode> nodes = new ArrayList<>(auths.size());
        for (Auth auth : auths) {
            TreeNode node = new TreeNode();
            node.setId(auth.getAuthId());
            node.setText(auth.getAuthName());
            node.setIconCls(auth.getIconCls());
            node.setState(auth.getState());
            if (auth.getAuthPath() != null) {
                HashMap<String, String> map = new HashMap<>();
                map.put("authPath", auth.getAuthPath());
                node.setAttributes(map);
            }
            nodes.add(node);
        }
        return nodes;
    }

    /**
     * @param auths    所有的权限对象
     * @param authIds  某个角色拥有的所有权限 id
     * @param parentId 作为父节点，匹配该权限下的子权限  一个权限树的父节点
     * @return 一个节点集合，这个结合可能包含一个节点，可能包含几个节点，每个节点可能就单独的一个，也可能他们都有自己的子节点
     */
    public static List<TreeNode> authsToNode(List<Auth> auths, String authIds, Integer parentId) {
        List<TreeNode> nodes = new ArrayList<>();
        for (Auth auth : auths) { //遍历所有的权限，看是否有parentId的子节点，如果有几把该权限对象，转换为节点类型。
            if (auth.getParentId() == parentId) {
                TreeNode node = new TreeNode();
                node.setId(auth.getAuthId());
                node.setText(auth.getAuthName());
                node.setState(auth.getState());
                node.setIconCls(auth.getIconCls());
                if (auth.getAuthPath() != null) {//该权限是否有请求路径，有把路径转为map类型存在节点的attributes属性中
                    Map<String, String> map = new HashMap<>();
                    map.put("authPath", auth.getAuthPath());
                    node.setAttributes(map);
                }
                if (authIds != null) {//本权限，是否包含在角色的拥有的权限中，有的话，就把该权限的状态设置为选中
                    String[] arr = authIds.split(",");
                    for (String s : arr) {
                        if (auth.getAuthId() == Integer.parseInt(s)) {
                            node.setChecked(true);
                            break;
                        }
                    }
                }
                //以本权限作为父节点，看是否有以本权限为父节点的子节点
                node.setChildren(TreeUtil.authsToNode(auths, authIds, auth.getAuthId()));//设置子节点，采用递归
                nodes.add(node);//到了这里就是有子节点，把子节点放到list中，返回出去。如果第一层接收对象返回给前端，深入的递归，接受对象为上层的节点的children属性，作为其子节点
            }
        }
        return nodes;
    }


    public static List<Auth> authsToNode_treegrid(List<Auth> auths, int parrentId) {
        List<Auth> authList = new ArrayList<>(auths.size());
        for (Auth auth : auths) {
            if (auth.getParentId().intValue() == parrentId) {
                auth.setChildren(TreeUtil.authsToNode_treegrid(auths, auth.getAuthId()));
                authList.add(auth);
            }
        }
        return authList;
    }
}
