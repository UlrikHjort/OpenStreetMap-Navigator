/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                              NodeParser.java                                *
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

package com.nav.osm.parser;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.nav.osm.model.OSMNode;

public class NodeParser {

	/**
	 * 
	 * @param node
	 * @return
	 */
	public static boolean isNode(Node node) {
		return (node.getNodeName().equals("node"));
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	public static OSMNode parseNode(Node node) {
		NamedNodeMap atts = node.getAttributes();

		/*
		 * String id = atts.getNamedItem("id").getNodeValue();
		 * getAttribute(atts, "visible"), getAttribute(atts, "timestamp"),
		 * getAttribute(atts, "version"), getAttribute(atts, "changeset"),
		 * getAttribute(atts, "user"), getAttribute(atts, "uid"),
		 * getAttribute(atts, "lat"),
		 */
//		System.out.println(getAttribute(atts, "lon"));
		OSMNode osmNode = new OSMNode(atts.getNamedItem("id").getNodeValue(),
				getAttribute(atts, "visible"), null, getAttribute(atts, "lat"),
				getAttribute(atts, "lon"));

		return osmNode;
	}

	/**
	 * 
	 * @param atts
	 * @param key
	 * @return
	 */
	private static String getAttribute(NamedNodeMap atts, String key) {
		Node node = atts.getNamedItem(key);
		return (node == null) ? null : node.getNodeValue();
	}

}
