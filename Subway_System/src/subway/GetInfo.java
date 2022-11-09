package subway;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class GetInfo {
    // 存储线路上的站点信息
    public static List<SubwayLine> lines = new ArrayList<>();
    // 存储相邻站点
    public static List<Station> stations = new ArrayList<>();

    // 解析 subway.txt 获取地铁图信息
    public static void subwayMap() {
        String f = "subway.txt";
        try (
                // FileReader用于读取文件字符流
                FileReader r = new FileReader(f);
                // BufferedReader类从字符输入流中读取文本并缓冲字符
                BufferedReader br = new BufferedReader(r);
        ) {
            String line;
            // 分离每一条线路
            while((line = br.readLine()) != null) {
                // 分离线路名称
                String[] tokens = line.split("/",2);
                SubwayLine s1 =  new SubwayLine();
                // tokens[0]是线路名称
                s1.name = tokens[0];
                // 分离票价
                String[] tokens1 = tokens[1].split("%",2);
                s1.fare = Double.parseDouble(tokens1[0]);
                // 分离首末班时间
                String[] ftime = tokens1[1].split("%",2);
                s1.FirstTime = s1.simpleDateFormat.parse(ftime[0]);
                String[] ltime = ftime[1].split("-",2);
                s1.LastTime = s1.simpleDateFormat.parse(ltime[0]);
                // 分离各个站点
                String[] tokens2 = ltime[1].split(" ");
                // forEach循环，字符串s每次获取一个站点信息
                for(String s: tokens2) {
                    Station station = new Station();
                    // 判断该站点是否可换乘
                    boolean isTransfer = s.contains("#");
                    if(isTransfer) {
                        //--------------------未完成
                        }
                        else {
                            if(s.contains("!")) {
                                continue;
                            }
                            station.isTransferStation = false;
                            station.name = s;
                        }
                    // 该站点所在线路
                    station.line = tokens[0];
                    // 添加途径站点
                    stations.add(station);
                    // 为该线路添加站点
//                    s1.stations.add(station);-------------------未完成
                    }
                lines.add(s1);
                }

            } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }
}
