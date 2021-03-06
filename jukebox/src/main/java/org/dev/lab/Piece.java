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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 *
 * @author betacentury
 */
public class Piece {
    
    private final Path path;
    
    public Piece(String _path)
    {
//        Path tmp = Paths.get(_path);
//        path = Files.exists(tmp) ? tmp : null;
        path = Paths.get(_path);
    }
    @Override
    public String toString()
    {
        return path != null ? path.getFileName().toString() : null;
    }
    public String getPath()
    {
        return path != null ? path.toString() : null;
    }
    @Override
    public boolean equals(Object _o){
        return _o instanceof Piece ? equals((Piece)_o) : false;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.path);
        return hash;
    }
    public boolean equals(Piece _o) {
        return path.equals(_o.path);
    }
}
