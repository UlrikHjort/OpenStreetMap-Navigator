/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                              WayParser.java                                   *
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



/**
 * 


 * @author uhh
 *
 */

package com.nav.osm.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nav.osm.model.OSMNode;
import com.nav.osm.model.Way;

public class WayParser
{

	/**
	 * 
	 * @param node
	 * @return
	 */
	public static boolean isWay(Node node)
	{
		return (node.getNodeName().equals("way"));
	}

	/**
	 * 
	 * @param atts
	 * @param key
	 * @return
	 */
	private static String getAttribute(NamedNodeMap atts, String key)
	{
		Node node = atts.getNamedItem(key);
		return (node == null) ? null : node.getNodeValue();
	}

	/**
	 * 
	 * @param node
	 * @param nodes
	 * @return
	 */
	public static Way parseWay(Node node, Map<String, OSMNode> nodes)
	{

		NamedNodeMap atts = node.getAttributes();

		String id = atts.getNamedItem("id").getNodeValue();

		Way way = new Way(id, getAttribute(atts, "visible"),
				OsmParser.parseTags(node.getChildNodes()),
				getNodes(node.getChildNodes(), nodes),getName(node.getChildNodes(), nodes));
		return way;
	}

	/**
	 * 
	 * @param children
	 * @param nodes
	 * @return
	 */
	private static List<OSMNode> getNodes(NodeList children,
			Map<String, OSMNode> nodes)
	{
		List<OSMNode> wayNodes = new ArrayList<OSMNode>();

		Node node;
		String nodeName;

		for (int i = 0; i < children.getLength(); i++) {

			node = children.item(i);
			nodeName = node.getNodeName();

			if (nodeName.equals("nd")) {

				wayNodes.add(nodes.get(node.getAttributes()
						.getNamedItem("ref")
						.getNodeValue()));
			}

		}

		return wayNodes;
	}

	/**
	 * 
	 * @param children
	 * @param nodes
	 * @return
	 */
	private static String getName(NodeList children,
			Map<String, OSMNode> nodes)
	{

		Node node = null;
		String nodeName = null;
		String wayName = "";

		for (int i = 0; i < children.getLength(); i++) {

			node = children.item(i);
			nodeName = node.getNodeName();

			if (nodeName.equals("tag")) {
				String type = node.getAttributes()
						.getNamedItem("k")
						.getNodeValue();

				if (type.equals("name")) {
					wayName = node.getAttributes()
							.getNamedItem("v")
							.getNodeValue();
				} 
			}

		}

		return wayName;
	}

}
