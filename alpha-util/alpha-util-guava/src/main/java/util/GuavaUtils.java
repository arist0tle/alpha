package util;

import com.google.common.collect.*;
import com.google.common.primitives.Booleans;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by tanghaiyang on 2019/3/11.
 */
public class GuavaUtils {


    public static void main(String[] args) {
        ArrayList<String> arrayList = Lists.newArrayList("ddd","ffff");;

        Lists.newArrayList();
        Lists.reverse(arrayList);

//        Collections2.filter();
        List<String> listA = new LinkedList<>();
        List<String> listB = new LinkedList<>();
        List<String> listC = Lists.newArrayList();
        Collections.copy(listA, listB);
        Collections.copy(listA, listC);


        Set<String> setA = new HashSet<>();
        Set<String> setB = new HashSet<>();

        Sets.union(setA, setB);

        Map<String,String> mapA = new HashMap<>();
        Map<String,String> mapB = new HashMap<>();
        Maps.difference(mapA,mapB);

        Queue<String> queue = new LinkedBlockingQueue<>();
        Queues.newLinkedBlockingDeque();



    }


}
