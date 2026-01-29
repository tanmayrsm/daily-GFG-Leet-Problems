// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class Main {
    public static String simplifyPath(String path) {
        // Implement Level 1
        String[] split = path.split("\\/", 0);
        Stack<String> st = new Stack<>();
        for (String file : split) {
            if(!file.isEmpty() && !file.equals(".") && !file.equals("")) {
                // System.out.println("cyrr : " + file);
                if (file.equals("..") && !st.isEmpty()) st.pop();
                else if (!file.equals("..")) st.push(file);
            }
        }
        int m = st.size();
        String[] res = new String[m];
        // System.out.println("st::" + st);
        while(!st.isEmpty()) {
            res[m - 1] = st.pop();
            m--;
        }
        return "/" + String.join("/", res);
    }
    private static String getRelativePath(String path) {
        String simplifiedPath = simplifyPath(path);
        int n = simplifiedPath.length();
        if (n == 1) return "";
        return simplifiedPath.substring(1);
    }

    public static String cd(String cwd, String newPath) {
        // Implement Level 3
        int m = newPath.length();
        cwd = simplifyPath(cwd);
        if(m == 0)  return cwd;
        if (newPath.charAt(0) !='/')
            return simplifyPath(cwd + "/"+newPath);
        return simplifyPath(newPath);
    }

    public static List<String> cdHistory(String cwd, List<String> moves) {
        // Implement Level 4
        List<String> ans = new ArrayList<>();
        String curr = cwd;
        for (String move : moves) {
            curr = cd(curr, move);
            ans.add(curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        // System.out.println(simplifyPath("/home//foo/"));
        // System.out.println(getRelativePath("/../"));
        // System.out.println(simplifyPath("/a/./b/../../c/"));
        // System.out.println(getRelativePath(simplifyPath("../a")));
        System.out.println("cd:" + cd("/a/b", "c/d/../e"));
        System.out.println("cd:" + cd("/a/b/c", "../../d"));
        System.out.println("cd:" + cd("/x", "/m/n"));
        System.out.println("cd:" + cd("/a/b", ".."));
        System.out.println("cdHist ::" + cdHistory("/a/b", Arrays.asList("c", "..", "../d", "/x")));

        // /a/b/c, ../../d
        // /x, /m/n

    }
}