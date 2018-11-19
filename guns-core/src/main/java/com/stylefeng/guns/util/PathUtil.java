package com.stylefeng.guns.util;

import java.io.File;

/**
 * 目录工具
 * Created by HH on 2017/4/12.
 */
public class PathUtil {

    /**
     * 组装路径
     *
     *
     * 组装的路径： path.package.class
     *
     * @param listPath
     * @return
     */
    public static String generateClasspath(String... listPath) {
        StringBuffer pathBuffer = new StringBuffer();
        String pathSeparator = ".";

        for(String path : listPath){
            if (path.indexOf("\\\\") != -1)
                path = path.replaceAll("\\\\", pathSeparator);

            if (pathBuffer.length() > 0) {
                String lastChar = String.valueOf(pathBuffer.charAt(pathBuffer.length() - 1));
                String firstChar = String.valueOf(path.charAt(0));

                if (!(pathSeparator.equals(lastChar) || pathSeparator.equals(firstChar)))
                    // 路径中没有分隔符，需要加上
                    pathBuffer.append(pathSeparator);
            }
            pathBuffer.append(path);
        }

        String result = pathBuffer.toString();

        if (pathSeparator.equals(String.valueOf(result.charAt(0))))
            result = pathBuffer.substring(1);

        if (pathSeparator.equals(String.valueOf(result.charAt(result.length() - 1))))
            result = result.substring(0, pathBuffer.length() - 1);

        return result;
    }

    /**
     * 组装路径
     *
     *
     * 组装的路径： /path/folder/file
     *
     * @param listPath
     * @return
     */
    public static String generate(String... listPath) {
        StringBuffer pathBuffer = new StringBuffer();
        String pathSpliter = "/";
        for(String path : listPath){
            if (path.indexOf("\\\\") != -1)
                path = path.replaceAll("\\\\", pathSpliter);

            if (pathBuffer.length() > 0) {
                String lastChar = String.valueOf(pathBuffer.charAt(pathBuffer.length() - 1));
                String firstChar = String.valueOf(path.charAt(0));

                if (!(pathSpliter.equals(lastChar) || pathSpliter.equals(firstChar)))
                    // 路径中没有分隔符，需要加上
                    pathBuffer.append(pathSpliter);
            }
            pathBuffer.append(path);
        }

        if (pathSpliter.equals(String.valueOf(pathBuffer.charAt(pathBuffer.length() - 1))))
            return pathBuffer.substring(0, pathBuffer.length() - 1);
        else
            return pathBuffer.toString();
    }

    public static File createPath(String path) {
        File targetPath = new File(path);

        if (null == targetPath )
            return null;

        if (! (targetPath.exists()) )
            targetPath.mkdirs();

        return targetPath;
    }

    public static File createPath(File root, String path) {
        if (null == root)
            return null;
        if (null == path)
            return null;
        if (!root.exists()||!root.isDirectory())
            root.mkdirs();

        return createPath(generate(root.getAbsolutePath(), path));
    }

    public static boolean deleteFiles(String file) {
        File f = new File(file);
        if(!f.exists()){
            return true;
        }

        if(f.isFile()){
            return f.delete();
        }


        if(f.isDirectory()){
            boolean result = true;
            File[] files = f.listFiles();
            for(File f1 :  files){
                if(!deleteFiles(f1.getPath())){
                    result = false;
                }
            }
            if(result){
                result = f.delete();
            }
            return result;
        }

        return false;
    }
}
