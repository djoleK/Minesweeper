import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String url = scanner.nextLine();
        String[] first = url.split("\\?");
        String[] keysValues1 = first[1].split("&");

        Map<String, String> keysValues = new LinkedHashMap<>();
        for (String s : keysValues1) {
            keysValues.put(s.split("=", -1)[0], s.split("=", -1)[1]);
        }

        for (Map.Entry<String, String> entry : keysValues.entrySet()) {
            if (entry.getValue().equals("")) {
                System.out.println(entry.getKey() + " : " + "not found");
            } else {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
        for (Map.Entry<String, String> entry : keysValues.entrySet()) {
            if (entry.getKey().equals("pass")) {
                System.out.println("password" + " : " + entry.getValue());
            }
        }
    }
}