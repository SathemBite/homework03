package task1;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anton on 18.10.17.
 */
public class CrazyLogger {
    private List<String[]> logs;
    private LocalDateTime lDT;

    public CrazyLogger(){
        logs = new LinkedList<>();
    }

    public void logging(String msg){
        String time = new SimpleDateFormat("dd-MM-YYYY : hh-mm – ").format(new Date());
        logs.add(new String[]{time, msg});
    }

    public boolean find(String msg){
        return logs.stream().filter(x -> x[1].indexOf(msg) != -1).count() > 0;
    }

    public boolean findPrint(String msg){
        String[] target = logs.stream().filter(x -> x[1].indexOf(msg) != -1).findFirst().get();
        int ind = target[1].indexOf(msg);
        boolean isFinds = ind != -1;
        if (isFinds){
            System.out.println(target[0] + target[1].substring(ind, ind + msg.length()));
        }
        return isFinds;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        logs.stream().forEach(x -> {sb.append(x[0]); sb.append(x[1]);});
        return sb.toString();
    }


}
