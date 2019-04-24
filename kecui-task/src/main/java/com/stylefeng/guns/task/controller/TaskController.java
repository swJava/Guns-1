package com.stylefeng.guns.task.controller;

import com.stylefeng.guns.task.batch.ClassImportTask;
import com.stylefeng.guns.task.batch.SignImportTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/4/10 18:57
 * @Version 1.0
 */
@Controller
@RequestMapping("/console")
public class TaskController {
    @Autowired
    private ClassImportTask classImportTask;

    @Autowired
    private SignImportTask signImportTask;

    @RequestMapping("/start/class")
    @ResponseBody
    public String startBatchClassTask(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                classImportTask.handleClassImport();
            }
        });

        executorService.shutdown();

        return "ok";
    }


    @RequestMapping("/start/sign")
    @ResponseBody
    public String startBatchSignTask(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                signImportTask.handleSignImport();
            }
        });

        executorService.shutdown();

        return "ok";
    }
}
