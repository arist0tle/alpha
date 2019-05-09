package com.geektcp.alpha.db.es6.admin;

/**
 * Created by yangyijun on 2018/11/27.
 */
public interface EsAdminDao {

    boolean existsIndex(String index);

    boolean existsType(String index, String type);

    boolean createIndex(String index);

    boolean deleteIndex(String index);

    boolean createType(String index, String type);

//    boolean createType(TypeMapping mapping);

//    boolean bulkUpsert(String index, String type, List<Source> sourceList);

//    boolean bulkScriptUpsert(String index, String type, List<ScriptSource> sources);

//    boolean delete(String index, String type, List<Source> sources);

    /**
    * @description 删除指定Index下的指定type
    * @param index
    * @param type
    * @return boolean
    * @author liulu
    * @date 2018/12/13
    */
   boolean delete(String index, String type);

}
