public class CommandFormatter {
	
	public static byte[] getByteArrayForTCP(String args[]) {
		byte[] format = new byte[getSize(args)];

		return format;
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