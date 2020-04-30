package com.example.educationsystemapp.service.serviceimplementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.educationsystemapp.entity.College;
import com.example.educationsystemapp.entity.University;
import com.example.educationsystemapp.exception.service.EducationSystemAppServiceException;
import com.example.educationsystemapp.exception.service.custom.UniversityAlreadyExistsExcepyion;
import com.example.educationsystemapp.exception.service.custom.UniversityNotFoundException;
import com.example.educationsystemapp.repository.CollegeRepository;
import com.example.educationsystemapp.repository.UniversityRepository;
import com.example.educationsystemapp.service.UniversityService;

@Service
public class UniversityServiceImpl implements UniversityService {

	@Autowired
	private UniversityRepository universityRepository;

	@Autowired
	private CollegeRepository collegeRepository;

	@Override
	public University saveUniversity(University university) throws EducationSystemAppServiceException {

		if (universityRepository.findByUniversityName(university.getUniversityName()).isPresent())
			throw new UniversityAlreadyExistsExcepyion("University Already Exists.");

		universityRepository.save(university);
		return university;

	}

	@Override
	public List<University> findAllUniversity() throws EducationSystemAppServiceException {

		List<University> universitiesList = universityRepository.findAll();

		/*
		 * if (universitiesList.isEmpty()) { throw new
		 * UniversityNotFoundException("University not found"); }
		 */

		universitiesList.stream().findAny().orElseThrow(() -> new UniversityNotFoundException("University not found"));

		universitiesList.forEach(university -> {
			Collections.sort(university.getCollegeList(), new Comparator<College>() {
				@Override
				public int compare(College c1, College c2) {
					return -Integer.compare(c1.getTotalStudents(), c2.getTotalStudents());
				}
			});
		});

		return universitiesList;
	}

	public void writingInExcel() throws EducationSystemAppServiceException {
		Map<College, University> map = new TreeMap<College, University>();

		try {
			collegeRepository.findAll().forEach(c -> {
				College college = new College(c.getCollegeId(), c.getCollegeName(), c.getTotalStudents());
				University university = new University(c.getUniversity().getUniversityId(),
						c.getUniversity().getUniversityName(), c.getUniversity().getTotalColleges());
				map.put(college, university);
			});

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("College with University Details");

			Row row;
			row = sheet.createRow(0);
			Cell cell01 = row.createCell(0);
			cell01.setCellValue("College_Id");
			Cell cell02 = row.createCell(1);
			cell02.setCellValue("College_Name");
			Cell cell03 = row.createCell(2);
			cell03.setCellValue("Total_student");
			Cell cell04 = row.createCell(3);
			cell04.setCellValue("University_Id");
			Cell cell05 = row.createCell(4);
			cell05.setCellValue("University_Name");
			Cell cell06 = row.createCell(5);
			cell06.setCellValue("Total_College");

			int rownum = 1;

			for (Map.Entry<College, University> mapdata : map.entrySet()) {

				row = sheet.createRow(rownum++);
				Cell cell1 = row.createCell(0);
				cell1.setCellValue(mapdata.getKey().getCollegeId());

				Cell cell2 = row.createCell(1);
				cell2.setCellValue(mapdata.getKey().getCollegeName());

				Cell cell3 = row.createCell(2);
				cell3.setCellValue(mapdata.getKey().getTotalStudents());

				Cell cell4 = row.createCell(3);
				cell4.setCellValue(mapdata.getValue().getUniversityId());

				Cell cell5 = row.createCell(4);
				cell5.setCellValue(mapdata.getValue().getUniversityName());

				Cell cell6 = row.createCell(5);
				cell6.setCellValue(mapdata.getValue().getTotalColleges());
			}

			FileOutputStream fileOutputStream = new FileOutputStream(new File(
					"D:\\CodingChallenge1303\\EducationSystemApp\\src\\main\\resources\\static\\CollegewithUniversity.xlsx"));
			workbook.write(fileOutputStream);

			fileOutputStream.close();
			workbook.close();

		} catch (IOException e) {

			throw new EducationSystemAppServiceException(e.getMessage());

		}

	}

	public List<String> readingFromExcel() throws EducationSystemAppServiceException {
		try {

			FileInputStream fileInputStream = new FileInputStream(
					"D:\\CodingChallenge1303\\EducationSystemApp\\src\\main\\resources\\static\\CollegewithUniversity.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheet("College with University Details");
			List<String> rowdata = new ArrayList<String>();

			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.iterator();

				List<String> celldata = new ArrayList<String>();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String s1 = cell.toString() + "    ";
					celldata.add(s1);

				}
				String s = celldata + "";
				rowdata.add(s);

			}

			workbook.close();
			fileInputStream.close();
			return rowdata;

		} catch (IOException e) {
			throw new EducationSystemAppServiceException(e.getMessage());
		}
	}

}
