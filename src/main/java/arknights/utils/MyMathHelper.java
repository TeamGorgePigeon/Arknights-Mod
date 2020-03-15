package arknights.utils;

import static java.lang.Math.PI;

public class MyMathHelper {
    public static double _90Degree = PI / 2;

    public static double in360(double a){
        a = a % 360;
        return a > 0 ? a : 360 + a;
    }
}
