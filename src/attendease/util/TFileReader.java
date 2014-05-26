/************************************************************************
    AttendEase - A simple, point-and-click attendance program.
    Copyright (C) 2013-2014  James Wolff, Timothy Chandler, Sterling Long, Cole Howe

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*************************************************************************/

package attendease.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author timothy.chandler
 * @date Sep 16, 2013
 */
public class TFileReader {
    public static ArrayList readFile(File f) throws FileNotFoundException{
        FileInputStream fis=new FileInputStream(f);
        ArrayList<String> buffer=new ArrayList<String>();
        Scanner scanner = new Scanner(fis);
        while(scanner.hasNext()){
            buffer.add(scanner.nextLine());
        }
        return buffer;
    }
}
