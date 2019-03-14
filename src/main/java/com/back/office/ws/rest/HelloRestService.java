package com.back.office.ws.rest;

import com.back.office.ws.db.DBConnection;
import com.back.office.ws.entity.*;
import com.back.office.ws.utils.WSUtils;
import com.google.gson.Gson;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("/")
public class HelloRestService {

    DBConnection connection = new DBConnection();

    //----POST methods ----

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
        for(ItemSale itemSale : itemSaleList){
            if(itemSale.getOrderId() != null && !itemSale.getOrderId().isEmpty()) {
                itemSale.setItemIdInt(itemSale.getItemId() != null && !itemSale.getItemId().isEmpty() ? Integer.parseInt(itemSale.getItemId()) : 0);
                itemSale.setQuantityInt(itemSale.getQuantity() != null && !itemSale.getQuantity().isEmpty() ? Integer.parseInt(itemSale.getQuantity()) : 0);
                itemSale.setPriceFloat(itemSale.getPrice() != null && !itemSale.getPrice().isEmpty() ? Float.parseFloat(itemSale.getPrice()) : 0);
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
                sif.setPackedTimeDate(sif.getPackedTime() != null && !sif.getPackedTime().isEmpty() ? WSUtils.convertStringToDateTime(sif.getPackedTime()) : null);
                sif.setCrewOpenedTimeDate(sif.getCrewOpenedTime() != null && !sif.getCrewOpenedTime() .isEmpty() ? WSUtils.convertStringToDateTime(sif.getCrewOpenedTime() ) : null);
                sif.setCrewClosedTimeDate(sif.getCrewClosedTime() != null && !sif.getCrewClosedTime().isEmpty() ? WSUtils.convertStringToDateTime(sif.getCrewClosedTime()) : null);
                connection.updateObjectHBM(sif);
                return Response.status(200).entity("ok").build();
            }
            else{
                sif.setDownloaded(new Date());
                int id = connection.insertObjectHBM(sif);
                return Response.status(200).entity(String.valueOf(id)).build();
            }
        }
        catch (Exception e){
            return Response.status(200).entity("error").build();
        }
    }

    //-----GET methods -----

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
        if(flights.size() == 1){
            Element orderMainDetail = root.addElement("flight");
            orderMainDetail.addElement("flightName").addText("");
            orderMainDetail.addElement("from").addText("");
            orderMainDetail.addElement("to").addText("");
            Element sectorMainElement = orderMainDetail.addElement("sectors");
            Element sectorElement = sectorMainElement.addElement("sector");
            sectorElement.addElement("from").addText("");
            sectorElement.addElement("to").addText("");
            sectorElement.addElement("type").addText("");

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
                ByteArrayInputStream bis = new ByteArrayInputStream(itemImage);
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
                ImageIO.write(outputImage, "png", file);
                return Response.ok(file, "image/png").header("Inline", "filename=\"" + file.getName() + "\"")
                        .build();
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
}
