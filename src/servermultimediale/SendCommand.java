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
package servermultimediale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author betacentury
 */
class SendCommand implements Runnable {
    
    private final String cmd,value[];
    private final ArrayList<Brano> al;
    protected final static String PLAY = "play", REMOVE = "remove", TOP = "top", KILL = "kill", VOLUME = "volume", SHUFFLE = "shuffle", GET = "get", LIST = "list", LAST = "last";
    
    public SendCommand(String _cmd) {
        this(_cmd,(String)null);
    }
        public SendCommand(String _cmd, ArrayList<Brano> _al) {
        this(_cmd,(String)null, _al);
    }
    public SendCommand(String _cmd, String _value) {
        this(_cmd,new String[] { _value });
    }
    public SendCommand(String _cmd, String[] _value) {
        this(_cmd, _value, null);
    }
    public SendCommand(String _cmd, String _value, ArrayList<Brano> _al) {
        this(_cmd,new String[] { _value }, _al);
    }
    public SendCommand(String _cmd, String[] _value, ArrayList<Brano> _al) {
        cmd = _cmd;
        value =  _value;
        al = _al;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", ServerMultimediale.PORT);
            BufferedReader socketIN = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socketOUT = new PrintWriter( new PrintStream (socket.getOutputStream() ));
            String buffer;
            System.out.println(cmd);
            socketOUT.println(cmd);
            socketOUT.flush();
            for (int i=0; i<value.length && value[i] != null ; i++ ) 
            {
                System.out.println(value[i]);
                socketOUT.println(value[i]);
                socketOUT.flush();
            }
            while( ascolta(cmd) && (buffer = socketIN.readLine()) != null )
            {
                al.add(new Brano(buffer));
            }
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SendCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static boolean ascolta (String _cmd) {
        switch (_cmd.toLowerCase())
        {
            case "get":
            case "list":
            case "last":
                return true;
            default:
                return false;
        }
    }
}
