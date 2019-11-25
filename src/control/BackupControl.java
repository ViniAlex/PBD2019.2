/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Backup;

/**
 *
 * @author Vinícius
 */
public class BackupControl extends Thread {

    private LocalTime update;//h attt
    private LocalTime atual;//h atual

    private boolean backup = true;

    public BackupControl() {
        this.update = LocalTime.of(12, 0);
        this.atual = LocalTime.now();
        start();
    }

    @Override
    public void run() {
        super.run();

        while (true) {

            if (backup && atual.isAfter(update)) {
                try {
                    Backup.backup(new File("..").getCanonicalPath());
                    backup = false;
                    stop();
                } catch (IOException ex) {
                    Logger.getLogger(BackupControl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }

    }

}
