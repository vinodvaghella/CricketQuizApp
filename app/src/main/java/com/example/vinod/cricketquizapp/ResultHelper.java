package com.example.vinod.cricketquizapp;

/**
 * Created by vinod on 30/09/2016.
 */

/**
 * This method is called for result helper for final result on quiz result page.
 */

public class ResultHelper {
    private static String _resultMessage;


    public static String get_resultMessage() {
        return _resultMessage;
    }

    public static void set_resultMessage(String resultMessage) {
        _resultMessage = resultMessage;
    }
}
