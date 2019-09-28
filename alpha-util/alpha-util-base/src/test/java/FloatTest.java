import org.assertj.core.api.BigDecimalAssert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author tanghaiyang on 2019/8/14.
 */
public class FloatTest {

    /**
     * float 比较大小的正确方式
     */
    @Test
    public void BigDecimalCompareTest(){
        // 要用字符串构造BigDecimal，如果用1.0F这种浮点数，会失真
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");

        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);

        System.out.println(x);
        System.out.println(y);
        if(x.equals(y)){
            System.out.println("true");
        }
    }

    /**
     * float 比较大小的错误方式
     */
    @Test
    public void FloatCompareTest(){
        float a = 1.0F - 0.9F;
        float b = 0.9F - 0.8F;

        System.out.println(a);
        System.out.println(b);

        if( a == b) {
            System.out.println(true);
        }

        Float x = Float.valueOf(a);
        Float y = Float.valueOf(b);
        if(x.equals(y)){
            System.out.println("true");
        }
    }



}
