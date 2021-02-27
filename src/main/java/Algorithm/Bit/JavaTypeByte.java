package Algorithm.Bit;
/**
 * Created by Defias on 2020/06.
 * Description:  java数据类型字节
 */
public class JavaTypeByte {

    public static void main(String[] args) {

        testByteValue();
    }

    public static void testByteValue(){
        System.out.println("Integer.BYTES: "+Integer.BYTES);
        System.out.println("Short.BYTES: "+Short.BYTES);
        System.out.println("Long.BYTES: "+Long.BYTES);
        System.out.println("Byte.BYTES: "+Byte.BYTES);
        System.out.println("Double.BYTES: "+Double.BYTES);
        System.out.println("Float.BYTES: "+Float.BYTES);

        System.out.println("Integer.MIN_VALUE: "+Integer.MIN_VALUE);
        System.out.println("Integer.MAX_VALUE: "+Integer.MAX_VALUE);
        System.out.println("Short.MIN_VALUE: "+Short.MIN_VALUE);
        System.out.println("Short.MAX_VALUE: "+Short.MAX_VALUE);
        System.out.println("Long.MIN_VALUE: "+Long.MIN_VALUE);
        System.out.println("Long.MAX_VALUE: "+Long.MAX_VALUE);
        System.out.println("Double.MIN_VALUE: " + Double.MIN_VALUE);
        System.out.println("Double.MAX_VALUE: "+Double.MAX_VALUE);
        System.out.println("Float.MIN_VALUE: "+Float.MIN_VALUE);
        System.out.println("Float.MAX_VALUE: "+Float.MAX_VALUE);

        System.out.println("Character.MIN_VALUE: "+ (0+Character.MIN_VALUE) );
        System.out.println("Character.MAX_VALUE: "+ (0+Character.MAX_VALUE) );

    }
}
