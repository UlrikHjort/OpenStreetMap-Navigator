/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                              OSMNode.java                                   *
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
import com.nav.osm.mapview.PngTile;


/**
 * 
 * @author uhh
 * 
 */
public class OSMNode extends Element
{

	private double lat;
	private double lon;
	private long x;
	private long y;
	
	private Map<String, String> tags;

	public enum Suffix {
		N, S, E, W
	};

	
	
	public String getLatLonStr()
	{
		return "" + lat + " "+ lon + " ";
	}
	
	/**
	 * 
	 * @param osmNode
	 * @return distance in meter
	 */
	public double distanceTo(OSMNode osmNode)
	{
		/*
		 * var R = 6371; // km var dLat = (lat2-lat1).toRad(); var dLon
		 * = (lon2-lon1).toRad(); var lat1 = lat1.toRad(); var lat2 =
		 * lat2.toRad();
		 * 
		 * var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		 * Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) *
		 * Math.cos(lat2); var c = 2 * Math.atan2(Math.sqrt(a),
		 * Math.sqrt(1-a)); var d = R * c;
		 */

		double r = 6371.0 * 1000.0;

		double deltaLat = Math.toRadians(this.lat - osmNode.lat);

		double deltaLon = Math.toRadians(this.lon - osmNode.lon);

		double a = Math.sin(deltaLat / 2.0) * Math.sin(deltaLat / 2.0)
				+ Math.sin(deltaLon / 2.0)
				* Math.sin(deltaLon / 2.0) * Math.cos(this.lat)
				* osmNode.lat;

		double c = Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));

		double d = r * c;

		return d;

	}

	
	/**
	 * 
	 * @param osmNode
	 * @return
	 */
	public double bearingTo(OSMNode osmNode)
	{
		/*
		 * var y = Math.sin(dLon) * Math.cos(lat2); var x =
		 * Math.cos(lat1)*Math.sin(lat2) -
		 * Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon); var brng =
		 * Math.atan2(y, x).toDeg();
		 */

		double deltaLon = Math.toRadians(this.lon - osmNode.lon);

		double y = Math.sin(deltaLon) * Math.cos(osmNode.lon);
		double x = Math.cos(this.lat) * Math.sin(osmNode.lat)
				- Math.sin(this.lat) * Math.cos(osmNode.lat)
				* Math.cos(deltaLon);

		double bearing = Math.toDegrees(Math.atan2(y, x));

		return bearing;
	}

	
	/**
	 * 
	 * @param osmNode
	 * @return
	 */
	public double finalBearing(OSMNode osmNode)
	{
		double finalBearing = osmNode.bearingTo(this);

		return (finalBearing + 180.0) % 360.0;
	}


	/**
	 * 
	 * @param lat
	 * @param lon
	 */
	public OSMNode(double lat, double lon)
	{
		super(null, null, null);
		this.lat = lat;
		this.lon = lon;
		this.tags = null;
		
		this.latToX();
		this.lonToY();
	}

	
	/**
	 * 
	 * @param id
	 * @param visible
	 * @param tags
	 * @param lat
	 * @param lon
	 */
	public OSMNode(String id, String visible, Map<String, String> tags,
			String lat, String lon)
	{
		super(id, visible, tags);
		this.lat = Double.parseDouble(lat);
		this.lon = Double.parseDouble(lon);
		this.tags = tags;
	}

	
	/**
	 * 
	 * @param dec
	 * @return
	 */
	public static String DECtoDMS(double dec)
	{
		double degrees = (long) dec;
		double minutes_tmp = (dec - degrees) * 60.0;
		double minutes = (long) minutes_tmp;
		double seconds = Math.round((minutes_tmp - minutes) * 60);

		String dms = (long) degrees + "¡" + (long) minutes + "'"
				+ (long) seconds + "''";

		return dms;
	}

	
	/**
	 * 
	 * @param degrees
	 * @param minutes
	 * @param seconds
	 * @return
	 */
	public static double DMStoDEC(double degrees, double minutes,
			double seconds, Suffix suffix)
	{
		double dec = degrees + (minutes / 60) + (seconds / 3600);

		if ((suffix == Suffix.S) || (suffix == Suffix.E)) {
			dec *= -1;
		}

		return dec;
	}

	/**
	 * 
	 * @param DMS
	 * @return
	 */
	public double DMStoDEC(String DMS)
	{
		double degrees = 0.0;
		double minutes = 0.0;
		double seconds = 0.0;
		Suffix suffix = null;

		double dec = DMStoDEC(degrees, minutes, seconds, suffix);

		return dec;
	}
	
	/**
	 * 
	 */
	private void latToX()
	{
		double factor = this.lat / (Osm.maxLat + Osm.minLat);		
		this.x = (long) (PngTile.imageWitdh / factor); 
		System.out.println("Factor1: " + factor + "," + x);						
	}
	
	
	/**
	 * 
	 */
	private void lonToY()
	{
		double factor = this.lon / (Osm.maxLon + Osm.minLon);		
		this.y = (long) (PngTile.imageHeight / factor); 
		//System.out.println("X,Y: " + PngTile.imageWitdh + "," + PngTile.imageHeight + " ,,, "  + factor);
		System.out.println("Factor: " + factor + "," + y);
		
	}

}

// http://www.movable-type.co.uk/scripts/latlong.html
