package com.example.demo;

import com.example.demo.bean.Detailed;
import com.example.demo.bean.Language;
import com.example.demo.dao.LanguageDao;
import com.example.demo.service.DetailedService;
import com.example.demo.util.POIExcelUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Resource
	private LanguageDao languageDao;

	@Resource
	private DetailedService detailedService;

	@Test
	public void contextLoads() throws Exception {
		//多个sheet页同时导出
		String exportFilePath3 = "D:\\Faq20190301全部问题详情.xls";
		List<Language> languages = languageDao.findAll();
		String[] sheetNameArr = new String [languages.size()];
		String[] tableTitleArr = new String [languages.size()];
		String[] headTablesColumnsNameArr = new String [languages.size()];
		String[] tablesColumnNameArr = new String [languages.size()];
		List<Object> list = new ArrayList<Object>();

		for (int i = 0 ; i < languages.size(); i++) {
			sheetNameArr[i] = languages.get(i).getTitle();
			tableTitleArr[i] = "";
			headTablesColumnsNameArr[i] = "父级,问题ID,标题,答案";
			tablesColumnNameArr[i] = "flTitle,id,title,contentTxt";
			List<Detailed> detaileds = new ArrayList<>();
			detaileds= detailedService.getAllByLangId(languages.get(i).getId());
			list.add(detaileds);
		}
		//new POIExcelUtils(exportFilePath3,sheetNameArr,tablesColumnArr,list);
		POIExcelUtils poiExcel = new POIExcelUtils(sheetNameArr,tableTitleArr,headTablesColumnsNameArr,list,tablesColumnNameArr);
		poiExcel.excelDataExport(exportFilePath3);
	}

}
