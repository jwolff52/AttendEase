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

import java.util.ArrayList;

/**
 *
 * @author james.wolff
 */
public class EFileUtilities {
    private ArrayList<String> names;
    private ArrayList<Double> idNums;
    private ArrayList<Double> points;

    public EFileUtilities(){
        names=new ArrayList<String>();
        idNums=new ArrayList<Double>();
        points=new ArrayList<Double>();
    }
    
    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public ArrayList<Double> getIdNums() {
        return idNums;
    }

    public void setIdNums(ArrayList<Double> idNums) {
        this.idNums = idNums;
    }
    
    public ArrayList<Double> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Double> points) {
        this.points = points;
    }
}
