/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                              SlippyMain.java                                *
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

package com.nav.osm.mapview;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 
 * @author uhh
 * 
 */
public class SlippyMap
{
	/**
	 * 
	 * @return
	 */
	private String getUrl()
	{

		int zoom = 17; // 10;
		double lat = 55.615679d;
		double lon = 12.980815d;

		int xtile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
		int ytile = (int) Math.floor((1 - Math.log(Math.tan(Math
				.toRadians(lat))
				+ 1
				/ Math.cos(Math.toRadians(lat))) / Math.PI)
				/ 2 * (1 << zoom));
		return ("http://tile.openstreetmap.org/" + "" + zoom + "/"
				+ xtile + "/" + ytile + ".png");
	}

	/**
	 * 
	 * @param mapServer
	 * @param lat
	 * @param lon
	 * @param zoom
	 * @return
	 */
	private static String getUrl(final String mapServer, final double lat,
			final double lon, final int zoom)
	{

		int xtile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
		int ytile = (int) Math.floor((1 - Math.log(Math.tan(Math
				.toRadians(lat))
				+ 1
				/ Math.cos(Math.toRadians(lat))) / Math.PI)
				/ 2 * (1 << zoom));
		return ("http://" + mapServer + "/" + "" + zoom + "/" + xtile
				+ "/" + ytile + ".png");
	}

	/**
	 * 
	 * @return
	 */
	public static BufferedImage getTile()
	{
		int zoom = 17; // 10;
		double lat = 55.615679d;
		double lon = 12.980815d;
		String mapServer = "tile.openstreetmap.org";

		BufferedImage image = null;
		URL url = null;
		String url_str = getUrl(mapServer, lat, lon, zoom);

		try {
			url = new URL(url_str);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			image = ImageIO.read(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * 
	 * @param zoom
	 * @param lat
	 * @param lon
	 * @param mapServer
	 * @return
	 */
	public static BufferedImage getTile(final int zoom, final double lat,
			final double lon, String mapServer)
	{

		BufferedImage image = null;
		URL url = null;
		String url_str = getUrl(mapServer, lat, lon, zoom);

		try {
			url = new URL(url_str);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			image = ImageIO.read(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * 
	 * @param x
	 * @param zoom
	 * @return
	 */
	private static double tileToLon(int x, int zoom)
	{
		return x / Math.pow(2.0, zoom) * 360.0 - 180;
	}

	/**
	 * 
	 * @param y
	 * @param zoom
	 * @return
	 */
	private static double tileToLat(int y, int zoom)
	{
		double n = Math.PI - (2.0 * Math.PI * y) / Math.pow(2.0, zoom);
		return Math.toDegrees(Math.atan(Math.sinh(n)));
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param zoom
	 * @return
	 */
	public BoundingBox tile2boundingBox(final int x, final int y,
			final int zoom)
	{
		BoundingBox bb = new BoundingBox();
		bb.north = tileToLat(y, zoom);
		bb.south = tileToLat(y + 1, zoom);
		bb.west = tileToLon(x, zoom);
		bb.east = tileToLon(x + 1, zoom);
		return bb;
	}

	/**
	 * SLETTES!
	 * 
	 * @param lat
	 * @param lon
	 * @param zoom
	 * @return
	 */
	public static String getTileNumber(final double lat, final double lon,
			final int zoom)
	{
		int xtile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
		int ytile = (int) Math.floor((1 - Math.log(Math.tan(Math
				.toRadians(lat))
				+ 1
				/ Math.cos(Math.toRadians(lat))) / Math.PI)
				/ 2 * (1 << zoom));
		return ("" + zoom + "/" + xtile + "/" + ytile);
	}

}
