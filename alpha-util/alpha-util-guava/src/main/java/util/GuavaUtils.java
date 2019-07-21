package util;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.html.HtmlEscapers;
import com.google.common.primitives.Booleans;
import com.google.common.reflect.Reflection;
import com.google.common.xml.XmlEscapers;
import com.sun.deploy.util.StringUtils;
import org.aspectj.apache.bcel.util.ClassPath;
import org.junit.Test;

import java.util.*;
import java.util.Objects;
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


    @Test
    public void StringUtils(){
//        StringUtils.join(", ", " ");
//        Maps.uniqueIndex();
        XmlEscapers.xmlAttributeEscaper();
        HtmlEscapers.htmlEscaper();
        System.out.println(ClassPath.getClassPath());
        System.out.println(Reflection.getPackageName(GuavaUtils.class));
        ImmutableRangeSet.builder();
}
}
