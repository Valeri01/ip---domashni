package org.elsys.netprog.rest;

import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;


@Path("/")
public class GameController {
	public static String hash;
	public static int length = 2;
	public static byte[] input = new byte[length];

	@POST
	@Path("/surverpost/hash/{hash}/input/{input}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response startGame(@PathParam("hash") String hashs, @PathParam("input") String inputs)
			throws URISyntaxException, NoSuchAlgorithmException {

		byte[] encodedBytes = Base64.getUrlDecoder().decode(inputs);

		if (Arrays.equals(encodedBytes, input) && hashs.equals(hash)) {
			return Response.status(200).build();
		} else {
			return Response.status(406).build();
		}

	}

	@GET
	@Path("/surverget")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response getGames() throws NoSuchAlgorithmException {
		JSONObject obj = new JSONObject();
		new Random().nextBytes(input);
		MessageDigest md = MessageDigest.getInstance("MD5");
		hash = Base64.getUrlEncoder().encodeToString(md.digest(input));
		System.out.println(hash);
		obj.put("length", length);
		obj.put("hash", hash);
		return Response.status(200).entity(obj.toString()).build();
	}

}