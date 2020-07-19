import java.util.ArrayList;

public class SMA {
    //private final ArrayList<Double> dataset;
    private final int per = 10;
    private final int per1 = 100;

    public void getSMA1() {
        double[] input_data = { 1, 3, 5, 6, 8,
                12, 18, 21, 22, 25 };

       int count = 0;
       double sum = 0;
       ArrayList<Double> res = new ArrayList<>();
       for(int i = 0; i<input_data.length; i++){
           count++;
           sum+=input_data[i];
           System.out.println(sum);
           if(count == 3){
               res.add(sum);
               sum-=input_data[i-2];
               count = 2;
           }

       }
    }


}
