package com.ping.wu.controller;

import com.ping.wu.dao.TravelRecordDataMapper;
import com.ping.wu.model.TravelRecordData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuping
 * @date 2019-07-19
 */

@RequestMapping("/test")
@Controller
public class TestController {
    private final TravelRecordDataMapper travelRecordDataMapper;

    @Autowired
    public TestController(TravelRecordDataMapper travelRecordDataMapper) {
        this.travelRecordDataMapper = travelRecordDataMapper;
    }

    @GetMapping("/testWrite")
    @ResponseBody
    public String testWrite(HttpServletRequest request) {
        String name = request.getParameter("name");
        TravelRecordData recordData = new TravelRecordData();
        recordData.setName(name);
        travelRecordDataMapper.insert(recordData);
        return "success";
    }

    @GetMapping("/testRead")
    @ResponseBody
    public String testRead(HttpServletRequest request) {
        String id = request.getParameter("id");
        TravelRecordData recordData = travelRecordDataMapper.selectByPrimaryKey(Long.valueOf(id));
        return recordData.getName();
    }
}
