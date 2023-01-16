package Interview.code;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Defias
 * @date: 2021/3/11
 * @description:
 */
public class question5 {

    public static void main(String[] args) {
        String[][] records = new String[][] {
                {"Stu1", "K1", "60", "201907"},
                {"Stu1", "K1", "59", "201907"},
                {"Stu2", "K1", "70", "201907"},
                {"Stu3", "K1", "20", "201907"},
                {"Stu1", "K3", "90", "201907"},

        };
        System.out.println(failsum(records));
    }

    public static int failsum(String[][] records) {

        Map<String, Map<String, String>> recordmap = new HashMap<>();

        for(int i=0; i<records.length; i++) {
            if(recordmap.get(records[i][0])==null) {
                Map<String, String> record = new HashMap<>();
                record.put(records[i][1], records[i][2]);
                recordmap.put(records[i][0], record);
            } else if(recordmap.get(records[i][0])!=null && recordmap.get(records[i][0]).get(records[i][1])==null) {
                recordmap.get(records[i][0]).put(records[i][1], records[i][2]);
            }  else {
                if(Integer.valueOf(recordmap.get(records[i][0]).get(records[i][1])) < Integer.valueOf(records[i][2])) {
                    recordmap.get(records[i][0]).put(records[i][1], records[i][2]);
                }
            }
        }

        int res = 0;
        for(Map.Entry<String, Map<String, String>> entry: recordmap.entrySet()) {
            Map<String, String> scodes = entry.getValue();
            for(Map.Entry<String, String> scode: scodes.entrySet()) {
                if(Integer.valueOf(scode.getValue()) < 60) {
                    res++;
                    break;
                }
            }
        }

        return res;
    }
}
