package mms.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mms.com.beans.Detailof3311ss;
import mms.com.mapper.Detailof3311ssMapper;
import mms.com.utility.DatabaseConnection;


public class Detailof3311ssDAO {
    Connection connection = DatabaseConnection.getConnection();
	public ArrayList<Detailof3311ss> getAll(){
		ArrayList<Detailof3311ss> list = new ArrayList<Detailof3311ss>();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from mms_detail_of_3311_ss");
			ResultSet resultSet = ps.executeQuery();
			Detailof3311ssMapper mapper = new Detailof3311ssMapper();
			while(resultSet.next()){
				list.add(mapper.detailsof3311Mapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in [getAll]"+e);
		} finally{
			
		}
		
		return list;
		
	}
	public void add(Detailof3311ss detailof3311ss){
		
		
		try {
			PreparedStatement ps = connection.prepareStatement("insert into mms_detail_of_3311_ss(circlename,division,nameofss,nameof33kvfdr,dateofmaintain,nameofofficer,totaltimesupply,numberoftransformer,numberofmaintaintransformer,capacity,maker,serialnumber,temp,hvtoe,lvtoe,hvtolv,oiltopup,conditionofexpvent,silicagelreplaced,breathermaintenance,bucholz,la,neutral,body,workcondition,anyother,nameof33kvincoming,maintain33kvbus,maintain33kvprotection,maintain11kvbus,maintainanceof11kvfeeder,maintainanceofearthing,maintananceofligthningarrestor,mantainanceofbatterries,maintainanceofrelay,maintainanceofcapacitorbank,anyotherworkdoneyard,maintain11kvprot,type, bdvtype) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, detailof3311ss.getCirclename());
			ps.setString(2, detailof3311ss.getDivision());
			ps.setString(3, detailof3311ss.getNameofss());
			ps.setString(4, detailof3311ss.getNameof33kvfdr());
			ps.setString(5, detailof3311ss.getDateofmaintain());
			ps.setString(6, detailof3311ss.getNameofofficer());
			ps.setString(7, detailof3311ss.getTotaltimesupply());
			ps.setString(8, detailof3311ss.getNumberoftransformer());
			ps.setString(9, detailof3311ss.getCapacity1());
			ps.setString(10, detailof3311ss.getMake1());
			ps.setString(11, detailof3311ss.getSerialnumber1());
			ps.setString(12, detailof3311ss.getCapacity2());
			ps.setString(13, detailof3311ss.getMake2());
			ps.setString(14, detailof3311ss.getSerialnumber2());
			ps.setString(15, detailof3311ss.getCapacity3());
			ps.setString(16, detailof3311ss.getMake3());
			ps.setString(17, detailof3311ss.getSerialnumber3());
			ps.setString(18, detailof3311ss.getCapacity4());
			ps.setString(19, detailof3311ss.getMake4());
			ps.setString(20, detailof3311ss.getSerialnumber4());
			ps.setString(21, detailof3311ss.getTemp1());
			ps.setString(22, detailof3311ss.getHvtoe1());
			ps.setString(23, detailof3311ss.getLvtoe1());
			ps.setString(24, detailof3311ss.getHvtolv1());
			ps.setString(25, detailof3311ss.getTemp2());
			ps.setString(26, detailof3311ss.getHvtoe2());
			ps.setString(27, detailof3311ss.getLvtoe2());
			ps.setString(28, detailof3311ss.getHvtolv2());
			ps.setString(29, detailof3311ss.getTemp3());
			ps.setString(30, detailof3311ss.getHvtoe3());
			ps.setString(31, detailof3311ss.getLvtoe3());
			ps.setString(32, detailof3311ss.getHvtolv3());
			ps.setString(33, detailof3311ss.getTemp4());
			ps.setString(34, detailof3311ss.getHvtoe4());
			ps.setString(35, detailof3311ss.getLvtoe4());
			ps.setString(36, detailof3311ss.getHvtolv4());
			ps.setString(37, detailof3311ss.getLa1());
			ps.setString(38, detailof3311ss.getBody1());
			ps.setString(39, detailof3311ss.getReportid());
			ps.setString(40, detailof3311ss.getNeutral1());
			ps.setString(41, detailof3311ss.getHvtolvearth1());
			ps.setString(42, detailof3311ss.getLa2());
			ps.setString(43, detailof3311ss.getBody2());
			ps.setString(44, detailof3311ss.getNeutral2());
			ps.setString(45, detailof3311ss.getHvtolvearth2());
			ps.setString(46, detailof3311ss.getLa3());
			ps.setString(47, detailof3311ss.getBody3());
			ps.setString(48, detailof3311ss.getNeutral3());
			ps.setString(49, detailof3311ss.getHvtolvearth3());
			ps.setString(50, detailof3311ss.getLa4());
			ps.setString(51, detailof3311ss.getBody4());
			ps.setString(52, detailof3311ss.getNeutral4());
			ps.setString(53, detailof3311ss.getHvtolvearth4());
			ps.setString(54, detailof3311ss.getOiltopupptr1());
			ps.setString(55, detailof3311ss.getOiltopupptr2());
			ps.setString(56, detailof3311ss.getOiltopupptr3());
			ps.setString(57, detailof3311ss.getOiltopupptr4());
			ps.setString(58, detailof3311ss.getBreakdownvoltageptr1());
			ps.setString(59, detailof3311ss.getBreakdownvoltageptr2());
			ps.setString(60, detailof3311ss.getBreakdownvoltageptr3());
			ps.setString(61, detailof3311ss.getBreakdownvoltageptr4());
			ps.setString(62, detailof3311ss.getBucholzptr1());
			ps.setString(63, detailof3311ss.getBucholzptr2());
			ps.setString(64, detailof3311ss.getBucholzptr3());
			ps.setString(65, detailof3311ss.getBucholzptr4());
			ps.setString(66, detailof3311ss.getBreatherptr1());
			ps.setString(67, detailof3311ss.getBreatherptr2());
			ps.setString(68, detailof3311ss.getBreatherptr3());
			ps.setString(69, detailof3311ss.getBreatherptr4());
			ps.setString(70, detailof3311ss.getSilicaGelptr1());
			ps.setString(71, detailof3311ss.getSilicaGelptr2());
			ps.setString(72, detailof3311ss.getSilicaGelptr3());
			ps.setString(73, detailof3311ss.getSilicaGelptr4());
			ps.setString(74, detailof3311ss.getOtwtiPtr1());
			ps.setString(75, detailof3311ss.getOtwtiPtr2());
			ps.setString(76, detailof3311ss.getOtwtiPtr3());
			ps.setString(77, detailof3311ss.getOtwtiPtr4());
			ps.setString(78, detailof3311ss.getAnyotherworkptr1());
			ps.setString(79, detailof3311ss.getAnyotherworkptr2());
			ps.setString(80, detailof3311ss.getAnyotherworkptr3());
			ps.setString(81, detailof3311ss.getAnyotherworkptr4());
			ps.setString(82, detailof3311ss.getIncomingvcb1());
			ps.setString(83, detailof3311ss.getIncomingvcb2());
			ps.setString(84, detailof3311ss.getIncomingvcb3());
			ps.setString(85, detailof3311ss.getIncomingvcb4());
			ps.setString(86, detailof3311ss.getOutgoingvcb1());
			ps.setString(87, detailof3311ss.getOutgoingvcb2());
			ps.setString(88, detailof3311ss.getOutgoingvcb3());
			ps.setString(89, detailof3311ss.getOutgoingvcb4());
			ps.setString(90, detailof3311ss.getBus1_33kv());
			ps.setString(91, detailof3311ss.getBus2_33kv());
			ps.setString(92, detailof3311ss.getBus3_33kv());
			ps.setString(93, detailof3311ss.getBus4_33kv());
			ps.setString(94, detailof3311ss.getMainvcbptr1_33kv());
			ps.setString(95, detailof3311ss.getMainvcbptr2_33kv());
			ps.setString(96, detailof3311ss.getMainvcbptr3_33kv());
			ps.setString(97, detailof3311ss.getMainvcbptr433kv());
			ps.setString(98, detailof3311ss.getMainvcbptr111kv());
			ps.setString(99, detailof3311ss.getMainvcbptr211kv());
			ps.setString(100, detailof3311ss.getMainvcbptr311kv());
			ps.setString(101, detailof3311ss.getMainvcbptr411kv());
			ps.setString(102, detailof3311ss.getBus1_11kv());
			ps.setString(103, detailof3311ss.getBus2_11kv());
			ps.setString(104, detailof3311ss.getBus3_11kv());
			ps.setString(105, detailof3311ss.getBus4_11kv());
			ps.setString(106, detailof3311ss.getYard());
			ps.setString(107, detailof3311ss.getControlroom());
			ps.setString(108, detailof3311ss.getMaintenenceofbettary());
			ps.setString(109, detailof3311ss.getMaintenanceofcontrolpanel());
			ps.setString(110, detailof3311ss.getVcbmaint11kv());
			ps.setString(111, detailof3311ss.getAnyoter());
			ps.setString(112, detailof3311ss.getType());
			ps.setString(113, detailof3311ss.getStart_time_hr());
			ps.setString(114, detailof3311ss.getStart_time_min());
			ps.setString(115, detailof3311ss.getEnd_time_hr());
			ps.setString(116, detailof3311ss.getEnd_time_min());
			ps.setString(117, detailof3311ss.getMain_cap_ptr1());
			ps.setString(118, detailof3311ss.getMain_cap_ptr2());
			ps.setString(119, detailof3311ss.getMain_cap_ptr3());
			ps.setString(120, detailof3311ss.getMain_cap_ptr4());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in [add]"+e);
		}
		
	}
	
	public Detailof3311ss getbyreportID(String reportId){	
		PreparedStatement ps;
		Detailof3311ssMapper detailof3311ssMapper = new Detailof3311ssMapper();
		Detailof3311ss detailof3311ss = null;
		try {
			ps = connection.prepareStatement("select * from mms_detail_of_3311_ss where reportid=?");
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
			detailof3311ss = detailof3311ssMapper.detailsof3311Mapper(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in [getbyreportID]"+e);
		}
		
		return detailof3311ss;
	}
	
	public ArrayList<Detailof3311ss> getbySubStation(String subStation){
		ArrayList<Detailof3311ss> list = new ArrayList<Detailof3311ss>();
		try {
			Detailof3311ssMapper detailof3311ssMapper = new Detailof3311ssMapper();
			PreparedStatement ps=connection.prepareStatement("select * from mms_detail_of_3311_ss where nameofss=?");
			ps.setString(1, subStation);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				list.add(detailof3311ssMapper.detailsof3311Mapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in [getbySubStation]"+e);
		} finally{
			
		}
		
		return list;
	}
	
	public ArrayList<Detailof3311ss> getbyCircle(String circle){
		ArrayList<Detailof3311ss> list = new ArrayList<Detailof3311ss>();
		try {
			Detailof3311ssMapper detailof3311ssMapper = new Detailof3311ssMapper();
			PreparedStatement ps=connection.prepareStatement("select * from mms_detail_of_3311_ss where circlename=?");
			ps.setString(1, circle);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				list.add(detailof3311ssMapper.detailsof3311Mapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in [getbyCircle]"+e);
		} finally{
			
		}
		
		return list;
	}
	
	public ArrayList<Detailof3311ss> getbyDivision(String division){
		ArrayList<Detailof3311ss> list = new ArrayList<Detailof3311ss>();
		try {
			Detailof3311ssMapper detailof3311ssMapper = new Detailof3311ssMapper();
			PreparedStatement ps=connection.prepareStatement("select * from mms_detail_of_3311_ss where division=?");
			ps.setString(1, division);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				list.add(detailof3311ssMapper.detailsof3311Mapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in [getbyDivision]"+e);
		} finally{
			
		}
		
		return list;
	}
	
	public ArrayList<Detailof3311ss> getbyFeederName(String feederName){
		ArrayList<Detailof3311ss> list = new ArrayList<Detailof3311ss>();
		try {
			Detailof3311ssMapper detailof3311ssMapper = new Detailof3311ssMapper();
			PreparedStatement ps=connection.prepareStatement("select * from mms_detail_of_3311_ss where nameof33kvfdr=?");
			ps.setString(1, feederName);
			ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()){
				list.add(detailof3311ssMapper.detailsof3311Mapper(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception occured in [getbyFeederName]"+e);
		} finally{
			
		}
		
		return list;
	}
}
