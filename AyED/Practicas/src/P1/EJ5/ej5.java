package P1.EJ5;

public class ej5 {

    // A
    public static String withReturn(int[] v){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int i=0; i<v.length; i++){
            if(max < v[i]) max = v[i];
            if(min > v[i]) min = v[i];
            sum+=v[i];
        }
        int mean = sum/v.length;
        return "Max. Value: " + max +
                " Min. Value: " + min +
                " Mean: " + mean;
    }
//=====================================================================================================
    // B
    public static void withParameters(int[] v, Info stats){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int i=0; i<v.length; i++){
            if(max < v[i]) max = v[i];
            if(min > v[i]) min = v[i];
            sum+=v[i];
        }

        stats.max = max;
        stats.min = min;
        stats.mean = sum/v.length;
    }

static class Info{
    public int max;
    public int min;
    public int mean;
}

//=====================================================================================================
    //C
    static int max;
    static int min;
    static int mean;
    public static void withoutReturnAndParameters(int[] v){
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        int sum = 0;
        for(int i=0; i<v.length; i++){
            if(max < v[i]) max = v[i];
            if(min > v[i]) min = v[i];
            sum+=v[i];
        }
        mean = sum/v.length;
    }
}
