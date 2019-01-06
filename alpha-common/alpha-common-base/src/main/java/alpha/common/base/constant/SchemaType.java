package alpha.common.base.constant;

/**
 * Created by chengmo on 2018/8/16.
 */
public enum SchemaType {
    VERTEX, VERTEX_MAIN, EDGE;

    public static boolean isVertex(SchemaType type) {
        return (type == VERTEX || type == VERTEX_MAIN);
    }

    public static boolean isMainVertex(SchemaType type) {
        return (type == VERTEX_MAIN);
    }

    public static boolean isEdge(SchemaType type) {
        return type == EDGE;
    }
}
