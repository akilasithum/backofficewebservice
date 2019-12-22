package com.back.office.ws.rest;

import com.back.office.ws.db.DBConnection;
import com.back.office.ws.entity.*;
import com.back.office.ws.utils.WSUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("/")
public class HelloRestService {

    DBConnection connection = new DBConnection();

    //----POST methods ----

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    @Path("/userComments")
    public Response postMsg(String msg) {

        System.out.println("Request :" + msg);
        Gson gson = new Gson();
        UserComments comments = gson.fromJson(msg, UserComments.class);
        List<UserComment> userCommentList = comments.getUserComments();
        for(UserComment comment : userCommentList){
            connection.insertObjectHBM(comment);
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/orderMainDetails")
    public Response getOrderMainDetails(String msg) {
        System.out.println("Request :" + msg);
        JSONObject jsonObj  = XML.toJSONObject(msg);
        Gson gson = new Gson();
        JSONObject data = new JSONObject(jsonObj.toString()).getJSONObject("orderMainDetails");
        JSONArray itemsArr = data.getJSONArray("orderMainDetail");
        List<OrderMainDetails> orderMainDetailsList = gson.fromJson(itemsArr.toString(), new TypeToken<List<OrderMainDetails>>(){}.getType());
        for(OrderMainDetails mainDetails : orderMainDetailsList){
            if(mainDetails.getOrderId() != null && !mainDetails.getOrderId().isEmpty()) {
                mainDetails.setTaxFloat(mainDetails.getTax() != null && !mainDetails.getTax().isEmpty() ? Float.parseFloat(mainDetails.getTax()) : 0);
                mainDetails.setDiscountFloat(mainDetails.getDiscount() != null && !mainDetails.getDiscount().isEmpty() ? Float.parseFloat(mainDetails.getDiscount()) : 0);
                mainDetails.setSubTotalFloat(mainDetails.getSubTotal() != null ? Float.parseFloat(mainDetails.getSubTotal()) : 0);
                connection.insertObjectHBM(mainDetails);
            }
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/paymentMethods")
    public Response getPaymentMethods(String msg) {
        System.out.println("Request :" + msg);
        JSONObject jsonObj  = XML.toJSONObject(msg);
        Gson gson = new Gson();
        JSONObject data = new JSONObject(jsonObj.toString()).getJSONObject("paymentMethods");
        JSONArray itemsArr = data.getJSONArray("paymentMethod");
        List<PaymentMethods> paymentMethodsList = gson.fromJson(itemsArr.toString(), new TypeToken<List<PaymentMethods>>(){}.getType());
        for(PaymentMethods paymentMethods : paymentMethodsList){
            if(paymentMethods.getOrderId() != null && !paymentMethods.getOrderId().isEmpty()) {
                paymentMethods.setAmountFloat(paymentMethods.getAmount() != null && !paymentMethods.getAmount().isEmpty() ? Float.parseFloat(paymentMethods.getAmount()) : 0);
                connection.insertObjectHBM(paymentMethods);
            }
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/itemSales")
    public Response getItemSalesDetails(String msg) {
        System.out.println("Request :" + msg);
        JSONObject jsonObj  = XML.toJSONObject(msg);
        Gson gson = new Gson();
        JSONObject data = new JSONObject(jsonObj.toString()).getJSONObject("itemSales");
        JSONArray itemsArr = data.getJSONArray("itemSale");
        List<ItemSale> itemSaleList = gson.fromJson(itemsArr.toString(), new TypeToken<List<ItemSale>>(){}.getType());
        Map<String,Item> codeItemMap = connection.getIemCodeItemsMap();
        for(ItemSale itemSale : itemSaleList){
            if(itemSale.getOrderId() != null && !itemSale.getOrderId().isEmpty()) {
                itemSale.setItemIdInt(itemSale.getItemId() != null && !itemSale.getItemId().isEmpty() ? Integer.parseInt(itemSale.getItemId()) : 0);
                itemSale.setQuantityInt(itemSale.getQuantity() != null && !itemSale.getQuantity().isEmpty() ? Integer.parseInt(itemSale.getQuantity()) : 0);
                itemSale.setPriceFloat(itemSale.getPrice() != null && !itemSale.getPrice().isEmpty() ? Float.parseFloat(itemSale.getPrice()) : 0);
                itemSale.setUnitCostPrice(codeItemMap.get(itemSale.getItemId()).getCostPrice()*itemSale.getQuantityInt());
                connection.insertObjectHBM(itemSale);
            }
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/creditCardDetails")
    public Response getCreditCardDetails(String msg) {
        System.out.println("Request :" + msg);
        JSONObject jsonObj  = XML.toJSONObject(msg);
        Gson gson = new Gson();
        JSONObject data = new JSONObject(jsonObj.toString()).getJSONObject("creditCards");
        JSONArray itemsArr = data.getJSONArray("creditCard");
        List<CreditCard> creditCardList = gson.fromJson(itemsArr.toString(), new TypeToken<List<CreditCard>>(){}.getType());
        for(CreditCard creditCard : creditCardList) {
            if(creditCard.getOrderId() != null && !creditCard.getOrderId().isEmpty()) {
                creditCard.setAmountFloat(creditCard.getAmount() != null && !creditCard.getAmount().isEmpty() ? Float.parseFloat(creditCard.getAmount()) : 0);
                connection.insertObjectHBM(creditCard);
            }
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/posFlightDetails")
    public Response getPOSFightDetails(String msg) {
        System.out.println("Request :" + msg);
        JSONObject jsonObj  = XML.toJSONObject(msg);
        Gson gson = new Gson();
        JSONObject data = new JSONObject(jsonObj.toString()).getJSONObject("flights");
        JSONArray itemsArr = data.getJSONArray("flight");
        List<POSFlight> posFlightList = gson.fromJson(itemsArr.toString(), new TypeToken<List<POSFlight>>(){}.getType());
        for(POSFlight posFlight : posFlightList) {
            if(posFlight.getFlightId() != null && !posFlight.getFlightId().isEmpty()) {
                posFlight.seteClassPaxCountInt(posFlight.geteClassPaxCount() != null && !posFlight.geteClassPaxCount().isEmpty() ? Integer.parseInt(posFlight.geteClassPaxCount()) : 0);
                posFlight.setbClassPaxCountInt(posFlight.getbClassPaxCount() != null && !posFlight.getbClassPaxCount().isEmpty() ? Integer.parseInt(posFlight.getbClassPaxCount ()) : 0);
                posFlight.setFlightDateFld(posFlight.getFlightDate() != null && !posFlight.getFlightDate().isEmpty() ? WSUtils.convertStringToDate(posFlight.getFlightDate()) : null);
                posFlight.setSifNoInt(posFlight.getSifNo() != null && !posFlight.getSifNo().isEmpty() ? Integer.parseInt(posFlight.getSifNo ()) : 0);
                connection.insertObjectHBM(posFlight);

                List<EquipmentMaster> equipmentMasterList = connection.getEquipments(posFlight.getFlightName(),posFlight.getFlightDateFld());
                if(equipmentMasterList != null && !equipmentMasterList.isEmpty()){
                    for(EquipmentMaster equipmentMaster : equipmentMasterList){
                        equipmentMaster.setStatus("In Bond");
                        connection.updateObjectHBM(equipmentMaster);
                    }
                }
            }
        }

        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/sifDetails")
    public Response updateSIFDetails(String msg) {
        System.out.println("Request :" + msg);
        try {
            JAXBContext jc = JAXBContext.newInstance(SIFDetails.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            InputStream stream = IOUtils.toInputStream(msg, "UTF-8");
            SIFDetails sif = (SIFDetails)unmarshaller.unmarshal(stream);
            if(sif.getPackedFor() != null && !sif.getPackedFor().isEmpty()){
                SIFDetails availableSif = connection.getSIF(Integer.parseInt(sif.getSifNo()));
                sif.setSifNoInt(Integer.parseInt(sif.getSifNo()));
                sif.setDownloaded(availableSif.getDownloaded());
                sif.setPackedUser(availableSif.getPackedUser());
                if(availableSif.getPrograms() != null && !availableSif.getPrograms().isEmpty()){
                    sif.setPrograms(availableSif.getPrograms());
                }
                if(availableSif.getKitCodes() != null && !availableSif.getKitCodes().isEmpty()){
                    sif.setKitCodes(availableSif.getKitCodes());
                }
                sif.setPackedTimeDate(sif.getPackedTime() != null && !sif.getPackedTime().isEmpty() ? WSUtils.convertStringToDateTime(sif.getPackedTime()) : null);
                sif.setCrewOpenedTimeDate(sif.getCrewOpenedTime() != null && !sif.getCrewOpenedTime() .isEmpty() ? WSUtils.convertStringToDateTime(sif.getCrewOpenedTime() ) : null);
                sif.setCrewClosedTimeDate(sif.getCrewClosedTime() != null && !sif.getCrewClosedTime().isEmpty() ? WSUtils.convertStringToDateTime(sif.getCrewClosedTime()) : null);
                sif.setFlightDateStr(sif.getFlightDate() != null && !sif.getFlightDate().isEmpty() ? WSUtils.convertStringToDate(sif.getFlightDate() ) : availableSif.getFlightDateStr() );
                connection.updateObjectHBM(sif);

                HHCMaster oldHhcMaster = connection.getHHC(sif.getDeviceId());
                HHCMaster hhcMaster = new HHCMaster();
                hhcMaster.setFlightNo(sif.getPackedFor());
                hhcMaster.setHhcId(sif.getDeviceId());
                hhcMaster.setLastUsedDate(sif.getFlightDateStr());
                if(oldHhcMaster == null) {
                    hhcMaster.setRecordStatus(0);
                    hhcMaster.setStatus("In Flight");
                    connection.insertObjectHBM(hhcMaster);
                }
                else{
                    oldHhcMaster.setRecordStatus(1);
                    hhcMaster.setStatus("In Bond");
                    connection.updateObjectHBM(oldHhcMaster);
                    connection.insertObjectHBM(hhcMaster);
                }

                return Response.status(200).entity("ok").build();
            }
            else{
                sif.setDownloaded(sif.getSyncTime() != null && !sif.getSyncTime().isEmpty() ? WSUtils.convertStringToDateTime(sif.getSyncTime()) : null);
                int id = connection.insertObjectHBM(sif);
                return Response.status(200).entity(String.valueOf(id)).build();
            }
        }
        catch (Exception e){
            return Response.status(200).entity("error").build();
        }
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/cartNumbers")
    public Response updateCartItems(String msg) {
        System.out.println("Request :" + msg);
        JSONObject jsonObj  = XML.toJSONObject(msg);
        Gson gson = new Gson();
        JSONObject data = new JSONObject(jsonObj.toString()).getJSONObject("cartNumbers");
        JSONArray itemsArr = data.getJSONArray("cartNumber");
        List<CartNumbers> cartNumbersList = gson.fromJson(itemsArr.toString(), new TypeToken<List<CartNumbers>>(){}.getType());
        for(CartNumbers cartNumber : cartNumbersList) {
            if(cartNumber.getCartNumber() != null && !cartNumber.getCartNumber().isEmpty()) {
                connection.insertObjectHBM(cartNumber);
                EquipmentMaster equipmentMaster = new EquipmentMaster();
                EquipmentMaster oldHhcMaster = connection.getEquipment(cartNumber.getCartNumber());
                EquipmentType equipmentType = connection.getEquipmentType(cartNumber.getPackType());
                equipmentMaster.setEquipmentId(cartNumber.getCartNumber());
                SIFDetails sif = connection.getSIF(Integer.parseInt(cartNumber.getSifNo()));
                equipmentMaster.setFlightNumber(sif.getPackedFor());
                equipmentMaster.setLastUsedDate(sif.getFlightDateStr());
                equipmentMaster.setType(equipmentType.getEquipmentType());
                equipmentMaster.setStatus("Active");
                if(oldHhcMaster == null) {
                    connection.insertObjectHBM(equipmentMaster);
                }
                else{
                    oldHhcMaster.setRecordStatus(1);
                    connection.updateObjectHBM(oldHhcMaster);
                    connection.insertObjectHBM(equipmentMaster);
                }

            }
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/sealDetails")
    public Response updateSealDetails(String msg) {
        System.out.println("Request :" + msg);
        JSONObject jsonObj  = XML.toJSONObject(msg);
        Gson gson = new Gson();
        JSONObject data = new JSONObject(jsonObj.toString()).getJSONObject("seals");
        JSONArray itemsArr = data.getJSONArray("seal");
        List<SealDetails> sealDetails = gson.fromJson(itemsArr.toString(), new TypeToken<List<SealDetails>>(){}.getType());
        for(SealDetails seal : sealDetails) {
            if(seal.getSealNumber() != null && !seal.getSealNumber().isEmpty()) {
                connection.insertObjectHBM(seal);
            }
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/faDetails")
    public Response updateFADetails(String msg) {
        System.out.println("Request :" + msg);
        JSONObject jsonObj  = XML.toJSONObject(msg);
        Gson gson = new Gson();
        JSONObject data = new JSONObject(jsonObj.toString()).getJSONObject("faDetails");
        JSONArray itemsArr = data.getJSONArray("fa");
        List<FaDetails> sealDetails = gson.fromJson(itemsArr.toString(), new TypeToken<List<FaDetails>>(){}.getType());
        for(FaDetails fa : sealDetails) {
            if(fa.getFaName() != null && !fa.getFaName().isEmpty()) {
                fa.setFlightDateFld(WSUtils.convertStringToDate(fa.getFlightDate()));
                connection.insertObjectHBM(fa);
            }
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/openingInventory")
    public Response updateOpeningInventory(String msg) {
        System.out.println("Request :" + msg);
        JSONObject jsonObj  = XML.toJSONObject(msg);
        Gson gson = new Gson();
        JSONObject data = new JSONObject(jsonObj.toString()).getJSONObject("inventories");
        JSONArray itemsArr = data.getJSONArray("inventory");
        List<OpeningInventory> openingInventories = gson.fromJson(itemsArr.toString(), new TypeToken<List<OpeningInventory>>(){}.getType());
        for(OpeningInventory inventory : openingInventories) {
            if(inventory.getItemId() != null && !inventory.getItemId().isEmpty()) {
                //inventory.setCartNoInt(inventory.getCartNo() != null ? Integer.parseInt(inventory.getCartNo()) : 0);
                inventory.setQuantityInt(inventory.getQuantity() != null ? Integer.parseInt(inventory.getQuantity()) : 0);
                connection.insertObjectHBM(inventory);
            }
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    @Path("/preOrder")
    public Response updatePreOrder(String msg) {
        System.out.println("Request :" + msg);
        Gson gson = new Gson();
        PreOrders preOrders = gson.fromJson(msg, PreOrders.class);
        List<PreOrderWeb> preOrderWebList = preOrders.getPreOrders();
        for(PreOrderWeb preOrder : preOrderWebList){
            preOrder.setPreOrderStatus("Active");
            int preOrderId = connection.insertObjectHBM(preOrder);
            for(PreOrderItemWeb item : preOrder.getProducts()){
                item.setPreOrderId(preOrderId);
                connection.insertObjectHBM(item);
            }
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    @Path("/deliveredPreOrder")
    public Response getDeliveredPreOrders(String msg) {
        System.out.println("Request :" + msg);
        Gson gson = new Gson();
        PreOrders preOrders = gson.fromJson(msg, PreOrders.class);
        List<PreOrderWeb> preOrderWebList = preOrders.getPreOrders();
        for(PreOrderWeb preOrder : preOrderWebList){
            connection.updatePreOrder(preOrder);
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    @Path("/sifSheet")
    public Response updateSIFSheet(String msg) {
        System.out.println("Request :" + msg);
        Gson gson = new Gson();
        SIFSheets preOrders = gson.fromJson(msg, SIFSheets.class);
        List<SIFSheet> sifEntries = preOrders.getSifEntries();
        for(SIFSheet sifSheet : sifEntries){
            connection.insertObjectHBM(sifSheet);
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Consumes("application/xml")
    @Produces("text/plain")
    @Path("/faMessages")
    public Response updateFAMessages(String msg) {
        System.out.println("Request :" + msg);
        JSONObject jsonObj  = XML.toJSONObject(msg);
        Gson gson = new Gson();
        JSONObject data = new JSONObject(jsonObj.toString()).getJSONObject("faMsgs");
        JSONArray itemsArr = data.getJSONArray("faMsg");
        List<FaMessage> faMessages = gson.fromJson(itemsArr.toString(), new TypeToken<List<FaMessage>>(){}.getType());
        for(FaMessage faMessage : faMessages) {
            if(faMessage.getFlightNumber() != null && !faMessage.getFlightNumber().isEmpty()) {
                faMessage.setFlightDate(WSUtils.convertStringToDate(faMessage.getFlightDateStr()));
                connection.insertObjectHBM(faMessage);
            }
        }
        return Response.status(200).entity("ok").build();
    }

    //-----GET methods -----

    @GET // This annotation indicates GET request
    @Path("/hello")
    public Response hello() {
        return Response.status(200).entity("hello").build();
    }

    @GET
    @Path("/sectors")
    public Response getSectorDetails(@QueryParam("baseStation") String baseStation) {
        List<Sector> sectors = (List<Sector>) connection.getSectorsFromBaseStation(baseStation);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "sectors" );
        for(Sector sector : sectors) {
            Element sectorElement = root.addElement("sector");
            sectorElement.addElement("from").addText(sector.getSectorFrom());
            sectorElement.addElement("to").addText(sector.getSectorTo());
            sectorElement.addElement("sectorType").addText(sector.getSectorType());
            sectorElement.addElement("flightType").addText(sector.getFlightType());
            sectorElement.addElement("flightNo").addText(String.valueOf(sector.getFlightId()));
        }
        return Response.status(200).entity(document.asXML()).build();
    }

    @GET
    @Path("/flights")
    public Response flightDetails(@QueryParam("baseStation") String baseStation) {
        List<Flight> flights = (List<Flight>)connection.getFlightsFromBaseStation(baseStation);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "flights" );
        for(Flight flight : flights){
            Element flightElement = root.addElement("flight");
            flightElement.addElement("flightId").addText(String.valueOf(flight.getFlightId()));
            flightElement.addElement("obFlightName").addText(flight.getObFlightNo());
            flightElement.addElement("obFrom").addText(flight.getObFlightFrom());
            flightElement.addElement("obTo").addText(flight.getObFlightTo());
            flightElement.addElement("ibFlightName").addText(flight.getIbFlightNo());
            flightElement.addElement("ibFrom").addText(flight.getIbFlightFrom());
            flightElement.addElement("ibTo").addText(flight.getIbFlightTo());
        }
        if(flights.size() == 1){
            Element flightElement = root.addElement("flight");
            flightElement.addElement("flightId").addText("");
            flightElement.addElement("obFlightName").addText("");
            flightElement.addElement("obFrom").addText("");
            flightElement.addElement("obTo").addText("");
            flightElement.addElement("ibFlightName").addText("");
            flightElement.addElement("ibFrom").addText("");
            flightElement.addElement("ibTo").addText("");
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
            flightElement.addElement("equipment").addText(String.valueOf(kitCode.getPackTypes()));
        }
        if(kitCodes.size() == 1){
            Element flightElement = root.addElement("KITNumber");
            flightElement.addElement("kitCode").addText("");
            flightElement.addElement("kitDesc").addText("");
            flightElement.addElement("serviceType").addText("");
            flightElement.addElement("activeDate").addText("");
            flightElement.addElement("noOfEq").addText("");
            flightElement.addElement("equipment").addText("");
        }

        return Response.status(200).entity(document.asXML()).build();
    }

    @GET
    @Path("/messagesToHHC")
    public Response getBondMessages(@QueryParam("flightNumber") String flightNo,@QueryParam("flightDate") String flightDate) {
        List<BondMessage> bondMessages = connection.getBondMessages(flightNo.replace("--"," "),convertStringToDate(flightDate));
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement( "BondMsgs" );
        for(BondMessage kitCode : bondMessages) {
            Element flightElement = root.addElement("BondMsg");
            flightElement.addElement("messageId").addText(String.valueOf(kitCode.getBondMessageId()));
            flightElement.addElement("messageBody").addText(kitCode.getMessageBody());
        }
        if(bondMessages.size() == 1){
            Element flightElement = root.addElement("BondMsg");
            flightElement.addElement("messageId").addText("");
            flightElement.addElement("messageBody").addText("");
        }

        return Response.status(200).entity(document.asXML()).build();
    }

    @GET
    @Path("/preOrders")
    public Response getPreOrders(@QueryParam("flightNumber") String flightNo,@QueryParam("flightDate") String flightDate) {

        List<PreOrderWeb> preOrders = connection.getPreOrdersForFlight(flightNo.replace("--"," "),convertStringToDate(flightDate));
        Map<String,Item> itemMap = connection.getIemCodeItemsMap();
        JSONObject returnObj = new JSONObject();
        JSONArray preOrderArray = new JSONArray();
        for(PreOrderWeb preOrder : preOrders) {

            JSONObject preOrderObj = new JSONObject();
            preOrderObj.put("preOrderId",String.valueOf(preOrder.getPreOrderId()));
            preOrderObj.put("PNR",preOrder.getPnrNumber());
            preOrderObj.put("customerName",preOrder.getUserName());
            preOrderObj.put("serviceType",preOrder.getServiceType());

            JSONArray itemArr = new JSONArray();

            List<PreOrderItemWeb> preOrderItemWebList = connection.getPreOrderItems(preOrder.getPreOrderId());
            for(PreOrderItemWeb item : preOrderItemWebList){
                JSONObject itemObj = new JSONObject();
                itemObj.put("preOrderId",String.valueOf(item.getPreOrderId()));
                itemObj.put("category",itemMap.get(item.getProductNumber()).getCategory());
                itemObj.put("itemNo",item.getProductNumber());
                itemObj.put("quantity",String.valueOf(item.getQty()));
                itemArr.put(itemObj);
            }
            preOrderObj.put("items",itemArr);
            preOrderArray.put(preOrderObj);

            preOrder.setPreOrderStatus("In Progress");
            connection.updateObjectHBM(preOrder);
        }
        returnObj.put("preOrder",preOrderArray);
        return Response.status(200).entity(returnObj.toString()).build();
    }

    @GET
    @Path("/preOrderItems")
    public Response getPreOrderItems() {
        List<PreOrderItemWeb> preOrderItems = (List<PreOrderItemWeb>)connection.getAllValuesNoRecordStatus("com.back.office.ws.entity.PreOrderItem");
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement( "items" );
        for(PreOrderItemWeb preOrder : preOrderItems) {
            Element item = root.addElement("item");
            item.addElement("preOrderId").addText(String.valueOf(preOrder.getPreOrderId()));
            /*item.addElement("category").addText(preOrder.getCategory());*/
            item.addElement("itemNo").addText(preOrder.getProductNumber());
            item.addElement("quantity").addText(String.valueOf(preOrder.getQty()));
        }
        if(preOrderItems.size() == 1){
            Element flightElement = root.addElement("item");
            flightElement.addElement("preOrderId").addText("");
            flightElement.addElement("category").addText("");
            flightElement.addElement("itemNo").addText("");
            flightElement.addElement("quantity").addText("");
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
            flightElement.addElement("noOfDrawers").addText(String.valueOf(equipmentType.getNoOfDrawers()));
            flightElement.addElement("noOfSeals").addText(String.valueOf(equipmentType.getNoOfSeals()));
        }
        if(equipmentTypes.size() == 1){
            Element flightElement = root.addElement("Equipment");
            flightElement.addElement("equipmentNo").addText("");
            flightElement.addElement("equipmentDesc").addText("");
            flightElement.addElement("equipmentType").addText("");
            flightElement.addElement("noOfDrawers").addText("");
            flightElement.addElement("noOfSeals").addText("");
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
            flightElement.addElement("itemNo").addText(kitItem.getItemId());
            flightElement.addElement("itemDescription").addText(kitItem.getItemName());
            flightElement.addElement("drawer").addText(kitItem.getDrawerName());
            flightElement.addElement("quantity").addText(String.valueOf(kitItem.getQuantity()));
        }
        if(kitItems.size() == 1){
            Element flightElement = root.addElement("KITItem");
            flightElement.addElement("equipmentNo").addText("");
            flightElement.addElement("itemNo").addText("");
            flightElement.addElement("itemDescription").addText("");
            flightElement.addElement("drawer").addText("");
            flightElement.addElement("quantity").addText("");
        }
        return Response.status(200).entity(document.asXML()).build();
    }

    @GET
    @Path("/items")
    public Response getItems() {
        List<Item> items = (List<Item>)connection.getAllValues("com.back.office.ws.entity.Item");
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "Items" );
        for(Item item : items) {
            Element flightElement = root.addElement("Item");
            flightElement.addElement("itemNo").addText(String.valueOf(item.getItemId()));
            flightElement.addElement("itemCode").addText(String.valueOf(item.getItemCode()));
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
            flightElement.addElement("nfcTag").addText(String.valueOf(item.getNfcTag()));
        }
        return Response.status(200).entity(document.asXML()).build();
    }

    @POST
    @Consumes("text/plain")
    @Path("/itemImages")
    @Produces("image/png")
    public Response getItemImages(String itemCode) throws IOException {
            byte[] itemImage = connection.getItemImageFromItemCode(itemCode);
            if(itemImage != null) {
                /*ByteArrayInputStream bis = new ByteArrayInputStream(itemImage);
                BufferedImage bImage = ImageIO.read(bis);
                File file = new File(itemCode + ".png");
                float width = bImage.getWidth();
                float height = bImage.getHeight();
                float maxVal = 150;
                if (width > maxVal || height > maxVal) {
                    if (width >= height) {
                        height = (height / width) * maxVal;
                        width = maxVal;
                    } else {
                        width = (width / height) * maxVal;
                        height = maxVal;
                    }
                }
                int widthInt = (int) Math.round(width);
                int heightInt = (int) Math.round(height);
                BufferedImage outputImage = new BufferedImage(widthInt,
                        heightInt, bImage.getType());

                // scales the input image to the output image
                Graphics2D g2d = outputImage.createGraphics();
                g2d.drawImage(bImage, 0, 0, widthInt, heightInt, null);
                g2d.dispose();
                ImageIO.write(outputImage, "png", file);*/
                return Response.ok(
                        itemImage,
                        MediaType.APPLICATION_OCTET_STREAM_TYPE).build();
                /*return Response.ok(file, "image/png").header("Inline", "filename=\"" + file.getName() + "\"")
                        .build();*/
            }
            else {
                return Response.status(200).entity("error").build();
            }
    }

    @GET
    @Path("/vouchers")
    public Response getVouchers() {
        List<Voucher> vouchers = (List<Voucher>)connection.getAllValues("com.back.office.ws.entity.Voucher");
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "vouchers" );
        for(Voucher voucher : vouchers){
            Element voucherElement = root.addElement("voucher");
            voucherElement.addElement("voucherId").addText(String.valueOf(voucher.getVoucherId()));
            voucherElement.addElement("voucherName").addText(voucher.getVoucherName());
            voucherElement.addElement("voucherType").addText(voucher.getVoucherType());
            if(voucher.getVoucherType().equals("Ratio")){
                voucherElement.addElement("discount").addText(voucher.getAmount() + "+"+voucher.getDiscount());
            }
            else{
                voucherElement.addElement("discount").addText(voucher.getDiscount());
            }
        }
        if(vouchers.size() == 1){
            Element voucherElement = root.addElement("voucher");
            voucherElement.addElement("voucherId").addText("");
            voucherElement.addElement("voucherName").addText("");
            voucherElement.addElement("voucherType").addText("");
            voucherElement.addElement("discount").addText("");

        }
        return Response.status(200).entity(document.asXML()).build();
    }

    @GET
    @Path("/currencies")
    public Response getCurrencies() {
        List<Currency> currencies = (List<Currency>)connection.getAllValues("com.back.office.ws.entity.Currency");
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "currencies" );
        for(Currency currency : currencies){
            Element voucherElement = root.addElement("currency");
            voucherElement.addElement("currencyCode").addText(String.valueOf(currency.getCurrencyCode()));
            voucherElement.addElement("currencyDesc").addText(currency.getCurrencyDesc());
            voucherElement.addElement("currencyRate").addText(String.valueOf(currency.getCurrencyRate()));
            voucherElement.addElement("currencyType").addText(currency.getCurrencyType());
            voucherElement.addElement("priorityOrder").addText(currency.getPriorityOrder());
            voucherElement.addElement("effectiveDate").addText(currency.getEffectiveDate());
        }
        if(currencies.size() == 1){
            Element voucherElement = root.addElement("currency");
            voucherElement.addElement("currencyCode").addText("");
            voucherElement.addElement("currencyDesc").addText("");
            voucherElement.addElement("currencyRate").addText("");
            voucherElement.addElement("currencyType").addText("");
            voucherElement.addElement("priorityOrder").addText("");
            voucherElement.addElement("effectiveDate").addText("");
        }
        return Response.status(200).entity(document.asXML()).build();
    }

    @GET
    @Path("/promotions")
    public Response getPromotions() {
        List<Promotion> promotions = (List<Promotion>)connection.getCurrentPromotions();
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "comboDiscounts" );
        for(Promotion promotion : promotions){
            Element voucherElement = root.addElement("comboDiscount");
            voucherElement.addElement("comboId").addText(promotion.getPromoName());
            voucherElement.addElement("discount").addText(String.valueOf(promotion.getDiscount()));
            voucherElement.addElement("items").addText(promotion.getItemCodes());
        }
        if(promotions.size() == 1){
            Element voucherElement = root.addElement("comboDiscount");
            voucherElement.addElement("comboId").addText("");
            voucherElement.addElement("discount").addText("");
            voucherElement.addElement("items").addText("");
        }
        return Response.status(200).entity(document.asXML()).build();
    }

    @GET
    @Path("/users")
    public Response getUsers() {
        List<User> users = (List<User>)connection.getUsers();
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "users" );
        for(User user : users){
            Element voucherElement = root.addElement("user");
            voucherElement.addElement("userName").addText(user.getStaffId());
            voucherElement.addElement("password").addText(String.valueOf(user.getPassword()));
        }
        if(users.size() == 1){
            Element voucherElement = root.addElement("user");
            voucherElement.addElement("userName").addText("");
            voucherElement.addElement("password").addText("");
        }
        return Response.status(200).entity(document.asXML()).build();
    }

    public static Date convertStringToDate(String dateStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
}
