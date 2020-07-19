//import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//import java.util.Date;
import java.time.*;

public class Currency{
    public ArrayList<LocalDate> date;
    public ArrayList<Double> value;

    Currency(){
        this.date = new ArrayList<LocalDate>();
        this.value = new ArrayList<Double>();
    }


    public void addDate(String a) throws Exception{
        LocalDate date = LocalDate.parse(a, DateTimeFormatter.ofPattern("d-MM-yyyy"));
        this.date.add(date);

        System.out.println(date);
    }

    public void addValue(Double a){
        this.value.add(a);
        //System.out.println(a);
    }
    public void clear(){
        date.clear();
        value.clear();
    }

    public ArrayList<LocalDate> getDate() {
        return date;
    }

    public ArrayList<Double> getValue() {
        return value;
    }

}