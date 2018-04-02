/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                                  Way.java                                   *
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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Way extends Element
{

	private List<OSMNode> nodes;
	public String name;

	/**
	 * 
	 * @param id
	 * @param visible
	 * @param tags
	 * @param nodes
	 * @param name
	 */
	public Way(String id, String visible, Map<String, String> tags,
			List<OSMNode> nodes, String name)
	{
		super(id, visible, tags);
		this.nodes = nodes;
		this.name = name;
		if (name.startsWith("Magnus")) {
			System.out.println("NAME: " + name);
		}
		// System.out.println("LeNgth: " + this.length() * 1000);

	}

	public String getLatLonList()
	{
		String list = this.name + " ";

		for (OSMNode node : nodes) {
			list += node.getLatLonStr();
		}
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public double length()
	{
		double distance = 0.0;

		Iterator<OSMNode> iterator = nodes.iterator();
		OSMNode current = iterator.next();

		while (iterator.hasNext()) {
			OSMNode next = iterator.next();
			distance += current.distanceTo(next);
			current = next;
		}

		return distance;

	}

}
