package com.shihaohu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

/**
 * @author ShiHaoHu
 * @date 2024/8/18 09:01
 * @description
 */
public class application {
    public static void main(String[] args) {
        // 定义父文件夹路径和目标文件夹路径
        String parentFolder = "/Users/hushihao/自学笔记/java/起飞上/Java基础-资料";
        String newFolder = "/Users/hushihao/自学笔记/java/起飞上/笔记";
        System.out.println("dudu");

        // 创建目标文件夹
        File targetDir = new File(newFolder);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        int num = 0;
        // 遍历父文件夹下的所有子文件夹
        File parentDir = new File(parentFolder);
        if (parentDir.exists() && parentDir.isDirectory()) {
            moveMdFiles(parentDir, targetDir);
        } else {
            System.out.println("父文件夹不存在或不是一个文件夹。");
        }
    }

    // 移动子文件夹中的所有 .md 文件到目标文件夹
    private static void moveMdFiles(File sourceDir, File targetDir) {
        for (File file : Objects.requireNonNull(sourceDir.listFiles())) {
            System.out.println(sourceDir.getName());
            if (file.isFile() && file.getName().endsWith(".md") && sourceDir.getName().equals("笔记")) {
                try {
                    Path targetPath = Paths.get(targetDir.getAbsolutePath(), file.getName());
                    // 使用 Files.move() 移动文件
                    Files.move(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("移动文件: " + file.getName() + " 到 " + targetPath);
                } catch (IOException e) {
                    System.err.println("移动文件失败: " + file.getName());
                    e.printStackTrace();
                }
            } else if (file.isDirectory()) {
                moveMdFiles(file, targetDir);
            }
        }
    }
}
