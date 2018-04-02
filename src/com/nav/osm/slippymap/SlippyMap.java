/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                              SlipyMap.java                                  *
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

package com.nav.osm.slippymap;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class SlippyMap {

	private String getUrl() {

		int zoom = 17; //10;
		double lat = 55.615679d;
		double lon = 12.980815d;

		int xtile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
		int ytile = (int) Math
				.floor((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1
						/ Math.cos(Math.toRadians(lat)))
						/ Math.PI)
						/ 2 * (1 << zoom));
		return ("http://tile.openstreetmap.org/" + "" + zoom + "/" + xtile
				+ "/" + ytile + ".png");
	}
	

	private String getUrl(String mapServer, double lat, double lon, int zoom) {


		int xtile = (int) Math.floor((lon + 180) / 360 * (1 << zoom));
		int ytile = (int) Math
				.floor((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1
						/ Math.cos(Math.toRadians(lat)))
						/ Math.PI)
						/ 2 * (1 << zoom));
		return ("http://"+ mapServer +"/" + "" + zoom + "/" + xtile
				+ "/" + ytile + ".png");
	}
/*
	public Bitmap getTile() 
	{
		int zoom = 17; //10;
		double lat = 55.615679d;
		double lon = 12.980815d;
		String mapServer = "tile.openstreetmap.org";

		Bitmap bitmap = null;
		URL url = null;
		String url_str = getUrl(mapServer, lat, lon, zoom);
		try {
			url = new URL(url_str);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			try {
				bitmap = BitmapFactory.decodeStream(url.openStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
	}
*/
}

