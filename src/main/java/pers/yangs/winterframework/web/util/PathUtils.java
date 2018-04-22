package pers.yangs.winterframework.web.util;

/**
 * Created by Ytadp on 2018/4/22.
 */
public class PathUtils {

    /**
     * 去掉首位的 斜杠
     * @param s
     * @return
     */
    public static String pathStr(String s){
        if(s.startsWith("/")){
            s = s.substring(1, s.length());
        }

        if(s.endsWith("/")){
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    /**
     * <p>
     *     路径拼接 主要用于拼接是 斜杠控制
     *      拼接后格式 xxx/xxx/xxx
     * </p>
     * */
    public static String pathAppend(String s1, String s2){
        // TODO: 2018/4/22 性能可能需要优化
        return pathStr(s1) + "/" + pathStr(s2);
    }
}
