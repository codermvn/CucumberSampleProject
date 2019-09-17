package com.lh.test.OBR;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lh.test.model.Location;
import com.lh.test.model.Market;
import com.lh.test.model.OBR.OBRRequest;
import com.lh.test.model.OBR.OBRResponse;
import com.lh.test.model.OBR.Obr;

public class OBRService {

	Logger Log = Logger.getLogger(OBRService.class.getName());

	public static void main(String[] args) throws Exception {
		OBRService obrServiceObj = new OBRService();

	}

	public OBRRequest createOBRRequest(List<Market> marketLst, String tigerID, String filingMethod)
			throws JsonParseException, JsonMappingException, IOException {
		OBRRequest obrRequest = null;
		String filePath = new String();
		if(filingMethod == null)
			filingMethod = "";
		switch (filingMethod.toUpperCase().replaceAll("\\s", "")) {
		case "CAT35FLOAT":
			filePath = System.getProperty("user.dir") + "\\OBRRequest_CAT35Float.json";
			break;
		case "CAT25":
			filePath = System.getProperty("user.dir") + "\\OBRRequest_CAT25.json";
			break;
		case "CAT35FIX":
			filePath = System.getProperty("user.dir") + "\\OBRRequest_CAT35Fix.json";
			break;
		default:
			filePath = System.getProperty("user.dir") + "\\OBRRequest_CAT35Float.json";
		}
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ObjectMapper ob = new ObjectMapper();
		obrRequest = ob.readValue(contentBuilder.toString(), OBRRequest.class);
		obrRequest.setTigerContractNumber(tigerID);
		List<Obr> obrObject = (List<Obr>) SerializationUtils.clone((Serializable) obrRequest.getObrs());
		obrRequest.setObrs(new ArrayList<Obr>());

		for (Market market : marketLst) {
			for (Obr obr : obrObject ) {
				obr = (Obr) SerializationUtils.clone(obr);
			Location location = obr.getFrame().getLocation();

			location.setOriginCode(market.getMarketOrgn());
			location.setDestinationCode(market.getMarketDstn());

			if (market.getMarketOrgn().length() == 4)
				location.setOriginType("AREA");
			else if (market.getMarketOrgn().length() == 3)
				location.setOriginType("CITY");
			else if (market.getMarketOrgn().length() == 2)
				location.setOriginType("COUNTRY");
			else
				Assert.fail("Market Origin is neither CITY/COUNTRY/AREA");

			if (market.getMarketDstn().length() == 4)
				location.setDestinationType("AREA");
			else if (market.getMarketDstn().length() == 3)
				location.setDestinationType("CITY");
			else if (market.getMarketDstn().length() == 2)
				location.setDestinationType("COUNTRY");
			else
				Assert.fail("Market Destination is neither CITY/COUNTRY/AREA");

			obrRequest.getObrs().add(obr);
			}
		}

		return obrRequest;

	}

	public List<Integer> doRequestToPLP(OBRRequest obrRequest) throws Exception {
		List<Integer> OBR_IDLst = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String obrRequestStr = objectMapper.writeValueAsString(obrRequest);

		Log.info("OBR Request" + obrRequestStr);
		String response = getTarget().request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(obrRequestStr, MediaType.APPLICATION_JSON)).readEntity(String.class);
		if (response != null && !response.isEmpty()) {
			Log.info("OBR Response:" + response);
			OBRResponse obrResponse = objectMapper.readValue(response, OBRResponse.class);
			if (obrResponse != null && obrResponse.getObrIds() != null && obrResponse.getObrIds().size() > 0) {
				OBR_IDLst = new ArrayList<Integer>();
				OBR_IDLst.addAll(obrResponse.getObrIds());
			}
		}

		return OBR_IDLst;

	}

	public static WebTarget getTarget() throws Exception {

		WebTarget target = null;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		target = client
				.target(UriBuilder.fromUri("https://airlapi.isb.lsy.fra.dlh.de/plp/api/panda/standard_obr").build());
		return target;
	}

	public String generateTigerID() {
		Random rand = new Random();
		int number = rand.nextInt(900000) + 100000;
		return "TIG" + number;
	}

}
