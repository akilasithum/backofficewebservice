package com.back.office.ws.rest;

import com.back.office.ws.db.DBConnection;
import com.back.office.ws.entity.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class HelloRestService {

    DBConnection connection = new DBConnection();

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/userComments")
    public Response postMsg(String msg) {
        System.out.println("Request :" + msg);
        JSONObject jsonObj  = XML.toJSONObject(msg);
        Gson gson = new Gson();
        JSONObject data = new JSONObject(jsonObj.toString()).getJSONObject("userComments");
        JSONArray itemsArr = data.getJSONArray("userComment");
        List<UserComment> userComments = gson.fromJson(itemsArr.toString(), new TypeToken<List<UserComment>>(){}.getType());
        return Response.status(200).entity("ok").build();
    }

    @GET // This annotation indicates GET request
    @Path("/hello")
    public Response hello() {
        return Response.status(200).entity("hello").build();
    }

    @GET
    @Path("/flights")
    public Response flightDetails() {
        List<Flight> flights = (List<Flight>)connection.getAllValues("com.back.office.ws.entity.Flight");
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "flights" );
        for(Flight flight : flights){
            Element flightElement = root.addElement("flight")
                    .addAttribute("flightName", flight.getFlightName());
            flightElement.addElement("from").addText(flight.getFlightFrom());
            flightElement.addElement("to").addText(flight.getFlightTo());
            List<Sector> sectors = connection.getFilterList("sectorFilter","flightId",flight.getFlightId(),
                    "com.back.office.ws.entity.Sector");
            Element sectorMainElement = flightElement.addElement("sectors");
            for(Sector sector : sectors) {
                Element sectorElement = sectorMainElement.addElement("sector");
                sectorElement.addElement("from").addText(sector.getSectorFrom());
                sectorElement.addElement("to").addText(sector.getSectorTo());
                sectorElement.addElement("type").addText(sector.getSectorType());
            }
        }
        return Response.status(200).entity(document.asXML()).build();
    }

    @GET
    @Path("/kitCodes")
    public Response getKitCodes() {
        List<KitCode> kitCodes = (List<KitCode>)connection.getAllValues("com.back.office.ws.entity.KitCode");
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement( "KITNumbers" );
        for(KitCode kitCode : kitCodes) {
            Element flightElement = root.addElement("KITNumber");
            flightElement.addElement("kitCode").addText(kitCode.getKitCode());
            flightElement.addElement("kitDesc").addText(kitCode.getDescription());
            flightElement.addElement("serviceType").addText(kitCode.getServiceType());
            flightElement.addElement("activeDate").addText(kitCode.getActivateDate());
            flightElement.addElement("noOfEq").addText(String.valueOf(kitCode.getNoOfEquipments()));
            flightElement.addElement("noOfSeals").addText(String.valueOf(kitCode.getNoOfSeals()));
        }

        return Response.status(200).entity(document.asXML()).build();
    }

    @GET
    @Path("/equipmentType")
    public Response getEquipmentTypes() {
        List<EquipmentType> equipmentTypes = (List<EquipmentType>)connection.getAllValues("com.back.office.ws.entity.EquipmentType");
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement( "Equipments" );
        for(EquipmentType equipmentType : equipmentTypes) {
            Element flightElement = root.addElement("Equipment");
            flightElement.addElement("equipmentNo").addText(equipmentType.getPackType());
            flightElement.addElement("equipmentDesc").addText(equipmentType.getPackDescription());
            flightElement.addElement("equipmentType").addText(equipmentType.getEquipmentType());
            flightElement.addElement("kitCode").addText(equipmentType.getKitCode());
            flightElement.addElement("noOfDrawers").addText(String.valueOf(equipmentType.getNoOfDrawers()));
        }

        return Response.status(200).entity(document.asXML()).build();
    }

    @GET
    @Path("/kitItems")
    public Response getKitItems() {
        List<KitItem> kitItems = (List<KitItem>)connection.getAllValues("com.back.office.ws.entity.KitItem");
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement( "KITItems" );
        for(KitItem kitItem : kitItems) {
            Element flightElement = root.addElement("KITItem");
            flightElement.addElement("equipmentNo").addText(kitItem.getPackType());
            flightElement.addElement("itemNo").addText(String.valueOf(kitItem.getItemId()));
            flightElement.addElement("itemDescription").addText(kitItem.getItemName());
            flightElement.addElement("drawer").addText(kitItem.getDrawerName());
            flightElement.addElement("quantity").addText(String.valueOf(kitItem.getQuantity()));
        }
        return Response.status(200).entity(document.asXML()).build();
    }

    @GET
    @Path("/items")
    public Response getItems() {
        List<Item> kitItems = (List<Item>)connection.getAllValues("com.back.office.ws.entity.Item");
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement( "Items" );
        for(Item item : kitItems) {
            Element flightElement = root.addElement("Item");
            flightElement.addElement("itemNo").addText(String.valueOf(item.getItemId()));
            flightElement.addElement("itemName").addText(item.getItemName());
            flightElement.addElement("category").addText(item.getCategory());
            flightElement.addElement("catlogNo").addText(item.getCatalogue());
            flightElement.addElement("price").addText(String.valueOf(item.getBasePrice()));
            flightElement.addElement("serviceType").addText(item.getServiceType());
            flightElement.addElement("baseCurrency").addText(item.getBaseCurrency());
            flightElement.addElement("secondCurrency").addText(item.getCostCurrency());
            flightElement.addElement("secondPrice").addText(String.valueOf(item.getCostPrice()));
            flightElement.addElement("activeDate").addText(item.getActivateDate());
            flightElement.addElement("weight").addText(String.valueOf(item.getWeight()));
        }
        return Response.status(200).entity(document.asXML()).build();
    }
}
