package com.softdesign.school.utils;

import android.util.Log;

/**
 * Зарефакторить код логера в соответствии с данными на лекции рекомендациями, исспользовать подход DRY Don’t repeat yourself (не повторяй себя) - 
 * т.е. избегаем повторения уже ранее написанного кода + Javadoc, 
 * логер должен исспользовать различные уровни вывода логов (Verbose, debug, info, error, warn, assert ).
 */
public class Lg {

    /**
     * Перечисление всех уровней логов
     */
    public enum Loglvl{
        Verbose,
        Debug,
        Info,
        Warning,
        Error,
        Assert;
    }

    private static final String PREFIX = "HTC ";
    public static final int LOGCAT_BUFFER_SIZE = 3000;

    private static boolean shouldLog() {
    //return BuildConfig.IS_LOGCAT_LOGGER_ENABLED;
    return true;
    //return false;
    }

    /**
     * Обрабатывает и кусками(или полность (согласно длинне LOGCAT_BUFFER_SIZE))посылает сообщение дальше методу throw_log
     */
    public static void send_log (Loglvl lvl, String tag, String text){
        if (shouldLog()) {
            if (text.length() > LOGCAT_BUFFER_SIZE){
                String s = text;
                while (s.length() > LOGCAT_BUFFER_SIZE){
                    String s1 = s.substring(0, LOGCAT_BUFFER_SIZE);
                    s = s.substring(LOGCAT_BUFFER_SIZE);
                    throw_log(lvl, PREFIX + tag, s1);
                }
                throw_log(lvl, PREFIX + tag, s);
            } else {
                throw_log(lvl, PREFIX + tag, text);
            }
        }
    }

    /**
     * Кидает сообщение в лог согласно уровню
     */
    private static void throw_log(Loglvl lvl, String tag, String text){
        switch (lvl){
            case Verbose: Log.v(tag, text) ;   break;
            case Debug: Log.d(tag, text) ;   break;
            case Info: Log.i(tag, text) ;   break;
            case Error: Log.e(tag, text) ;   break;
            case Warning: Log.w(tag, text) ;   break;
            case Assert: Log.wtf(tag,text);   break;
        }
    }
}
