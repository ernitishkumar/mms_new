package mms.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mms.com.beans.Detailof3311ss;

public class Detailof3311ssMapper {

	public Detailof3311ss detailsof3311Mapper(ResultSet rs){
		Detailof3311ss detailof3311ss = new Detailof3311ss();
		int i = 1;
		try {
				detailof3311ss.setCirclename(rs.getString(i));
				detailof3311ss.setDivision(rs.getString(i++));
				 detailof3311ss.setNameofss(rs.getString(i++));
				detailof3311ss.setNameof33kvfdr(rs.getString(i++));
				detailof3311ss.setDateofmaintain(rs.getString(i++));
				detailof3311ss.setNameofofficer(rs.getString(i++));
				detailof3311ss.setTotaltimesupply(rs.getString(i++));
				detailof3311ss.setNumberoftransformer(rs.getString(i++));
				detailof3311ss.setCapacity1(rs.getString(i++));
				detailof3311ss.setMake1(rs.getString(i++));
				detailof3311ss.setSerialnumber1(rs.getString(i++));
				detailof3311ss.setCapacity2(rs.getString(i++));
				detailof3311ss.setMake2(rs.getString(i++));
				detailof3311ss.setSerialnumber2(rs.getString(i++));
				detailof3311ss.setCapacity3(rs.getString(i++));
				detailof3311ss.setMake3(rs.getString(i++));
				detailof3311ss.setSerialnumber3(rs.getString(i++));
				detailof3311ss.setCapacity4(rs.getString(i++));
				detailof3311ss.setMake4(rs.getString(i++));
				detailof3311ss.setSerialnumber4(rs.getString(i++));
				detailof3311ss.setTemp1(rs.getString(i++));
				detailof3311ss.setHvtoe1(rs.getString(i++));
				detailof3311ss.setLvtoe1(rs.getString(i++));
				detailof3311ss.setHvtolv1(rs.getString(i++));
				detailof3311ss.setTemp2(rs.getString(i++));
				detailof3311ss.setHvtoe2(rs.getString(i++));
				detailof3311ss.setLvtoe2(rs.getString(i++));
				detailof3311ss.setHvtolv2(rs.getString(i++));
				detailof3311ss.setTemp3(rs.getString(i++));
				detailof3311ss.setHvtoe3(rs.getString(i++));
				detailof3311ss.setLvtoe3(rs.getString(i++));
				detailof3311ss.setHvtolv3(rs.getString(i++));
				detailof3311ss.setTemp4(rs.getString(i++));
				detailof3311ss.setHvtoe4(rs.getString(i++));
				detailof3311ss.setLvtoe4(rs.getString(i++));
				detailof3311ss.setHvtolv4(rs.getString(i++));
				detailof3311ss.setLa1(rs.getString(i++));
				detailof3311ss.setBody1(rs.getString(i++));
				detailof3311ss.setReportid(rs.getString(i++));
				detailof3311ss.setNeutral1(rs.getString(i++));
				detailof3311ss.setHvtolvearth1(rs.getString(i++));
				detailof3311ss.setLa2(rs.getString(i++));
				detailof3311ss.setBody2(rs.getString(i++));
				detailof3311ss.setNeutral2(rs.getString(i++));
				detailof3311ss.setHvtolvearth2(rs.getString(i++));
				detailof3311ss.setLa3(rs.getString(i++));
				detailof3311ss.setBody3(rs.getString(i++));
				detailof3311ss.setNeutral3(rs.getString(i++));
				detailof3311ss.setHvtolvearth3(rs.getString(i++));
				detailof3311ss.setLa4(rs.getString(i++));
				detailof3311ss.setBody4(rs.getString(i++));
				detailof3311ss.setNeutral4(rs.getString(i++));
				detailof3311ss.setHvtolvearth4(rs.getString(i++));
				detailof3311ss.setOiltopupptr1(rs.getString(i++));
				detailof3311ss.setOiltopupptr2(rs.getString(i++));
				detailof3311ss.setOiltopupptr3(rs.getString(i++));
				detailof3311ss.setOiltopupptr4(rs.getString(i++));
				detailof3311ss.setBreakdownvoltageptr1(rs.getString(i++));
				detailof3311ss.setBreakdownvoltageptr2(rs.getString(i++));
				detailof3311ss.setBreakdownvoltageptr3(rs.getString(i++));
				detailof3311ss.setBreakdownvoltageptr4(rs.getString(i++));
				detailof3311ss.setBucholzptr1(rs.getString(i++));
				detailof3311ss.setBucholzptr2(rs.getString(i++));
				detailof3311ss.setBucholzptr3(rs.getString(i++));
				detailof3311ss.setBucholzptr4(rs.getString(i++));
				detailof3311ss.setBreatherptr1(rs.getString(i++));
				detailof3311ss.setBreatherptr2(rs.getString(i++));
				detailof3311ss.setBreatherptr3(rs.getString(i++));
				detailof3311ss.setBreatherptr4(rs.getString(i++));
				detailof3311ss.setSilicaGelptr1(rs.getString(i++));
				detailof3311ss.setSilicaGelptr2(rs.getString(i++));
				detailof3311ss.setSilicaGelptr3(rs.getString(i++));
				detailof3311ss.setSilicaGelptr4(rs.getString(i++));
				detailof3311ss.setOtwtiPtr1(rs.getString(i++));
				detailof3311ss.setOtwtiPtr2(rs.getString(i++));
				detailof3311ss.setOtwtiPtr3(rs.getString(i++));
				detailof3311ss.setOtwtiPtr4(rs.getString(i++));
				detailof3311ss.setAnyotherworkptr1(rs.getString(i++));
				detailof3311ss.setAnyotherworkptr2(rs.getString(i++));
				 detailof3311ss.setAnyotherworkptr3(rs.getString(i++));
				detailof3311ss.setAnyotherworkptr4(rs.getString(i++));
				detailof3311ss.setIncomingvcb1(rs.getString(i++));
				 detailof3311ss.setIncomingvcb2(rs.getString(i++));
				detailof3311ss.setIncomingvcb3(rs.getString(i++));
				detailof3311ss.setIncomingvcb4(rs.getString(i++));
				detailof3311ss.setOutgoingvcb1(rs.getString(i++));
				detailof3311ss.setOutgoingvcb2(rs.getString(i++));
				detailof3311ss.setOutgoingvcb3(rs.getString(i++));
				detailof3311ss.setOutgoingvcb4(rs.getString(i++));
				detailof3311ss.setBus1_33kv(rs.getString(i++));
				detailof3311ss.setBus2_33kv(rs.getString(i++));
				detailof3311ss.setBus3_33kv(rs.getString(i++));
				detailof3311ss.setBus4_33kv(rs.getString(i++));
				detailof3311ss.setMainvcbptr1_33kv(rs.getString(i++));
				detailof3311ss.setMainvcbptr2_33kv(rs.getString(i++));
				detailof3311ss.setMainvcbptr3_33kv(rs.getString(i++));
				detailof3311ss.setMainvcbptr433kv(rs.getString(i++));
				detailof3311ss.setMainvcbptr111kv(rs.getString(i++));
				detailof3311ss.setMainvcbptr211kv(rs.getString(i++));
				detailof3311ss.setMainvcbptr311kv(rs.getString(i++));
				detailof3311ss.setMainvcbptr411kv(rs.getString(i++));
				detailof3311ss.setBus1_11kv(rs.getString(i++));
				detailof3311ss.setBus2_11kv(rs.getString(i++));
				detailof3311ss.setBus3_11kv(rs.getString(i++));
				detailof3311ss.setBus4_11kv(rs.getString(i++));
				detailof3311ss.setYard(rs.getString(i++));
				detailof3311ss.setControlroom(rs.getString(i++));
				detailof3311ss.setMaintenenceofbettary(rs.getString(i++));
				detailof3311ss.setMaintenanceofcontrolpanel(rs.getString(i++));
				detailof3311ss.setVcbmaint11kv(rs.getString(i++));
				detailof3311ss.setAnyoter(rs.getString(i++));
				 detailof3311ss.setType(rs.getString(i++));
				detailof3311ss.setStart_time_hr(rs.getString(i++));
				detailof3311ss.setStart_time_min(rs.getString(i++));
				detailof3311ss.setEnd_time_hr(rs.getString(i++));
				detailof3311ss.setEnd_time_min(rs.getString(i++));
				 detailof3311ss.setMain_cap_ptr1(rs.getString(i++));
				detailof3311ss.setMain_cap_ptr2(rs.getString(i++));
				detailof3311ss.setMain_cap_ptr3(rs.getString(i++));
				detailof3311ss.setMain_cap_ptr4(rs.getString(i++));	
				
		
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return detailof3311ss;
	}
}
