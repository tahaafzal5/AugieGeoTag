/*
	class SmallEndian contains some useful methods to deal with small endian calculation
 */

package src.endian;

public class SmallEndian
{
	//Pre: this returned unsigned integer is small endian version
	//Return: an int represent a unsigned 16 bit int.
	public static int getInt16(byte data1, byte data2)
	{
		int result = 0;
		result = result | (data2 & 0xFF);
		result = result << 8;
		result = result | (data1 & 0xFF);
		return result;
	}

	//Pre: this returned unsigned integer is small endian version
	//Return: an long represent a unsigned 32 bit long.
	public static int getInt32(byte data1, byte data2, byte data3, byte data4)
	{
		int result = 0;
		result = result | (data4 & 0xFF);
		result = result << 8;
		result = result | (data3 & 0xFF);
		result = result << 8;
		result = result | (data2 & 0xFF);
		result = result << 8;
		result = result | (data1 & 0xFF);
		return result;
	}

	//Pre: this returned unsigned integer is small endian version
	//Return: an long represent a unsigned 32 bit integer.
	public static long getLong32(byte data1, byte data2, byte data3, byte data4)
	{
		long result = 0;
		result = result | (data4 & 0xFF);
		result = result << 8;
		result = result | (data3 & 0xFF);
		result = result << 8;
		result = result | (data2 & 0xFF);
		result = result << 8;
		result = result | (data1 & 0xFF);
		return result;
	}

	//Pre: this returned unsigned integer is small endian version
	//Return: a signed short which 2 bytes of data represent
	public static short getSignedShort(byte data1, byte data2)
	{
		short signed_short = 0;
		signed_short = (short)(signed_short | (data2 & 0xFF));
		signed_short = (short)(signed_short << 8);
		signed_short = (short)(signed_short | (data1 & 0xFF));
		return signed_short;
	}

	//Helper function of getInt32
	public static int getInt32(byte[] value)
	{
		return getInt32(value[0], value[1], value[2], value[3]);
	}

	//Helper function of getSignedShort
	public static short getSignedShort(byte[] value)
	{
		return getSignedShort(value[0], value[1]);
	}

	//Helper function of getLong32
	public static long getLong32(byte[] value)
	{
		return getLong32(value[0], value[1], value[2], value[3]);
	}

	//Helper function of getInt16
	public static int getInt16(byte[] value)
	{
		return getInt16(value[0], value[1]);
	}

}