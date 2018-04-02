/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                                  OSM.java                                   *
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

import java.util.HashSet;
import java.util.Set;

public class Osm
{

	public static double minLat = 0.0;
	public static double minLon = 0.0;
	public static double maxLat = 0.0;
	public static double maxLon = 0.0;

	private double deltaLat = 0.0;
	private double deltaLon = 0.0;

	private OSMNode leftLower = null;
	private OSMNode leftUpper = null;
	private OSMNode rightLower = null;
	private OSMNode rightUpper = null;

	private double xSize = 0.0;
	private double ySize = 0.0;

	private Set<OSMNode> nodes;
	private Set<Way> ways;
	private Set<Relation> relations;

	public String getWay(String name)
	{
		System.out.println("WAYS: " + ways.size());
		for (Way way : ways) {
			if (way.name.equals(name)) {
				return way.getLatLonList();
			}
		}
		return null;
	}

	/**
	 * 
	 */
	public Osm()
	{
		nodes = new HashSet<OSMNode>();
		ways = new HashSet<Way>();
		relations = new HashSet<Relation>();
	}

	/**
	 * 
	 * @param osmNode
	 */
	public void addNode(OSMNode osmNode)
	{
		nodes.add(osmNode);
	}

	/**
	 * 
	 * @param way
	 */
	public void addWay(Way way)
	{
		ways.add(way);
	}

	public void addRelation(Relation relation)
	{
		relations.add(relation);
	}

	/**
	 * 
	 */
	public void printSettings()
	{
		System.out.println("Min Lat:" + this.minLat);
		System.out.println("Min Lon:" + this.minLon);
		System.out.println("Max Lat:" + this.maxLat);
		System.out.println("Max Lon:" + this.maxLon);

		System.out.println("Delta Lat: " + this.deltaLat);
		System.out.println("Delta Lon: " + this.deltaLon);

		System.out.println("X:" + this.xSize);
		System.out.println("Y:" + this.ySize);
	}

	/**
	 * 
	 * @param minLat
	 * @param minLon
	 * @param maxLat
	 * @param maxLon
	 */
	public void setLimits(String minLat, String minLon, String maxLat,
			String maxLon)
	{
		this.minLat = Double.parseDouble(minLat);
		this.minLon = Double.parseDouble(minLon);
		this.maxLat = Double.parseDouble(maxLat);
		this.maxLon = Double.parseDouble(maxLon);

		double latTmp = this.maxLat - this.minLat;
		double lonTmp = this.maxLon - this.minLon;

		this.deltaLat = latTmp - ((long) latTmp);
		this.deltaLon = lonTmp - ((long) lonTmp);
		/*
		 * leftLower = new OSMNode(this.minLat, this.minLon); leftUpper
		 * = new OSMNode(this.minLat, this.maxLon); rightLower = new
		 * OSMNode(this.maxLat, this.minLon); rightUpper = new
		 * OSMNode(this.maxLat, this.maxLon);
		 */
		leftLower = new OSMNode(this.minLon, this.minLat);
		leftUpper = new OSMNode(this.minLon, this.maxLat);
		rightLower = new OSMNode(this.maxLon, this.minLat);
		rightUpper = new OSMNode(this.maxLon, this.maxLat);

		xSize = leftLower.distanceTo(rightLower);
		ySize = leftLower.distanceTo(leftUpper);
	}

	/**
	 * 
	 * @return
	 */
	public Set<OSMNode> getNodes()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Set<OSMNode> getWays()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Set<OSMNode> getRelations()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
