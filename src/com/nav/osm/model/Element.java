/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                              Element.java                                   *
 *                                                                             *
 *                                   Module                                    *
 *                                                                             *
 *                    Copyright (C) 2011 Ulrik H¿rlyk Hjort                    *
 *                                                                             *
 *   OpenstreetMap Viewer is free software;  you can  redistribute it          *
 *   and/or modify it under terms of the  GNU General Public License           *
 *   as published  by the Free Software  Foundation;  either version 2,        *
 *   or (at your option) any later version.                                    *
 *   OpenstreetMap Viewer is distributed in the hope that it will be           *
 *   useful, but WITHOUT ANY WARRANTY;  without even the  implied warranty     *
 *   of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 *   See the GNU General Public License for  more details.                     *
 *   You should have  received  a copy of the GNU General                      *
 *   Public License  distributed with Yolk.  If not, write  to  the  Free      *
 *   Software Foundation,  51  Franklin  Street,  Fifth  Floor, Boston,        *
 *   MA 02110 - 1301, USA.                                                     *
 *                                                                             *
 ******************************************************************************/

package com.nav.osm.model;

import java.util.Map;

public class Element {
    public String id;
    protected String visible;
    protected String timestamp;
    protected String version;
    protected String changeset;
    protected String user;
    protected String uid;
    protected Map<String, String> tags;
    
    
    public Element(String id, String visible,
            Map<String, String> tags) {

        this.id = id;
        this.visible = visible;
        this.timestamp = timestamp;
        this.version = version;
        this.changeset = changeset;
        this.user = user;
        this.uid = uid;
        this.tags = tags;

    }

}
