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

    public static List<TreeNode> authsToNode(List<Auth> auths,String authIds,Integer parentId ) {
        List<TreeNode> nodes = new ArrayList<>();
        for (Auth auth : auths) {
            if (auth.getParentId()==parentId) {
                TreeNode node = new TreeNode();
                node.setId(auth.getAuthId());
                node.setText(auth.getAuthName());
                node.setState(auth.getState());
                node.setIconCls(auth.getIconCls());
                if (auth.getAuthPath() != null) {
                    Map<String, String> map = new HashMap<>();
                    map.put("authPath", auth.getAuthPath());
                    node.setAttributes(map);
                }
                if (authIds != null) {
                    String[] arr = authIds.split(",");
                    for (String s : arr) {
                        if (auth.getParentId() == Integer.parseInt(s)) {
                            node.setChecked(true);
                            break;
                        }
                    }
                }
                node.setChildren(TreeUtil.authsToNode(auths, authIds, auth.getAuthId()));//设置子节点，采用递归
                nodes.add(node);
            }
        }
        return nodes;
    }


}
