/*
 * Copyright (C) 2015 betacentury
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dev.lab;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author betacentury
 */
public class Kill implements Runnable{

    @Override
    public void run() {
        try {
            ProcessBuilder pb = new ProcessBuilder("sh", "-c","kill `pidof mplayer`");
            pb.directory(new File(ServerMultimediale.MUSICPATH.toString()));
            pb.redirectError(ProcessBuilder.Redirect.appendTo(new File(ServerMultimediale.LOGFILE.toString())));
            pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File(ServerMultimediale.LOGFILE.toString())));
            Process p = pb.start();
            p.waitFor();
            System.out.println("killall mplayer");
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Kill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}