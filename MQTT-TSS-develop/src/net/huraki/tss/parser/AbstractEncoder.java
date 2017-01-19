/**
 * 
 */
package net.huraki.tss.parser;

import net.huraki.tss.messages.AbstractMessage;

/**
 * @author Raphael Huber
 *
 */
public abstract class AbstractEncoder {

	public abstract byte[] encode(AbstractMessage msg);

}
