package tree3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Objects;

/**
 * Created by tanghaiyang on 2019/1/10.
 */
public class VoTreeBuilder {

    private static <T extends BaseTreeNodeVo> boolean insertNode(T currentNode, T childNode) {
        if( currentNode.getId().equals(childNode.getParentId()) ){
            currentNode.add(childNode);
            return true;
        }
        if (Objects.nonNull(currentNode.getChildren())){
            currentNode.getChildren().forEach(currentChildNode ->{
                insertNode(currentChildNode, childNode);
            });
        }
        return false;
    }


    /*
    * para list will be modified when excute recursive inserting, so need deepcopy
    *
    * 使用Collections.copy还是会修改参数list的元素，使用clone也会修改，这两种方式都是浅拷贝
    * 只有使用深度拷贝时，才会有不改变参数list的值
    * */
    @SuppressWarnings("all")
    public static <T extends BaseTreeNodeVo> T createTree(List<T> list, Class<T> clazz)
            throws IllegalAccessException, InstantiationException {
//        BaseTreeNodeVo currentNode = new BaseTreeNodeVo();
        T currentNode = clazz.newInstance();
        List<T> listCopy = (List<T>)deepCopy(list);
        if(Objects.nonNull(listCopy)) {
            listCopy.forEach(childNode -> { insertNode(currentNode, childNode); });
        }
        return currentNode;
    }


    public static <T extends BaseTreeNodeVo> List createTreeList(List<T> list, Class<T> clazz)
            throws IllegalAccessException, InstantiationException {
        return createTree(list, clazz).getChildren();
    }


    private static Object deepCopy(Object object) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(object);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return objInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
