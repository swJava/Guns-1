package com.stylefeng.guns.modular.batchMGR.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.constant.state.GenericState;
import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessDetailService;
import com.stylefeng.guns.modular.batchMGR.service.IBatchProcessService;
import com.stylefeng.guns.modular.batchMGR.warpper.BatchProcessDetailWrapper;
import com.stylefeng.guns.modular.batchMGR.warpper.BatchProcessWrapper;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.service.IAttachmentService;
import com.stylefeng.guns.util.DateUtil;
import com.stylefeng.guns.util.PathUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static com.stylefeng.guns.util.ExcelUtil.addCell;
import static com.stylefeng.guns.util.ExcelUtil.addCells;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2019/3/23 08:27
 * @Version 1.0
 */
@Controller
@RequestMapping("/batch")
public class BatchController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(BatchController.class);

    private String PREFIX = "/batchMGR/";

    @Value("${application.attachment.visit-url}")
    private String viewPath = "/";

    @Autowired
    private IBatchProcessService batchProcessService;

    @Autowired
    private IBatchProcessDetailService batchProcessDetailService;

    @Autowired
    private IAttachmentService attachmentService;

    private static final List<String> DETAIL_HEADER_DEFINE = new ArrayList<String>(){
        private static final long serialVersionUID = -5456067913018841267L;
        {
            add("序号");
            add("批次号");
            add("行号");
            add("处理状态");
            add("处理时间（秒）");
            add("导入时间");
            add("完成时间");
            add("导入数据");
            add("备注");
        }
    };

    /**
     * 跳转到批量成绩管理首页
     */
    @RequestMapping("/process/{service}")
    public String index(@PathVariable("service") String serviceName, Model model) {
        BatchServiceEnum service = BatchServiceEnum.valueOf(StringUtils.capitalize(serviceName));
        model.addAttribute("service", service.name());
        model.addAttribute("serviceCode", service.code);
        return PREFIX + "process.html";
    }


    /**
     * 跳转到添加批处理任务
     */
    @RequestMapping("/process/add")
    public String processAdd(String service, Model model) {
        BatchServiceEnum serviceInstance = BatchServiceEnum.valueOf(StringUtils.capitalize(service));

        model.addAttribute("service", serviceInstance.name());
        model.addAttribute("serviceCode", serviceInstance.code);
        return PREFIX + "process_"+service.toLowerCase()+"_wizard.html";
    }


    /**
     * 跳转到批量处理详情
     */
    @RequestMapping("/detail/{code}")
    public String detail(@PathVariable("code") String batchCode, Model model) {
        model.addAttribute("batchCode", batchCode);
        return PREFIX + "process_detail.html";
    }

    /**
     * 获取批量任务明细列表
     */
    @RequestMapping(value = "/detail/list")
    @ResponseBody
    public Object detailList(@RequestParam Map<String, String> queryParams){
        //分页查詢
        Page<BatchProcessDetail> page = new PageFactory<BatchProcessDetail>().defaultPage();
        Page<Map<String, Object>> pageMap = batchProcessDetailService.selectMapsPage(page, new EntityWrapper<BatchProcessDetail>(){
            {
                eq("status", GenericState.Valid.code);
                if (StringUtils.isNotEmpty(queryParams.get("batchCode"))){
                    eq("batch_code", queryParams.get("batchCode"));
                }
                if (StringUtils.isNotEmpty(queryParams.get("status"))){
                    try{
                        int status = Integer.parseInt(queryParams.get("status"));
                        switch(status){
                            case 1:
                                in("work_status", new Integer[]{0, 1});
                                break;
                            default:
                                eq("work_status", status);
                                break;
                        }
                    }catch(Exception e){}
                }
            }
        });
        //包装数据
        new BatchProcessDetailWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 获取批量任务列表
     */
    @RequestMapping(value = "/process/list")
    @ResponseBody
    public Object list(@RequestParam Map<String, String> queryParams) {
        //分页查詢
        Page<BatchProcess> page = new PageFactory<BatchProcess>().defaultPage();
        Page<Map<String, Object>> pageMap = batchProcessService.selectMapsPage(page, new EntityWrapper<BatchProcess>(){
            {
                eq("status", GenericState.Valid.code);
                if (StringUtils.isNotEmpty(queryParams.get("service"))){
                    BatchServiceEnum serviceEnum = BatchServiceEnum.valueOf(queryParams.get("service"));
                    eq("service", serviceEnum.code);
                }
                if (StringUtils.isNotEmpty(queryParams.get("status"))){
                    try{
                        int status = Integer.parseInt(queryParams.get("status"));
                        eq("work_status", status);
                    }catch(Exception e){}
                }
                if (StringUtils.isNotEmpty(queryParams.get("beginImportDate"))){
                    try{
                        Date queryDate = DateUtil.parse(queryParams.get("beginImportDate"), "yyyy-MM-dd");
                        ge("import_date", queryDate);
                    }catch(Exception e){}
                }
                if (StringUtils.isNotEmpty(queryParams.get("endImportDate"))){
                    try{
                        Date queryDate = DateUtil.parse(queryParams.get("endImportDate"), "yyyy-MM-dd");
                        lt("import_date", queryDate);
                    }catch(Exception e){}
                }
            }
        });
        //包装数据
        new BatchProcessWrapper(pageMap.getRecords()).warp();
        return super.packForBT(pageMap);
    }

    /**
     * 删除批处理任务（逻辑删）
     */
    @RequestMapping(value = "/process/delete")
    @ResponseBody
    public Object delete(@RequestParam String batchCode) {

        batchProcessService.delete(batchCode);

        return SUCCESS_TIP;
    }

    /**
     * 导出批处理详情
     */
    @RequestMapping(value = "/export/{batchCode}")
    @ResponseBody
    public Object export(@PathVariable("batchCode") String batchCode) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        List<BatchProcessDetail> detailList = batchProcessDetailService.selectList(batchCode);

        if (null == detailList){
            detailList = new ArrayList<BatchProcessDetail>();
        }

        resultMap.put("totalRecord", detailList.size());
        resultMap.put("dataResult", detailList);

        XSSFWorkbook workbook = buildBatchProcessDetailExportWorkbook(DETAIL_HEADER_DEFINE, resultMap);

        File storeFolder = attachmentService.getStoreFolder();
        String filename = attachmentService.getFilename();
        File destFile = new File(storeFolder, filename);

        Tip result = null;
        boolean exportSucceed = false;
        try {
            workbook.write(new FileOutputStream(destFile));
            exportSucceed = true;
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            result = new ErrorTip(500, "导出失败");
        }

        if (exportSucceed) {
            log.info("Order export file success");
            Attachment attachment = new Attachment();
            attachment.setMasterName("BATCH_PROCESS_DETAIL_EXPORT");
            attachment.setMasterCode(filename);
            attachment.setStatus(GenericState.Valid.code);
            attachment.setFileName("Batch-" + batchCode + ".xlsx");
            attachment.setAttachmentName(filename);
            attachment.setType("excel");
            attachment.setPath(destFile.getAbsolutePath());
            attachment.setSize(0L);
            attachmentService.insert(attachment);

            result = SUCCESS_TIP;
            result.setMessage(PathUtil.generate(viewPath, String.valueOf(attachment.getId())));
        }
        return result;
    }

    private XSSFWorkbook buildBatchProcessDetailExportWorkbook(List<String> headers, Map<String, Object> result) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("批量导入数据明细");

        // 合并列，显示统计信息： 总数量
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 11));
        Row top = sheet.createRow(0);
        top.setHeight((short) (40 * 20));
        Font topFont = workbook.createFont();
        topFont.setFontHeightInPoints((short) 20);
        topFont.setFontName("黑体");
        topFont.setColor(HSSFColor.BLACK.index);
        XSSFCellStyle topStyle = workbook.createCellStyle();
        topStyle.setFont(topFont);
        topStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        topStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        topStyle.setIndention((short) 2);
        // 添加统计信息
        String totalRecord = String.valueOf(result.get("totalRecord"));

        addCell(top, "", topStyle);
        addCell(top, "导入总数： " + totalRecord, topStyle);

        Row header = sheet.createRow(1);
        header.setHeight((short) (35 * 20));

        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setFontName("黑体");
        headerFont.setColor(HSSFColor.BLACK.index);
        XSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 添加头部
        addCells(header, headers, headerStyle);

        int valueStartRow = 2;
        int valueIndex = 1;

        Font valueFont = workbook.createFont();
        valueFont.setFontHeightInPoints((short) 9);
        valueFont.setFontName("宋体");
        valueFont.setColor(HSSFColor.BLACK.index);
        XSSFCellStyle valueStyle = workbook.createCellStyle();
        valueStyle.setFont(valueFont);
        valueStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        valueStyle.setIndention((short) 1);
        valueStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        List<BatchProcessDetail> resultList = (List<BatchProcessDetail>) result.get("dataResult");

        for(BatchProcessDetail order : resultList){
            Row valueRow = sheet.createRow(valueStartRow++);

            String workStatus = "";
            try {
                workStatus = BatchProcessStatusEnum.instanceOf(order.getWorkStatus()).text;
            }catch(Exception e){}

            String importDate = "";
            try{
                importDate = DateUtil.format(order.getImportDate(), "yyyy-MM-dd HH:mm:ss");
            }catch(Exception e){}

            String completeDate = "";
            try{
                completeDate = DateUtil.format(order.getCompleteDate(), "yyyy-MM-dd HH:mm:ss");
            }catch(Exception e){}

            addCell(valueRow, valueIndex++, valueStyle);
            addCell(valueRow, order.getBatchCode(), valueStyle);
            addCell(valueRow, order.getLine(), valueStyle);
            addCell(valueRow, workStatus, valueStyle);
            addCell(valueRow, order.getDuration(), valueStyle);
            addCell(valueRow, importDate, valueStyle);
            addCell(valueRow, completeDate, valueStyle);
            addCell(valueRow, order.getData(), valueStyle);
            addCell(valueRow, order.getRemark(), valueStyle);
        }

        //
        for (int i = 0; i < headers.size(); i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i,sheet.getColumnWidth(i)*11/10);
        }

        return workbook;
    }
}
