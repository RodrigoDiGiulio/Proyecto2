/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.time.Duration;
import java.time.Instant;


/**
 *
 * @author RDG
 */
public class clock {
    private Instant start;
    private Instant end;
    private Duration pauseDuration = Duration.ZERO;
    private Instant pauseStart;
    private boolean isPaused = false;
    private String lastTime;

    public void start() {
        if (pauseStart != null) {
            pauseDuration = pauseDuration.plus(Duration.between(pauseStart, Instant.now()));
            pauseStart = null;
        } else {
            start = Instant.now();
        }
        isPaused = false;
    }

    public void stop() {
        end = Instant.now();
    }

    public void pause() {
        pauseStart = Instant.now();
        isPaused = true;
    }
    
    public String getTime() {
        if (start == null){
            return "00:00";
        }
        if (isPaused){
            return lastTime;
        }
        Instant currentEnd = (end != null) ? end : Instant.now();
        Duration duration = Duration.between(start, currentEnd).minus(pauseDuration);
        int runTime = (int) duration.getSeconds();
    //    int runTime = 100;
        int clockSec = runTime % 60;
        int clockMin = runTime / 60;
        String secS = (clockSec < 10) ? "0" + clockSec : "" + clockSec;
        String minS = (clockMin < 10) ? "0" + clockMin : "" + clockMin;
        String currentTime = minS + ":" + secS;
        lastTime = currentTime;
        return currentTime;
    }
}