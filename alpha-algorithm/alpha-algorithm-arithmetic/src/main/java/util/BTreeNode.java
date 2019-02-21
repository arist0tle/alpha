package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by tanghaiyang on 2019/2/21.
 */
@Data
public class BTreeNode {
    private JSONArray data;
    private JSONObject singleFilter;
    private String operation;
    private List<BTreeNode> child = new LinkedList<>();

    public JSONArray calculate(){
        JSONArray ret = new JSONArray();
        if(Objects.isNull(operation) || child.isEmpty()){
            return data;
        }else {
            if(operation.equals("AND")) {
                for (BTreeNode tree : child) {
                    JSONArray tmpDataAnd = tree.calculate();
                    ret.retainAll(tmpDataAnd);
                }
            }else if(operation.equals("OR")){
                for(BTreeNode tree: child){
                    JSONArray tmpDataOr = tree.calculate();
                    ret.addAll(tmpDataOr);
                }
            }
        }

        return ret;
    }
}
