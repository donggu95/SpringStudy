<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.koreait.project1210.domain.Map"%>
<%@page import="com.koreait.project1210.model.repository.MapDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! MapDAO mapDAO= new MapDAO(); %>
<%
   String map_id = request.getParameter("map_id");

   Map map = mapDAO.select(Integer.parseInt(map_id));

   JsonObject json = new JsonObject();
   json.addProperty("map_id", map.getMap_id());
   json.addProperty("title", map.getTitle());
   json.addProperty("lati", map.getLati());
   json.addProperty("longi", map.getLongi());
   json.addProperty("filename", map.getFilename());
   
   Gson gson = new Gson();
   String jsonStr = gson.toJson(json);
   
   out.print(jsonStr);
%>