package task3;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by anton on 20.10.17.
 */
public class FileAnalyzer {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        BufferedReader bf =
                new BufferedReader
                        (new InputStreamReader
                                (new FileInputStream
                                        ("resources/Java.SE.03.Information handling_task_attachment.html"), "Cp1251"));
        StringBuilder imgSeq = new StringBuilder();
        StringBuilder sentencesWithImg = new StringBuilder();
        Pattern tempPat = Pattern.compile("\\s+[^.!?]*Рис\\.\\s*?(\\d+)[^.!?]*[.!?]");
        Stream<String> fileLines = bf.lines();
        String text = fileLines.
                reduce("", (acc, cVal) -> acc + "\n" + cVal).
                replaceAll("(</?.*?>)|([^А-Яа-я0-9,.!:\\?\\s])", "");
        Matcher matcher = tempPat.matcher(text);
        int lastImgNum = 0;
        while (matcher.find()){
            sentencesWithImg.append(matcher.group(0));
            int curImgNum = Integer.parseInt(matcher.group(1));
            imgSeq.append("Рис. ");
            imgSeq.append(curImgNum);
            if ((curImgNum == lastImgNum) || (curImgNum == lastImgNum + 1)){
                lastImgNum = curImgNum;
            }else{
                imgSeq.append(" (wrong num!)");
            }
            imgSeq.append("\n");
        }

        BufferedWriter bw = new BufferedWriter(
                            new OutputStreamWriter(
                            new FileOutputStream("resources/result.txt")));
        bw.write("Sentences with images links: \n\n");
        bw.write(sentencesWithImg.
                toString().
                replaceAll(" {2,}", " ").
                replaceAll("\\n{2,}", "\n").
                replaceAll("\\.{2,}", "."));
        bw.write(imgSeq.toString());
        bw.close();

    }
}
