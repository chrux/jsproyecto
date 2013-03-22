<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.clase.dao.ConnDB"%>
<%@page import="net.sf.jasperreports.engine.export.JRRtfExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRHtmlExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.util.JRStyledText"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporter"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="net.sf.jasperreports.engine.xml.JRXmlLoader"%>
<%@page import="net.sf.jasperreports.engine.design.JasperDesign"%>
<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.clase.models.Usuario"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>

<%
String tipoReporte = request.getParameter("tipo");	
ConnDB conn = new ConnDB();
	JRExporter exporter = null;
	JasperPrint jasperPrint = null;
	String nombreReporte = "reportes/report1.jasper";
	final OutputStream servletOutputStream = response.getOutputStream();
	Map parameters = new HashMap();
	String idProy="0";

	File reportFile = new File(
			application.getRealPath("reportes/"+ tipoReporte +".jasper"));
	System.out.println("Pasa por aquì parameters.put test");
	//parameters.put("test", "test");
	try{
		idProy = request.getParameter("idProy");
		if(idProy.contains("-1")){
			response.sendRedirect("./ReportePersProyecto.jsp?mensaje=" + "Seleccione un proyecto");
			return;
		}
			
		System.out.println("Pasa por aqui obteniendo el id");
		parameters.put("Id_Proyecto", new Integer(idProy));
		
		
		System.out.println("Tipo: " + tipoReporte);
		
		
	}
	catch (Exception e){
		
	}
	
	try {

		
		System.out.println("Entra a generar Reporte ");
		response.setContentType("application/pdf");
		System.out.println("Llamó al PDF ");
		response.setHeader("Content-Disposition", "inline; filename=\""
				+ nombreReporte + ".pdf\"");
		exporter = new JRPdfExporter();
		System.out.println("Llamó al exporter ");

		/*response.setContentType("application/xls");
		response.setHeader("Content-Disposition", "inline; filename=\"" + nombreReporte + ".xls\"");
		exporter = new JRXlsExporter();*/
		
		//exporter = new JRHtmlExporter();
		
// 		response.setContentType("application/rtf");
// 					response.setHeader("Content-Disposition", "inline; filename=\"" + nombreReporte + ".rtf\"");
// 					exporter = new JRRtfExporter();
		System.out.println("Llamará al jasperPrint ");
		jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), parameters, conn.getConnection());
		System.out.println("Llamó al jasperPrint ");
		
		// estableciendo el valor de las fuentes.
		jasperPrint.setProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, "true");
		
		response.setHeader("Date", new java.util.Date().toString());
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
		// experimentacion

		exporter.exportReport();

		

		servletOutputStream.flush();
		
		

	} catch (Exception e) {

		out.println("Error: " + e.getMessage());

	}
%>

</head>
<body>

</body>
</html>
