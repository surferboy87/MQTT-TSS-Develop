/**
 * 
 */
package net.huraki.tss.parser;

import java.io.ByteArrayInputStream;

import net.huraki.tss.messages.AbstractMessage;

/**
 * @author Raphael Huber
 *
 */
public abstract class AbstractDecoder {
	
	public abstract AbstractMessage decode(ByteArrayInputStream bais);

}
