/*
 * DB-SVG Copyright 2009 Derrick Bowen
 *
 * This file is part of DB-SVG.
 *
 *   DB-SVG is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   DB-SVG is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with DB-SVG.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */

package DBViewer.models;

import java.sql.*;
import java.util.*;
import DBViewer.objects.model.*;

/**
 * Interface for the DAO that accesses the actual databases to be inspected.
 * 
 * @author derrick.bowen
 */
public interface IMainDAO {

     public Connection getDefaultConnection();
     public Map<String, Table> getTables(Connection conn, String schemaName) throws Exception;
     
}