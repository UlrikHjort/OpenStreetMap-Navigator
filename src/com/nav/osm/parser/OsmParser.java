/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                              OSMPArser.java                                 *
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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.nav.osm.model.OSMNode;
import com.nav.osm.model.Osm;
import com.nav.osm.model.Way;

/**
 * 
 * @author uhh
 *
 */
public class OsmParser
{

	/**
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static Osm parse(String path) throws Exception
	{

		Document doc;
		DocumentBuilder builder;

		org.w3c.dom.Node node;
		NodeList nodesList;

		Map<String, OSMNode> nodes = new LinkedHashMap<String, OSMNode>();

		builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		doc = builder.parse(path);

		nodesList = doc.getChildNodes().item(0).getChildNodes();

		Osm osm = new Osm();
		for (int i = 0; i < nodesList.getLength(); i++) {

			node = nodesList.item(i);

			if (isBounds(node)) {
				NamedNodeMap atts = node.getAttributes();
				
				osm.setLimits(getAttribute(atts, "minlat"),
						getAttribute(atts, "minlon"),
						getAttribute(atts, "maxlat"),
						getAttribute(atts, "maxlon"));

			} else if (NodeParser.isNode(node)) {
				OSMNode osmNode = NodeParser.parseNode(node);
				nodes.put(osmNode.id, osmNode);

			} else if (WayParser.isWay(node)) {
				Way way = WayParser.parseWay(node, nodes);
				osm.addWay(way);
				

			} else if (RelationParser.isRelation(node)) {
				
			}
		}
		
		System.out.println("N LEN: " + nodes.size());

		return osm;
	}

	
	
	/**
	 * 
	 * @param nodes
	 * @return
	 */
	protected static Map<String, String> parseTags(NodeList nodes)
	{

		Map<String, String> tags = new HashMap<String, String>();

		for (int i = 0; i < nodes.getLength(); i++) {

			Node node = nodes.item(i);

			if (node.getNodeName().equals("tag")) {

				addTag(tags, node);

			}
		}

		return tags;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private static boolean isBounds(Node node)
	{
		return (node.getNodeName().equals("bounds"));
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
	 * @param tags
	 * @param node
	 */
	private static void addTag(Map<String, String> tags, Node node)
	{
		String key = node.getAttributes().getNamedItem("k")
				.getNodeValue();
		String value = node.getAttributes().getNamedItem("v")
				.getNodeValue();

		if (tags.get(key) != null) {

			tags.put(key, tags.get(key) + ";" + value);

		} else {

			tags.put(key, value);

		}
	}
}
