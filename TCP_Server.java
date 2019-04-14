
/**  TCP Server
 * Code is taken from Computer Networking: A Top-Down Approach Featuring 
 * the Internet, second edition, copyright 1996-2002 J.F Kurose and K.W. Ross, 
 * All Rights Reserved.
 **/

import java.io.*;
import java.net.*;

class TCP_Server {

	public static void main(String argv[]) throws Exception {
		String clientSentence;
		String capitalizedSentence;

		ServerSocket welcomeSocket = new ServerSocket(45676);
		try {
			while (true) {

				Socket connectionSocket = welcomeSocket.accept();
				while (true) {
					BufferedReader inFromClient = new BufferedReader(
							new InputStreamReader(connectionSocket.getInputStream()));

					DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

					clientSentence = inFromClient.readLine();
					if (clientSentence.contains("STOP")) {
						welcomeSocket.close();
						return;
					}
					capitalizedSentence = clientSentence.toUpperCase() + '\n';
					System.out.println("FROM CLIENT: " + capitalizedSentence);
					outToClient.writeBytes(capitalizedSentence);

				}
			}
		} catch (Exception e) {
			welcomeSocket.close();
		}
	}
}
