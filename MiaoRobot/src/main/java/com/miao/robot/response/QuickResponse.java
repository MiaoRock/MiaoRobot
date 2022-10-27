package com.miao.robot.response;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class QuickResponse {

    public static String quickResponseFile(String request) {
        String response = null;
        String req2ResLine = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            String fileName = "D:\\Workspaces\\MyRobot\\src\\main\\resources\\QuickResponse.miao";
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            while ((req2ResLine = bufferedReader.readLine()) != null) {
                String[] req2Res = req2ResLine.split("\\|");
                if (req2Res[0].equals(request.trim())) {
                    response = req2Res[1];
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("File - req:" + request + " res:" + response);
        return response;
    }
}
