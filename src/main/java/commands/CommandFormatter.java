package main.java.commands;

public class CommandFormatter {
	
	private static byte[] getByteArray(String args[]) {
		byte[] format = new byte[getSize(args)];

		int index = 0;

		for(int i=0;i<args.length;i++) {

			byte[] argBytes = args[i].getBytes(); 

			if(i == (args.length -1)) {
				format[index++] = (byte) Integer.parseInt(args[i]);		//to change if we want to handle integers greater than 1 byte
				break;
			}

			for(int j=0;j<args[i].length();j++)
				format[index++] = argBytes[j];

			format[index++] = (byte) ' ';
		}

		return format;
	}

	public static byte[] formatForTCP(String[] args) {

		byte[] message = getByteArray(args);

		for(int i=(message.length - 3);i<message.length;i++)
			message[i] = (byte) '*';

		return message;
	}

	public static byte[] formatForUDP(String[] args) {

		byte[] message = getByteArray(args);

		for(int i=(message.length - 4);i<message.length;i++)
			message[i] = (byte) '+';
		
		return message;
	}

	private static int getSize(String args[]) {
		int size = 0;

		for(int i=0;i<args.length;i++) {

			for(int j=0;j<args[i].length();j++) {
				size++;
			}

			if(i == (args.length - 1)) {		//if the last argument was handled
				size += 3;					//add size for terminating characters
				break;
			} 

			size++;
		}

		return size;
	}

}