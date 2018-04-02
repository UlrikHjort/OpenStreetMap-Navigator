/*******************************************************************************
 *                                                                             *
 *                            OpenstreetMap Viewer                             *
 *                                                                             *
 *                              PngTile.java                                   *
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
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author uhh
 *
 */
public class PngTile
{
	
	
	public static int imageHeight = 0;
	public static int imageWitdh = 0;
	
	public BufferedImage image = null;
	
	/**
	 * 
	 */
	public PngTile()
	{
		readSlippyMap();
	}
	
	
	
	public PngTile(int zoom, double lat, double lon, String mapServer)
	{
		readSlippyMap(zoom, lat, lon, mapServer);	
	}

	
	/**
	 * 
	 * @param fileName
	 */
	public PngTile(String fileName)
	{
		readImage(fileName);
	}
	
	/**
	 * 
	 * @param fileName
	 */
	private void readImage(String fileName)
	{
		
		try {
			System.out.println("DIR: "
					+ System.getProperty("user.dir"));
			System.out.println("----------------");
			image = ImageIO.read(new File("./images/map_small.png"));
			PngTile.imageHeight = image.getHeight();
			PngTile.imageWitdh = image.getWidth();
			System.out.println("H W " + PngTile.imageHeight + " "
					+ PngTile.imageWitdh);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private void readSlippyMap()
	{
		image = SlippyMap.getTile();
		PngTile.imageHeight = image.getHeight();
		PngTile.imageWitdh = image.getWidth();
	}

	/**
	 * 
	 * @param zoom
	 * @param lat
	 * @param lon
	 * @param mapServer
	 */
	private void readSlippyMap(int zoom, double lat, double lon, String mapServer)
	{
		image = SlippyMap.getTile(zoom, lat, lon, mapServer);
		PngTile.imageHeight = image.getHeight();
		PngTile.imageWitdh = image.getWidth();
		
	}
}
