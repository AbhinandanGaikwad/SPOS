package A4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MacroProcessor_PassTwo {
    static List<String> MDT;
    static Map<String, String> MNT;
    static int mntPtr, mdtPtr;
    static List<String> formalParams, actualParams;

    public static void main(String[] args) {
        try {
            initiallizeTables();
            pass2();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void pass2() throws Exception {
        BufferedReader input = new BufferedReader(
                new InputStreamReader(new FileInputStream("src/A4/output_pass1.txt")));
        PrintWriter out_pass2 = new PrintWriter(new FileWriter("src/A4/output_pass2.txt"), true);

        System.out.println("============= Pass 2 Output ==============");

        String s;
        while ((s = input.readLine()) != null) {
            String[] s_arr = tokenizeString(s, " ");

            if (MNT.containsKey(s_arr[0])) {
                String[] actual_params = tokenizeString(s_arr[1], ",");
                String param;
                actualParams.clear();

                for (int i = 0; i < actual_params.length; i++) {
                    param = actual_params[i];
                    if (param.contains("=")) {
                        param = param.substring(param.indexOf("=") + 1);
                    }
                    actualParams.add(param);
                }

                mdtPtr = Integer.parseInt(MNT.get(s_arr[0]));
                String macroDef;
                boolean createParamArray = true;
                String[] def_tokens = {}, paramStr = "", printStr;

                while (true) {
                    macroDef = MDT.get(mdtPtr);

                    if (createParamArray) {
                        createFormalParamList(macroDef);
                        createParamArray = false;
                    } else {
                        def_tokens = tokenizeString(macroDef, " ");

                        if (def_tokens[0].equalsIgnoreCase("MEND")) {
                            break;
                        } else {
                            paramStr = replaceFormalParams(def_tokens[1]);
                        }
                        printStr = "+" + def_tokens[0] + " " + paramStr;
                        System.out.println(printStr);
                        out_pass2.println(printStr);
                    }
                    mdtPtr++;
                }
            } else {
                System.out.println(s);
                out_pass2.println(s);
            }
        }
        input.close();
        out_pass2.close();
    }

    static String replaceFormalParams(String formalParamList) {
        String returnStr = "";
        formalParamList = formalParamList.replace("#", "");
        String[] param_array = tokenizeString(formalParamList, ",");
        int index;
        String actualParam;

        for (int i = 0; i < param_array.length; i++) {
            index = Integer.parseInt(param_array[i]);
            if (index <= actualParams.size()) {
                actualParam = actualParams.get(index - 1);
            } else {
                actualParam = formalParams.get(index - 1);
            }
            returnStr += actualParam + ",";
        }

        returnStr = returnStr.substring(0, returnStr.length() - 1);
        return returnStr;
    }

    static void createFormalParamList(String macroDef) {
        String argList, param;
        String[] s_arr = tokenizeString(macroDef, " ");
        argList = s_arr[1];
        String[] arg_array = tokenizeString(argList, ",");

        formalParams.clear();
        for (int i = 0; i < arg_array.length; i++) {
            param = arg_array[i];
            if (param.contains("=")) {
                param = param.substring(param.indexOf("=") + 1);
            }
            formalParams.add(param);
        }
    }

    static void initiallizeTables() throws Exception {
        MDT = new ArrayList<>();
        MNT = new LinkedHashMap<>();
        formalParams = new ArrayList<>();
        actualParams = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/A4/MNT.txt")));
        String s;
        while ((s = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s, " ", false);
            MNT.put(st.nextToken(), st.nextToken());
        }
        br.close();

        br = new BufferedReader(new InputStreamReader(new FileInputStream("src/A4/MDT.txt")));
        while ((s = br.readLine()) != null) {
            String[] s_arr = tokenizeString(s, " ");
            if (s_arr.length == 0)
                continue;
            int index = Integer.parseInt(s_arr[0]);

            if (s_arr.length == 2) {
                MDT.add(index, s_arr[1]);
            } else if (s_arr.length == 3) {
                MDT.add(index, s_arr[1] + " " + s_arr[2]);
            }
        }
        br.close();
    }

    static String[] tokenizeString(String str, String separator) {
        StringTokenizer st = new StringTokenizer(str, separator, false);
        String[] s_arr = new String[st.countTokens()];
        for (int i = 0; i < s_arr.length; i++) {
            s_arr[i] = st.nextToken();
        }
        return s_arr;
    }
}
